#!/usr/bin/env node

/**
 * Exporta o catálogo público da Giassi (VTEX) em CSV.
 * Requer Node.js 18+ e não usa dependências externas.
 */

import {
    appendFile,
    copyFile,
    mkdir,
    rename,
    writeFile
} from 'node:fs/promises'

import {
    dirname,
    resolve
} from 'node:path'


const BASE_URL =
    'https://www.giassi.com.br/api/catalog_system/pub/products/search'

const DEFAULT_BATCH_SIZE = 50


function usage() {

    console.log(`
Uso:

  node giassi-scraper.mjs [opções]

Opções:

  --output <arquivo>   CSV de saída (padrão: produtos-giassi.csv)

  --limit <n>          Limita a quantidade de SKUs (bom para teste)

  --pause <ms>         Pausa entre requisições (padrão: 800)

  --cookie <valor>     Cookie de uma sessão já autenticada
                       (ou use GIASSI_COOKIE)

  --help               Exibe esta ajuda

Exemplo:

  node giassi-scraper.mjs --output produtos.csv --limit 100

`)
}


function parseArgs(args) {

    const config = {
        output: 'produtos-giassi.csv',
        limit: Infinity,
        pause: 800,
        cookie: process.env.GIASSI_COOKIE
    }

    for (let i = 0;i < args.length;i += 1) {

        const arg = args[i]

        if (arg === '--help') {
            return { help: true }
        }

        if (
            ![
                '--output',
                '--limit',
                '--pause',
                '--cookie'
            ].includes(arg)
        ) {
            throw new Error(`Opção desconhecida: ${arg}`)
        }

        const value = args[++i]

        if (!value || value.startsWith('--')) {
            throw new Error(`Falta o valor de ${arg}`)
        }

        if (arg === '--output') {
            config.output = value
        }

        if (arg === '--limit') {
            config.limit = parsePositiveInteger(
                value,
                '--limit'
            )
        }

        if (arg === '--pause') {
            config.pause = parseNonNegativeInteger(
                value,
                '--pause'
            )
        }

        if (arg === '--cookie') {
            config.cookie = value
        }
    }

    return config
}


function parsePositiveInteger(value, option) {

    const number = Number(value)

    if (
        !Number.isInteger(number) ||
        number <= 0
    ) {
        throw new Error(
            `${option} deve ser um inteiro positivo.`
        )
    }

    return number
}


function parseNonNegativeInteger(value, option) {

    const number = Number(value)

    if (
        !Number.isInteger(number) ||
        number < 0
    ) {
        throw new Error(
            `${option} deve ser um inteiro maior ou igual a zero.`
        )
    }

    return number
}


function sleep(ms) {

    return new Promise(
        resolve => setTimeout(resolve, ms)
    )
}


/**
 * Busca uma página da API VTEX.
 */
async function getPage(from, to, cookie) {

    const url = new URL(BASE_URL)

    url.searchParams.set(
        '_from',
        String(from)
    )

    url.searchParams.set(
        '_to',
        String(to)
    )


    const headers = {
        Accept: 'application/json',
        'User-Agent': 'giassi-catalog-export/1.0'
    }


    if (cookie) {
        headers.Cookie = cookie
    }


    let lastError


    for (
        let attempt = 0;
        attempt < 4;
        attempt += 1
    ) {

        try {

            const response = await fetch(
                url,
                {
                    headers,
                    signal: AbortSignal.timeout(30_000)
                }
            )


            if (response.ok) {

                const payload =
                    await response.json()


                if (!Array.isArray(payload)) {

                    throw new Error(
                        'A API respondeu em um formato inesperado.'
                    )
                }


                return payload
            }


            /*
             * Esses erros podem ser temporários.
             */
            if (
                ![
                    429,
                    500,
                    502,
                    503,
                    504
                ].includes(response.status)
            ) {

                throw new Error(
                    `HTTP ${response.status}: ${await response.text()}`
                )
            }


            lastError =
                new Error(
                    `HTTP ${response.status}`
                )

        } catch (error) {

            lastError = error
        }


        console.log(
            `Tentativa ${attempt + 1}/4 falhou para ${from}-${to}.`
        )


        await sleep(
            1_000 * (attempt + 1)
        )
    }


    throw lastError
}


/**
 * Tenta buscar uma página.
 *
 * Se a API retornar erro, divide o intervalo
 * em duas partes e tenta novamente.
 *
 * Exemplo:
 *
 * 1400-1449
 *
 * vira:
 *
 * 1400-1424
 * 1425-1449
 */
