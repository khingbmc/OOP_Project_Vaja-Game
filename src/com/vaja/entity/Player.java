package com.vaja.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.animation.AnimationManage;
import com.vaja.input.InputManage;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;
import com.vaja.resource.Util;

public class Player extends Entity{

	private InputManage im;
	
	public Player(String id, Vector2 position, TileMap tileMap, ResourceManage rm) {
		super(id, position, tileMap, rm);
		this.speed = 1;
		
		am = new AnimationManage(rm.sprites, Util.WALKING, Util.WALKING_DELAY);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch, true);
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}
	
	

}
