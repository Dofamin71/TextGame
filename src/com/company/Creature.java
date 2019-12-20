package com.company;

public abstract class Creature {
    public int hp;
    public int exp;

//    public Creature(int hp, int lvl, int damage) {
//        this.hp = hp;
//        this.lvl = lvl;
//        this.damage = damage;
//    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    public int getExp() {
        return exp;
    }
}
