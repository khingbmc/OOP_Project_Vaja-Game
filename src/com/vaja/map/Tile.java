package com.vaja.map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.vaja.entity.Entity;

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
	 */
	public static final byte NORMAL = 0, EXTRA = 1;
	
	//each tile is unique id
	public int id;
	public int type;
	
	//position tile
	public Vector2 tilePosition;
	
	//Entity is each tile has contains
	public Entity contain;
	
	//sprite
	public TextureRegion sprite;
	
	public Tile(int id, TextureRegion sprite,Vector2 tilePosition) {
		this.id = id;
		this.sprite = sprite;
		this.tilePosition = tilePosition;
		
		//default tile has no entity
		this.contain = null;
		
		//idetify type of tile
		if(id > 8 && id < 16) type = this.EXTRA;
		else type = this.NORMAL;
	}
	
	
	public void addEntity(Entity e) {
		if(!containEntity()) this.contain = e;
		//is not entity in tile
	}
	
	public boolean containEntity() {
		return this.contain == null;
	}
	
	public boolean isExtra() {
		return this.type == 0;
	}
}
