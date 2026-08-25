package com.economarket.api.scrapers;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class KochScraper {

    public static void main(String[] args) throws IOException {

        String url = "https://www.superkoch.com.br/";

        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(30_000)
                .get();

        System.out.println(document.title());
    }
}