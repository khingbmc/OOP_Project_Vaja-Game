package com.vaja.battle;

/**
 * Class Move is an attack or ability can deal dmg to entity
 */

public class Move {

    public String name;
    public String description;

    //dmg range
    public int minDmg, maxDmg;

    public int dmgSeed;

    public Move(String name, String description, int minDmg, int maxDmg){
        this.name = name;
        this.description = description;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
    }

}
