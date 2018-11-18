package com.vaja.entity;

import com.badlogic.gdx.math.Vector2;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;

/**
 * An Entity is not move but
 * if player walk and stomp them
 * it is battle scene
 * @author khingbmc
 */

public class Monster extends Entity {

    // amount of exp an Enemy will give when defeated
    protected int expDrop;
    // amount of gold an Enemy will drop
    protected int goldDrop;

    public Monster(String id, Vector2 position, TileMap tileMap, ResourceManage rm) {
        super(id, position, tileMap, rm);

        speed = 0;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public int getGoldDrop() {
        return goldDrop;
    }

}

