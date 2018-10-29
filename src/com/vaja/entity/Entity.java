package com.vaja.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.animation.AnimationManage;
import com.vaja.map.Tile;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;

public class Entity {
	protected String id;
	protected ResourceManage rm;
	
	//animation
	protected AnimationManage am;
	
	/**
	 * 0 = up 
	 * 1 = down 
	 * 2 = left
	 * 3 = right
	 */
	
	protected int dir;
	
	//position x, y in tile
	protected Vector2 position;
	
	//the target position
	protected Vector2 target;
	
	/**
	 * 0 = idle
	 * 1 = up
	 * 2 = down
	 * 3 = right
	 * 4 = left
	 */
	protected int movement;
	protected boolean moving;
	protected float speed;
	
	//remove Enitity
	
	protected boolean shouldDestroy;
	protected boolean destroyed;
	
	//map
	protected TileMap tileMap;
	
	public Entity(Vector2 position, TileMap tileMap, ResourceManage rm) {
		this.position = position;
		this.tileMap = tileMap;
		this.rm = rm;
		
		this.shouldDestroy = this.destroyed = false;
		
	}
	
	public void update(float delta) {
		if(!this.destroyed) {
			move();
			am.update(delta);
		}
	}
	
	/**
	 * this input is process one of 4 direction
	 * @param movement
	 */
	public void setMovement(int movement) {
		if(this.moving) return;
		this.movement = movement;
		this.moving = canMove();
	}
	
	public void render(SpriteBatch batch, boolean looping) {
		if(!destroyed) {
			batch.draw(this.am.getKeyFrame(looping),
					this.position.x *this.tileMap.tileSize - this.am.width / 2,
					this.position.y * this.tileMap.tileSize - this.am.height / 2);
		}
	}
	
	//check entity is able to move dest position
	
	public boolean canMove() {
		if(this.moving) return true;
		
		//if player want to move in position out of map or extra tile: moving = 0
		switch(this.movement) {
		//up
		case 1:
			if(this.position.y + 1 == this.tileMap.mapHeight || this.tileMap.getTile((int) this.position.x ,(int) this.position.y +1).type == Tile.EXTRA)
			return false;
			else this.target.y -= 1;
			break;
		//down
		case 2:
			if(this.position.y - 1 ==  -1 || this.tileMap.getTile((int) this.position.x, (int) this.position.y -1).type == Tile.EXTRA) return false;
			else
				this.target.y -=1 ;
			break;
		
		//right
		case 3:
			if(this.position.x+1 == this.tileMap.mapWidth || this.tileMap.getTile((int) this.position.x + 1, (int) this.position.y).type == Tile.EXTRA) return false;
			else
				this.target.x += 1;
			break;
		
		//left
		case 4:
			if(this.position.x -1 == -1 || this.tileMap.getTile((int) this.position.x -1, (int) this.position.y).type == Tile.EXTRA) return false;
			else
				this.target.x -= 1;
			break;
		
			
		}
		return true;
	}
	
	/**
	 * this method is move entity from current position to target position
	 */
	public void move() {
		if(this.position.x == this.target.x && this.position.y == this.target.y) this.movement = 0;
		
		//up
		if(this.movement == 1 && this.position.y < this.target.y) this.position.y +=this.speed;
		else this.movement = 0;
		if(this.movement == 1 && this.position.y > this.target.y) this.position.y = this.target.y;
		
		//down
		if(this.movement == 2 && this.position.y > this.target.y) this.position.y -= this.speed;
		else this.movement = 0;
		if(this.movement == 2 && this.position.y < this.target.y) this.position.y = this.target.y;
		
		//right
		if(this.movement == 3 && this.position.x < this.target.x) this.position.x += this.speed;
		else this.movement = 0;
		if(this.movement == 3 && this.position.x > this.target.x) this.position.x = this.target.x;
		
		//left
		if(this.movement == 4 && this.position.x > this.target.x) this.position.x -= this.speed;
		else this.movement = 0;
		if(this.movement == 4 && this.position.x < this.target.x) this.position.x = this.target.x;
		
		
	}
	
	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setTarget(Vector2 target) {
		this.target = target;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isShouldDestroy() {
		return shouldDestroy;
	}

	public void setShouldDestroy(boolean shouldDestroy) {
		this.shouldDestroy = shouldDestroy;
	}

	public String getId() {
		return id;
	}

	public int getDir() {
		return dir;
	}

	public Vector2 getTarget() {
		return target;
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	public boolean isDestroyed() {
		return this.destroyed;
	}
	
	
	
}
