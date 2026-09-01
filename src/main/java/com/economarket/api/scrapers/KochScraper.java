package com.economarket.api.scrapers;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class KochScraper {

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect("https://www.superkoch.com.br/")
                .userAgent("Mozilla/5.0")
                .timeout(30000)
                .get();

        Elements cards = document.select("div.shadow-card-hover");
        System.out.println("oi "+cards);
        for (Element card : cards) {

            String nome = card.select("p.font-semibold").text();
            String preco = card.select("p.font-bold").text();

            System.out.println("Produto: " + nome);
            System.out.println("Preço: " + preco);
            System.out.println("-------------------------");
        }
    }
}