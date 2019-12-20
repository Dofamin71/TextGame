package com.company;

public class Unit extends Creature {
    public int gold;
    public int lvl;     
    public Unit(int hp, int lvl, int gold) {
        this.hp = hp;
        this.lvl = lvl;
        this.gold = gold;
    }
}
