package com.Headliner.service;

// Creator: Luke Heary
// Date: 6/22/18

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;

@Component
public class Initializer {

    @Autowired
    private Crawler crawler;

    public void init() {

        Scanner in;
        try {
            in = new Scanner(new File("/Users/lukeheary/IdeaProjects/Headliner/src/main/resources/links.txt"));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] attributes = line.split(",");

                Set<String> headlines = crawler.search(attributes[0], attributes[1]);
                System.out.println("here");

            }
        } catch (Exception ignored) {  }
    }
}
