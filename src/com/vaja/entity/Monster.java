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
    //exp is give when defeated monster
    protected int expDrop;
    //coin is give when defeated monster
    protected int coinDrop;

    public Monster(String id, Vector2 position, TileMap tileMap, ResourceManage rm) {
        super(id, position, tileMap, rm);
    }
}
