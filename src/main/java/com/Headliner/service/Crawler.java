package com.Headliner.service;

// Creator: Luke Heary
// Date: 6/22/18

import lombok.AllArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class Crawler {

    private String cssTag;

    private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Set<String> headlines = new HashSet<String>();

    public Set<String> search(String link, String cssTag) {
        this.cssTag = cssTag;

        while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
            String currentLink;

            if (this.pagesToVisit.isEmpty()) {
                currentLink = link;
                this.pagesVisited.add(link);
            } else {
                currentLink = this.nextLink();
            }

            this.headlines.addAll(look(currentLink));
            this.pagesToVisit.addAll(getLinks());
        }
        System.out.println(String.format("Finished exploring %s web pages\n", this.pagesVisited.size()));
        for (String headline : headlines) {
            System.out.println(headline);
        }

        return headlines;
    }

    private Set<String> look(String currentLink) {
        try {
            Connection connection = Jsoup.connect(currentLink).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();

            if(connection.response().statusCode() == 200) {
                System.out.println("Exploring " + currentLink);
            }

            for (Element headline : htmlDocument.select(cssTag)) {
                if(headline.text().split(" ").length > 3) {
                    headlines.add(headline.text());
                }
            }

            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  headlines;
    }

    private String nextLink() {
        String nextLink;

        do {
            nextLink = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(nextLink));

        this.pagesVisited.add(nextLink);
        return nextLink;
    }

    private List<String> getLinks() {
        return this.links;
    }
}
