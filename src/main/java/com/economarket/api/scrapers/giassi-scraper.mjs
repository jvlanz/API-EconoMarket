#!/usr/bin/env node

/**
 * Exporta o catálogo público da Giassi (VTEX) em CSV.
 *
 * Requer Node.js 18+
 * Não usa dependências externas.
 */

import {
    appendFile,
    copyFile,
    mkdir,
    rename,
    writeFile
} from 'node:fs/promises';

import {
    dirname,
    resolve
} from 'node:path';


// =====================================================
// CONFIGURAÇÕES
// =====================================================

const BASE_URL =
    'https://www.giassi.com.br/api/catalog_system/pub/products/search';

const DEFAULT_BATCH_SIZE = 50;


// =====================================================
// CONTROLE DE SKUS DUPLICADOS
// =====================================================

const seenSkus = new Set();


// =====================================================
// AJUDA
// =====================================================

function usage() {

    console.log(`

Uso:

  node giassi-scraper.mjs [opções]


Opções:

  --output <arquivo>   CSV de saída
                        padrão: produtos-giassi.csv

  --limit <n>          Limita a quantidade de SKUs

  --pause <ms>         Pausa entre requisições
                        padrão: 800

  --cookie <valor>     Cookie de uma sessão autenticada
                        ou use GIASSI_COOKIE

  --help               Exibe esta ajuda


Exemplo:

  node giassi-scraper.mjs --output produtos.csv --limit 100

`);
}


// =====================================================
// ARGUMENTOS
// =====================================================

function parseArgs(args) {

    const config = {

        output: 'produtos-giassi.csv',

        limit: Infinity,

        pause: 800,

        cookie: process.env.GIASSI_COOKIE
    };


    for (let i = 0; i < args.length; i += 1) {

        const arg = args[i];


        if (arg === '--help') {

            return {
                help: true
            };
        }


        if (
            ![
                '--output',
                '--limit',
                '--pause',
                '--cookie'
            ].includes(arg)
        ) {

            throw new Error(
                `Opção desconhecida: ${arg}`
            );
        }


        const value = args[++i];


        if (!value || value.startsWith('--')) {

            throw new Error(
                `Falta o valor de ${arg}`
            );
        }


        if (arg === '--output') {

            config.output = value;
        }


        if (arg === '--limit') {

            config.limit =
                parsePositiveInteger(
                    value,
                    '--limit'
                );
        }


        if (arg === '--pause') {

            config.pause =
                parseNonNegativeInteger(
                    value,
                    '--pause'
                );
        }


        if (arg === '--cookie') {

            config.cookie = value;
        }
    }


    return config;
}


// =====================================================
// VALIDAÇÃO DE INTEIRO POSITIVO
// =====================================================

function parsePositiveInteger(
    value,
    option
) {

    const number = Number(value);


    if (
        !Number.isInteger(number) ||
        number <= 0
    ) {

        throw new Error(
            `${option} deve ser um inteiro positivo.`
        );
    }


    return number;
}


// =====================================================
// VALIDAÇÃO DE INTEIRO NÃO NEGATIVO
// =====================================================

function parseNonNegativeInteger(
    value,
    option
) {

    const number = Number(value);


    if (
        !Number.isInteger(number) ||
        number < 0
    ) {

        throw new Error(
            `${option} deve ser um inteiro maior ou igual a zero.`
        );
    }


    return number;
}


// =====================================================
// ESPERA
// =====================================================

function sleep(ms) {

    return new Promise(
        resolve => setTimeout(resolve, ms)
    );
}


// =====================================================
// BUSCA UMA PÁGINA DA API VTEX
// =====================================================

