package com.vaja.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.animation.AnimationManage;
import com.vaja.battle.Moveset;
import com.vaja.input.InputManage;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;
import com.vaja.resource.Util;

public class Player extends Entity{
	
	//Battle
	private Monster opponent;
	private boolean fighting = false;


	
	public Player(String id, Vector2 position, TileMap tileMap, ResourceManage rm) {
		super(id, position, tileMap, rm);
		
		this.opponent = null;
		
		//attribute of player
		this.hp = this.maxHp = 60;
		this.accuracy = 85;
		this.minDmg = 12;
		this.maxDmg = 18;
		
		this.speed = 1;
		
		am = new AnimationManage(rm.sprites16x16, Util.WALKING, Util.WALKING_DELAY);
	
		this.moveset = new Moveset(rm);
		//seed of dmg is random between range of dmg
		this.moveset.reset(this.rand.nextInt((this.maxDmg - this.minDmg)+1)+this.minDmg, this.maxHp);
		
	}
	
	public void update(float delta) {
		super.update(delta);
		if(canMove()) am.stopAnimation();
		
		//check entity interact
		System.out.println(this.tileMap.toCoor(this.position));
		if(this.tileMap.getTile(this.tileMap.toCoor(this.position)).containEntity()) {
			//type cast because it return entity is super class of monster
			this.opponent = (Monster) this.tileMap.getTile(this.tileMap.toCoor(this.position)).getEntity();
			this.fighting = true;
		}
		
	}
	
	public void render(SpriteBatch batch) {
		if(!this.destroyed) {
			batch.draw(this.am.getKeyFrame(true), this.position.x + 1, this.position.y);
		}
	}
	
	public Monster getOpponent() {
		return opponent;
		
	}
	public void finishFighting() {
		this.fighting = false;
		this.opponent = null;
	}
	
	public boolean isFighting() {
		return this.fighting;
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}
	
	

}
