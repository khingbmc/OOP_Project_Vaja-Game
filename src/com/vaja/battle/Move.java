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
    	this.minHeal = this.maxHeal = -1;
    	this.crit = crit;
    }
    
    //calculate the true dmg for entity range
    public void setDmg(int damageSeed) {
    	if(this.type == 3) return;
    	
    	//for accurate it is normal attack it has deviate little from avg
    	if(this.type == 0) {
    		this.minDmg = damageSeed - (this.minDmg * (damageSeed / 24));
    		this.maxDmg = damageSeed - (this.maxDmg * (damageSeed / 24));
    	}
    	
    	//for wide it has over area attack it has large diviate from avg
    	else if(this.type == 1) {
    		this.minDmg = damageSeed - (this.minDmg * (damageSeed / 12));
    		this.maxDmg = damageSeed - (this.minDmg * (damageSeed / 12));
    	}
    	
    	//for crit
    	else if(this.type == 2) {
    		this.minDmg = this.maxDmg = damageSeed - (damageSeed / this.minDmg);
    	}
    }
    
    //heal is of entity max hp
    
    public void setHeal(int hpSeed) {
    	if(this.type != 3) return;
    	this.minHeal = (hpSeed / 16)*this.minHeal;
    	this.maxHeal = (hpSeed/16) * this.maxHeal;
    }

}