async function getPage(
    from,
    to,
    cookie
) {

    const url = new URL(BASE_URL);


    url.searchParams.set(
        '_from',
        String(from)
    );


    url.searchParams.set(
        '_to',
        String(to)
    );


    const headers = {

        Accept: 'application/json',

        'User-Agent':
            'giassi-catalog-export/1.0'
    };


    if (cookie) {

        headers.Cookie = cookie;
    }


    let lastError;


    // Faz até 4 tentativas
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

                    signal:
                        AbortSignal.timeout(30000)
                }
            );


            // =========================================
            // SUCESSO
            // =========================================

            if (response.ok) {

                const payload =
                    await response.json();


                if (!Array.isArray(payload)) {

                    throw new Error(
                        'A API respondeu em um formato inesperado.'
                    );
                }


                return payload;
            }


            // =========================================
            // ERROS QUE PODEM SER TEMPORÁRIOS
            // =========================================

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
                );
            }


            lastError =
                new Error(
                    `HTTP ${response.status}`
                );

        } catch (error) {

            lastError = error;
        }


        console.log(
            `Tentativa ${attempt + 1}/4 falhou para ${from}-${to}.`
        );


        await sleep(
            1000 * (attempt + 1)
        );
    }


    throw lastError;
}


// =====================================================
// FALLBACK
// =====================================================

