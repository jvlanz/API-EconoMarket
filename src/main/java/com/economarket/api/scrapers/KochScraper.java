package com.economarket.api.scrapers;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class KochScraper {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            // Abre o Chromium
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                    .setHeadless(false)
            );

            // Cria uma página
            Page page = browser.newPage();

            // Abre o site do Koch
            page.navigate("https://www.superkoch.com.br/");

            // Espera a página carregar
            page.waitForLoadState(LoadState.NETWORKIDLE);

            // Dá alguns segundos para o JavaScript terminar de renderizar
            page.waitForTimeout(3000);

            System.out.println("Página carregada!");
            System.out.println("Título: " + page.title());

            // Localiza os nomes dos produtos
            Locator nomes = page.locator("p.line-clamp-3");

            // Localiza os preços
            Locator precos = page.locator("p.font-bold");

            System.out.println("Nomes encontrados: " + nomes.count());
            System.out.println("Preços encontrados: " + precos.count());

            System.out.println("--------------------------------");

            // Percorre os produtos
            int quantidade = Math.min(nomes.count(), precos.count());

            for (int i = 0; i < quantidade; i++) {

                String nome = nomes.nth(i).innerText().trim();

                String preco = precos.nth(i).innerText().trim();

                System.out.println("Produto: " + nome);
                System.out.println("Preço: " + preco);
                System.out.println("--------------------------------");
            }

            // Mantém o navegador aberto
            System.out.println("Scraping finalizado.");

            page.waitForTimeout(10000);

            browser.close();
        }
    }
}