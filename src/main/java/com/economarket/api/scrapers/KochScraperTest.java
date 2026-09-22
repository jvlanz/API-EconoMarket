package com.economarket.api.scrapers;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class KochScraperTest {

	private static final String OUTPUT_FILE = "produtos-koch.csv";

	private static final Set<String> SEEN_PRODUCTS = new HashSet<>();

	public static void main(String[] args) {

		List<Produto> produtos = new ArrayList<>();

		try (Playwright playwright = Playwright.create()) {

			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

			BrowserContext context = browser.newContext();

			Page page = context.newPage();

			page.navigate(

					"https://www.superkoch.com.br/",

					new Page.NavigateOptions()

							.setWaitUntil(WaitUntilState.DOMCONTENTLOADED));

			page.waitForTimeout(5000);

			page.waitForTimeout(3000);

			System.out.println("Página carregada: " + page.title());

			// ====================================
			// CAPTURA PRODUTOS DA PÁGINA
			// ====================================

			coletarProdutos(page, produtos);

			// ====================================
			// PAGINAÇÃO
			// Ajustar o seletor depois
			// ====================================

			while (true) {

				Locator proximaPagina = page.locator("button[aria-label='Próxima página']");

				if (proximaPagina.count() == 0) {
					break;
				}

				System.out.println("Indo para próxima página...");

				proximaPagina.first().click();

				page.waitForLoadState(LoadState.NETWORKIDLE);

				page.waitForTimeout(2000);

				coletarProdutos(page, produtos);
			}

			exportarCsv(produtos);

			System.out.println("\nTotal exportado: " + produtos.size());

			browser.close();

		} catch (Exception e) {

			System.err.println("Erro durante scraping:");

			e.printStackTrace();
		}
	}

	private static void coletarProdutos(Page page, List<Produto> produtos) {

		try {

			Locator nomes = page.locator("p.line-clamp-3");

			Locator precos = page.locator("p.font-bold");

			int quantidade = Math.min(nomes.count(), precos.count());

			System.out.println("Produtos encontrados: " + quantidade);

			for (int i = 0; i < quantidade; i++) {

				String nome = nomes.nth(i).innerText().trim();

				String precoTexto = precos.nth(i).innerText().trim();

				String sku = gerarSku(nome);

				if (SEEN_PRODUCTS.contains(sku)) {
					continue;
				}

				SEEN_PRODUCTS.add(sku);

				double preco = converterPreco(precoTexto);

				Produto produto = new Produto(sku, nome, preco);

				produtos.add(produto);

				System.out.println(nome + " - " + preco);
			}

		} catch (Exception e) {

			System.err.println("Erro ao coletar página:");

			e.printStackTrace();
		}
	}

	private static double converterPreco(String precoTexto) {

		try {

			String limpo = precoTexto

					.replace("R$", "").replace(".", "").replace(",", ".").trim();

			return Double.parseDouble(limpo);

		} catch (Exception e) {

			return 0.0;
		}
	}

	private static String gerarSku(String nome) {

		return String.valueOf(nome.hashCode());
	}

	private static void exportarCsv(List<Produto> produtos) throws Exception {

		Path output = Paths.get(OUTPUT_FILE);

		try (BufferedWriter writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {

			// BOM UTF-8 para Excel

			writer.write("\uFEFF");

			writer.write("sku_id;nome;preco\n");

			for (Produto produto : produtos) {

				writer.write(produto.getSku() + ";\"" + produto.getNome() + "\";" + produto.getPreco() + "\n");
			}
		}

		System.out.println("CSV salvo em: " + output.toAbsolutePath());
	}
		 
		  static class Produto {
		  
		  private final String sku;
		  
		  private final String nome;
		  
		  private final double preco;
		  
		  public Produto( String sku, String nome, double preco ) {
		  
		  this.sku = sku; this.nome = nome; this.preco = preco; }
		  
		  public String getSku() { return sku; }
		  
		  public String getNome() { return nome; }
		  
		  public double getPreco() { return preco; } }
		 
}