package com.company;

import java.time.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CrazyLogger crazy = new CrazyLogger("tag");
        Clock clock = Clock.system(ZoneId.of("Europe/Moscow"));

        crazy.contains("ПР");
        crazy.of("tag2");

        crazy.log("tag1", "Hellosh");
        crazy.log("tag2", "ПРивет");
        crazy.log("tag3", "shshshshshshshs");

        String logEntry = crazy.search();
        System.out.println("----" + logEntry);
    }
}
