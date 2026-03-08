package com.dharnish.updatecheck.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebContentFetcher {

    public static String fetch(String url) {

        try {

            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();

            return document.body().text();

        } catch (Exception e) {

            throw new RuntimeException("Failed to fetch website content: " + url, e);

        }
    }
}