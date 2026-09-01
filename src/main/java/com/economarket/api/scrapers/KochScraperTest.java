package com.economarket.api.scrapers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KochScraperTest {

    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect("https://www.superkoch.com.br/")
                    .userAgent("Mozilla/5.0")
                    .timeout(30000)
                    .get();

            System.out.println("Título: " + document.title());

            // Procura por possíveis cards de produto
            Elements cards = document.select(
                    "div.shadow-card-hover, article, .product-card, .product-item");

            System.out.println("Quantidade de cards: " + cards.size());

            for (Element card : cards) {

                String nome = "";

                Element nomeElement = card.selectFirst(
                        "h2, h3, h4, .product-name, .name");

                if (nomeElement != null) {
                    nome = nomeElement.text();
                }

                String preco = "";

                Element precoElement = card.selectFirst(
                        "[class*=price], [class*=preco]");

                if (precoElement != null) {
                    preco = precoElement.text();
                }

                System.out.println("Nome: " + nome);
                System.out.println("Preço: " + preco);
                System.out.println("--------------------------------");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}