package com.vaja.battle;

/**
 * Class Move is an attack or ability can deal dmg to entity
 */

public class Move {

    public String name;
    public String description;
    
    /*
     * 0 - accurate
     * 1 - wide
     * 2 - critical
     * 3 - healing
     */
    public int type;

    //dmg range
    public int minDmg, maxDmg;

    //healing move
    public int minHeal, maxHeal;
    
    //critical chance
    public int crit;
    
    //this constructor is not for critical type

    public Move(int type, String name, String description, int minDmg, int maxDmg){
        
    	this.type = type;
    	this.name = name;
        this.description = description;
        
        if(type == 0 || type == 1) {
        	this.minDmg = minDmg;
        	this.maxDmg = maxDmg;
        }else {
        	this.minHeal = minDmg;
        	this.maxHeal = maxDmg;
        	this.minDmg = this.maxDmg = this.crit = -1;
        }
    }
    
    //this constructor is for critical type
    public Move(String name, String description, int dmg, int crit) {
    	this.type = 2;
    	this.name = name;
    	this.description = description;
    	
    	this.minDmg = this.maxDmg = dmg;
    	this.crit = crit;
    }
    
    //calculate the true dmg for entity range
    public void setDmg(int minSeed, int maxSeed) {
    	this.minDmg = (this.minDmg * (minSeed / 12))+minSeed;
    	this.maxDmg = (this.maxDmg * (minSeed / 12))+maxSeed;
    }
    
    //heal is 1/4 of entity max hp
    
    public void setHeal(int hpSeed) {
    	this.minHeal += hpSeed/4;
    	this.maxHeal += hpSeed/4;
    }

}