async function getPageWithFallback(
    from,
    to,
    cookie
) {

    try {

        return await getPage(
            from,
            to,
            cookie
        )

    } catch (error) {

        /*
         * Se chegamos a apenas um item,
         * não existe mais como dividir.
         */
        if (from === to) {
            throw error
        }


        const middle =
            Math.floor(
                (from + to) / 2
            )


        console.log(
            `Falha em ${from}-${to}. ` +
            `Dividindo em ${from}-${middle} ` +
            `e ${middle + 1}-${to}.`
        )


        const firstHalf =
            await getPageWithFallback(
                from,
                middle,
                cookie
            )


        const secondHalf =
            await getPageWithFallback(
                middle + 1,
                to,
                cookie
            )


        return [
            ...firstHalf,
            ...secondHalf
        ]
    }
}


/**
 * Prepara valores para CSV.
 */
function csv(value) {

    const text =
        value === null ||
            value === undefined
            ? ''
            : String(value)


    return `"${text.replaceAll('"', '""')}"`
}


/**
 * Transforma os produtos retornados pela VTEX
 * nas linhas que serão salvas no CSV.
 */
function rowsFromProducts(products) {

    const rows = []


    for (const product of products) {

        for (
            const item of product.items ?? []
        ) {

            /*
             * Procura primeiro o vendedor padrão.
             * Se não encontrar, usa o primeiro vendedor.
             */
            const offer =
                item.sellers?.find(
                    seller => seller.sellerDefault
                )?.commertialOffer

                ?? item.sellers?.[0]?.commertialOffer

                ?? {}


            rows.push({

                /*
                 * ID único do SKU no Giassi/VTEX.
                 */
                sku_id: item.itemId,

                /*
                 * Nome do produto.
                 */
                nome:
                    item.nameComplete ||
                    item.name ||
                    product.productName,

                /*
                 * Preço atual.
                 */
                preco:
                    offer.Price ?? '',

                /*
                 * Unidade de medida.
                 */
                unidade:
                    item.measurementUnit ?? '',

                /*
                 * Multiplicador.
                 */
                multiplicador:
                    item.unitMultiplier ?? ''
            })
        }
    }


    return rows
}


/**
 * Colunas do CSV.
 */
const columns = [
    'sku_id',
    'nome',
    'preco',
    'unidade',
    'multiplicador'
]


/**
 * Função principal.
 */
async function main() {
    await mkdir("dados", { recursive: true });

    const temporaryPath = `${config.output}.partial`;

    let totalExportados = 0;

    try {
        await writeFile(
            temporaryPath,
            `${CSV_COLUMNS.join(",")}\n`,
            "utf8"
        );

        let from = 0;

        while (true) {
            const to = from + DEFAULT_BATCH_SIZE - 1;

            const products = await getPageWithFallback(
                from,
                to,
                config.cookie
            );

            if (products.length === 0) {
                break;
            }

            const rows = rowsFromProducts(products);

            const novasRows = rows.filter(
                row => !seenSkus.has(row.sku_id)
            );

            for (const row of novasRows) {
                seenSkus.add(row.sku_id);
            }

            if (novasRows.length > 0) {
                const csv = novasRows
                    .map(row => rowToCsv(row))
                    .join("\n");

                await appendFile(
                    temporaryPath,
                    csv + "\n",
                    "utf8"
                );
            }

            totalExportados += novasRows.length;

            console.log(
                `Página ${from}-${to}: ${products.length} produtos; ${novasRows.length} SKUs exportados.`
            );

            from += DEFAULT_BATCH_SIZE;

            await sleep(config.pause);
        }

        // Terminou normalmente
        await rename(temporaryPath, config.output);

        console.log(`\nConcluído!`);
        console.log(`CSV salvo em: ${config.output}`);
        console.log(`Total exportado: ${totalExportados}`);

    } catch (error) {

        console.error("\n❌ O scraper encontrou um erro:");
        console.error(error.message);

        // Mesmo com erro, transforma o partial em CSV
        try {
            await copyFile(temporaryPath, config.output);

            console.log(`\n⚠️ O CSV foi salvo mesmo com erro!`);
            console.log(`CSV: ${config.output}`);
            console.log(`Produtos/SKUs salvos: ${totalExportados}`);
            console.log(`Backup: ${temporaryPath}`);

        } catch (saveError) {
            console.error(
                "Não foi possível salvar o CSV:",
                saveError.message
            );
        }

        process.exitCode = 1;
    }
}

/*
 * Executa o programa.
 */
main().catch(error => {

    console.error(
        `Erro: ${error.message}`
    )

    process.exitCode = 1
})