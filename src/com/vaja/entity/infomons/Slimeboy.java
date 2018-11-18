package com.vaja.entity.infomons;

import com.badlogic.gdx.math.Vector2;
import com.vaja.animation.AnimationManage;
import com.vaja.entity.Monster;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;

/**
 * A super Slime boy in the world(it so very noob)
 * @author khingbmc
 *
 */

public class Slimeboy extends Monster {

    public Slimeboy(String id, Vector2 position, TileMap tileMap, ResourceManage rm) {
        super(id, position, tileMap, rm);

        am = new AnimationManage(rm.sprites16x16, 2, 2, 1 / 3f);
    }
}
