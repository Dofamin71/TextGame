package com.company;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nПравила игры очень просты. Для определения развития событий тебе нужно кинуть два кубика D6 и записать выпавший результат без пробела.\n" +
                "Звучит непонятно, поэтому я объясню на примере. Вот тебе выпало последовательно 1 и 5. Ты пишешь 15. Все просто)\n\n" +
                "|__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__|\n\n" +
                "Для начала тебе нужно выбрать персонажа (класс, жизни, уровень, золото):\n\n" +
                "1) Ураг гро-Шуб (маг, 5, 4, 70)\n" +
                "Ураг гро-Шуб — орк-маг, что редкость для орков.\n" +
                "Ураг является библиотекарем Арканеума в Коллегии Винтерхолда.\n" +
                "Коллекционирует редкие книги и торгует менее ценными.\n\n" +
                "2) Синмир (воин, 10, 1, 10)\n" +
                "Синмир - норд и постоянный жилец в \"Гарцующей кобыле\" в Вайтране.\n" +
                "Он неравнодушен к бутылке и часто комментирует отсутствие безопасности в Вайтране\n\n" +
                "3) Карлия (вор, 7, 1, 100)\n" +
                "Карлия — данмерка, член Гильдии воров.\n" +
                "Изгнана из организации после обвинения в убийстве Галла Дезидения.\n" +
                "Была возлюбленной Галла.\n\n" +
                "|__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__|\n");
        Unit unit = new Unit(0, 0, 0);
        switch (scan.nextInt()) {
            case 1:
                unit = new Wizard(5, 4, 70);
                System.out.println("Со стороны библиотеки раздался звон металла и крик: \"За орду!!!\" Что бы это могло значить?..\n");
                break;
            case 2:
                unit = new Warrior(10, 1, 10);
                System.out.println("\"Охрана в Вайтране ужасна. Просто курам на смех.\" - сказал Синмир, допивая очередную пинту эля.\n");
                break;
            case 3:
                unit = new Thief(7, 1, 100);
                System.out.println("\"Мы связаны с нею узами делового соглашения...\" Что тут сказать... Соловьи, они такие.\n");
                break;
        }
        System.out.println("Помни. Ты в любой момент можешь заглянуть к торговцу. Просто введи 228.\n" +
                "Ты также можешь посмотреть свои характеристики. Для этого введи любое число меньше 11 или больше 66 (кроме 228, разумеется).\n\n" +
                "|__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__|\n");
        Events event = new Events();
        Trader trader = new Trader();
        int d6;
        while (unit.lvl < 50 && unit.hp > 0) {
            System.out.println("Посмотрим, что же выпало на кубах?1\n");
            int choice = scan.nextInt();
            if ((choice < 11 || choice > 66) && choice != 228) {
                System.out.println("Жизни - " + unit.hp + ".\n" +
                        "Уровень - " + unit.lvl + ".\n" +
                        "Золото - " + unit.gold + "\n");
            } else if (choice == 228) {
                trader.trade(unit.gold);
                choice = scan.nextInt();
                if (choice == 1 && unit.gold > 100) {
                    unit.gold -= 100;
                    unit.hp += 1;
                } else if (choice == 2 && unit.gold > 200) {
                    unit.gold -= 200;
                    unit.lvl += 1;
                }
            } else if (choice == 11 || choice == 66) {
                unit.hp -= 1;
                event.kill(unit.hp);
            } else if (choice == 21 || choice == 41 || choice == 61) {
                event.theft(unit.gold);
                if (unit.gold > 30) {
                    unit.gold -= 30;
                } else {
                    unit.hp -=1;
                }
            } else if (choice == 14 || choice == 23 || choice == 34 || choice == 43 || choice == 54 || choice == 63) {
                event.nothing();
            } else if (choice == 13 || choice == 15 || choice == 31 || choice == 33 || choice == 35 || choice == 51 || choice == 53 || choice == 55) {
                event.easy(unit.hp);
                unit.lvl += 1;
            } else if (choice == 22 || choice == 24 || choice == 26 || choice == 42 || choice == 44 || choice == 46 || choice == 62 || choice == 64) {
                event.hard(unit.hp);
                d6 = scan.nextInt();
                int gold = (int) ((Math.random() * 10 * unit.lvl) / 3);
                event.d6(d6, gold);
                if (d6 % 2 == 0) {
                    unit.gold += gold;
                    unit.lvl += 2;
                } else {
                    unit.hp -= 1;
                    unit.gold += gold;
                    unit.lvl += 3;
                }
            } else {
                int gold = (int) ((Math.random() * 10 * unit.lvl) / 3);
                unit.gold += gold;
                event.gold(gold);
            }
            System.out.println("|__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__|\n");
        }
        if (unit.hp == 0) {
            System.out.println("ДОСТОЙНЫЕ ВОЙНЫ УМИРАЮТ ДОСТОЙНО...\n" +
                    "Не отчаивайся! Ты можешь попробовать еще раз ;)");
        }
        if (unit.lvl >= 50) {
            System.out.println("ИСТИННЫЕ ВОЙНЫ НИКОГДА НЕ ПРОИГРЫВАЮТ.\n" +
                    "Мои поздравления! Ты прошел этот непростой квест!\n\n" +
                    "|__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__--__|");
        }
    }
}