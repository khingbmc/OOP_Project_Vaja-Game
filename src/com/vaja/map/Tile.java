package com.vaja.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.vaja.entity.Entity;

import java.util.Random;

/**
 * this method contail information about each tile (how player interact or what  each tile include )
 * @author khingbmc
 *
 */

public class Tile {
	/**
	 * tile is 2 type
	 * -type Normal: player can pass it
	 * -type Extra:player can't go pass it
	 * -type Change:Player lose movement mad after stepping
	 */
	public static final byte NORMAL = 0, EXTRA = 1, CHANGE = 2;
	
	//each tile is unique id
	public int id;
	public int type;

	private Random rand;
	
	//position tile
	public Vector2 tilePosition;
	
	//Entity is each tile has contains
	public Entity contain;
	
	//sprite
	public TextureRegion sprite;

	public int mag;
	
	public Tile(int id, TextureRegion sprite,Vector2 tilePosition, Random rand) {
		this.id = id;
		this.sprite = sprite;
		this.tilePosition = tilePosition;
		this.rand = rand;
		
		//default tile has no entity
		this.contain = null;
		
		//idetify type of tile\
		if(id == 9) this.type = this.EXTRA;
		else if (id == 5) this.type = this.CHANGE;
		else this.type = this.NORMAL;

		//magOffset can either be -1 or 1
		if(isChange()){
			int r = rand.nextInt(2);
			System.out.println(r);
			if(r == 0) this.mag = 1;
			if(r == 1) this.mag = -1;
		}
	}
	
	
	public void addEntity(Entity e) {
		if(!containEntity()) this.contain = e;
		//is not entity in tile
	}
	
	public boolean containEntity() {
		return this.contain == null;
	}
	
	public boolean isExtra() {
		return this.type == EXTRA;
	}
	public boolean isChange(){
		return type == CHANGE;
	}
	public int getMag(){
		return this.mag;
	}
}