/**
 * Tenta buscar uma página.
 *
 * Se a API retornar erro,
 * divide o intervalo em duas partes.
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
        );

    } catch (error) {

        // =========================================
        // NÃO TEM MAIS COMO DIVIDIR
        // =========================================

        if (from === to) {

            throw error;
        }


        // =========================================
        // DIVIDE O INTERVALO
        // =========================================

        const middle =
            Math.floor(
                (from + to) / 2
            );


        console.log(
            `Falha em ${from}-${to}. ` +
            `Dividindo em ${from}-${middle} ` +
            `e ${middle + 1}-${to}.`
        );


        const firstHalf =
            await getPageWithFallback(
                from,
                middle,
                cookie
            );


        const secondHalf =
            await getPageWithFallback(
                middle + 1,
                to,
                cookie
            );


        return [
            ...firstHalf,
            ...secondHalf
        ];
    }
}


// =====================================================
// PREPARAR VALOR PARA CSV
// =====================================================

function csv(value) {

    const text =
        value === null ||
        value === undefined
            ? ''
            : String(value);


    return `"${text.replaceAll('"', '""')}"`;
}


// =====================================================
// TRANSFORMAR PRODUTOS EM LINHAS
// =====================================================

function rowsFromProducts(products) {

    const rows = [];


    for (const product of products) {

        for (
            const item of product.items ?? []
        ) {

            // =========================================
            // PROCURA O VENDEDOR PADRÃO
            // =========================================

            const offer =
                item.sellers?.find(
                    seller =>
                        seller.sellerDefault
                )?.commertialOffer

                ??

                item.sellers?.[0]?.commertialOffer

                ??

                {};


            rows.push({

                sku_id:
                    item.itemId,

                nome:
                    item.nameComplete ||
                    item.name ||
                    product.productName,

                preco:
                    offer.Price ?? '',

                unidade:
                    item.measurementUnit ?? '',

                multiplicador:
                    item.unitMultiplier ?? ''
            });
        }
    }


    return rows;
}


// =====================================================
// COLUNAS DO CSV
// =====================================================

const columns = [

    'sku_id',

    'nome',

    'preco',

    'unidade',

    'multiplicador'
];


// =====================================================
// MAIN
// =====================================================

async function main() {

    // =========================================
    // LÊ OS ARGUMENTOS
    // =========================================

    const config =
        parseArgs(
            process.argv.slice(2)
        );


    // =========================================
    // HELP
    // =========================================

    if (config.help) {

        usage();

        return;
    }


    // =========================================
    // CAMINHOS
    // =========================================

    const outputPath =
        resolve(config.output);


    const temporaryPath =
        `${outputPath}.partial`;


    // Cria a pasta de saída
    await mkdir(
        dirname(outputPath),
        {
            recursive: true
        }
    );


    let totalExportados = 0;


    try {

        // =========================================
        // CRIA O PARTIAL
        // =========================================

        await writeFile(

            temporaryPath,

            `${columns.join(',')}\n`,

            'utf8'
        );


        let from = 0;


        // =========================================
        // LOOP PRINCIPAL
        // =========================================

        while (true) {

            const to =
                from +
                DEFAULT_BATCH_SIZE -
                1;


            console.log(
                `\nBuscando produtos ${from}-${to}...`
            );


            // =========================================
            // BUSCA OS PRODUTOS
            // =========================================

            const products =
                await getPageWithFallback(

                    from,

                    to,

                    config.cookie
                );


            // =========================================
            // NÃO HÁ MAIS PRODUTOS
            // =========================================

            if (
                products.length === 0
            ) {

                break;
            }


            // =========================================
            // TRANSFORMA EM LINHAS
            // =========================================

            const rows =
                rowsFromProducts(
                    products
                );


            // =========================================
            // REMOVE DUPLICADOS
            // =========================================

            const novasRows =
                rows.filter(

                    row =>
                        !seenSkus.has(
                            row.sku_id
                        )
                );


            // =========================================
            // REGISTRA OS SKUS
            // =========================================

            for (
                const row of novasRows
            ) {

                seenSkus.add(
                    row.sku_id
                );
            }


            // =========================================
            // SALVA NO PARTIAL
            // =========================================

            if (
                novasRows.length > 0
            ) {

                const csvContent =
                    novasRows

                        .map(

                            row =>
                                [

                                    csv(row.sku_id),

                                    csv(row.nome),

                                    csv(row.preco),

                                    csv(row.unidade),

                                    csv(row.multiplicador)

                                ].join(',')
                        )

                        .join('\n');


                await appendFile(

                    temporaryPath,

                    csvContent + '\n',

                    'utf8'
                );
            }


            // =========================================
            // CONTADOR
            // =========================================

            totalExportados +=
                novasRows.length;


            console.log(

                `Página ${from}-${to}: ` +

                `${products.length} produtos; ` +

                `${novasRows.length} SKUs exportados.`
            );


            // =========================================
            // VERIFICA LIMITE
            // =========================================

            if (
                totalExportados >=
                config.limit
            ) {

                console.log(
                    `\nLimite de ${config.limit} SKUs atingido.`
                );

                break;
            }


            // =========================================
            // PRÓXIMA PÁGINA
            // =========================================

            from +=
                DEFAULT_BATCH_SIZE;


            // =========================================
            // PAUSA
            // =========================================

            await sleep(
                config.pause
            );
        }


        // =========================================
        // TERMINOU NORMALMENTE
        // =========================================

        await rename(

            temporaryPath,

            outputPath
        );


        console.log(
            '\n================================='
        );

        console.log(
            'SCRAPING CONCLUÍDO'
        );

        console.log(
            '================================='
        );

        console.log(
            `CSV salvo em: ${outputPath}`
        );

        console.log(
            `Total exportado: ${totalExportados}`
        );

    } catch (error) {

        // =========================================
        // ERRO DURANTE O SCRAPING
        // =========================================

        console.error(
            '\n================================='
        );

        console.error(
            'ERRO DURANTE O SCRAPING'
        );

        console.error(
            '================================='
        );

        console.error(
            error.message
        );


        // =========================================
        // TRANSFORMA PARTIAL EM CSV
        // =========================================

        try {

            await copyFile(

                temporaryPath,

                outputPath
            );


            console.log(
                '\n⚠️ Ocorreu um erro, ' +
                'mas os dados foram salvos!'
            );


            console.log(
                `CSV: ${outputPath}`
            );


            console.log(
                `Backup partial: ${temporaryPath}`
            );


            console.log(
                `Total coletado: ${totalExportados}`
            );

        } catch (saveError) {

            console.error(
                '\n❌ Não foi possível criar o CSV.'
            );


            console.error(
                saveError.message
            );
        }


        process.exitCode = 1;
    }
}


// =====================================================
// EXECUTA
// =====================================================

main().catch(error => {

    console.error(
        `Erro fatal: ${error.message}`
    );

    process.exitCode = 1;
});