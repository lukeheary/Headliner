package com.Headliner;

// Creator: Luke Heary
// Date: 6/22/18

import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner in;
        try {
            in = new Scanner(new File("/Users/lukeheary/Documents/TenTrending/src/main/resources/links.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] attributes = line.split(",");

                Crawler crawler = new Crawler(attributes[0], attributes[1]);
                crawler.search();
            }
        } catch (Exception ignored) {
        }
    }

}
