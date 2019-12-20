package com.company;

public class Trader {
    public Trader() {

    }

    void trade(int gold) {
        System.out.println("\"Лучшие товары по лучшим ценам!\"\n\n" +
                "У тебя есть " + gold + " золотых.\n\n" +
                "1) Зелье здоровья - 100 золотых.\n" +
                "2) Зелье опыта - 200 золотых.\n");
    }
}
