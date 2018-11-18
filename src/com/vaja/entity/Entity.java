package com.vaja.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.animation.AnimationManage;
import com.vaja.battle.Moveset;
import com.vaja.map.Tile;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;

import java.util.Random;

public class Entity {
	protected String id;
	protected ResourceManage rm;
	
	//animation
	protected AnimationManage am;
	//battle scene animation
	protected AnimationManage bam;
	
	protected Vector2 position;

	protected Random rand;
	
	/**
	 * 0 = up 
	 * 1 = down 
	 * 2 = left
	 * 3 = right
	 */
	
	
	protected boolean[] moving;
	protected float speed;
	//Entity current tile
	protected int currentTileX;
	protected int currentTileY;
	//the number of tile in direction move
	protected int numberOfTile;
	
	
	//remove Enitity
	
	protected boolean shouldDestroy;
	protected boolean destroyed;

	//Rpg aspect
	protected int hp;
	protected int maxHp;
	// 0 - 100 in %
	protected int accuracy;
	
	protected int minDmg;
	protected int maxDmg;

	//level inf
	protected int level;
	protected int exp;
	protected int maxExp;
	
	//rpg aspect
	protected Moveset moveset;
	
	//map
	protected TileMap tileMap;
	
	
	
	public Entity(String id,Vector2 position, TileMap tileMap, ResourceManage rm) {
		this.position = position;
		this.id = id;
		this.tileMap = tileMap;
		this.rm = rm;

		rand = new Random();
		
		
		
		
		moving = new boolean[4];
		for (int i =0 ;i<4;i++) moving[i] = false;
		
		
	}
	
	public void update(float delta) {
		if(!this.destroyed) {
			
			//movement
			handle();
			//handle elements of RPG
			if(this.hp > this.maxHp) this.hp = this.maxHp;
			if(this.hp <= 0){
				this.hp = 0;
				//entity die die die
				shouldDestroy = true;
			}

			//animation
			am.update(delta);
		}
	}
	
	/**
	 * move to target position with number of tile in direction
	 * @param direction
	 */
	public void move(int direction, int numberOfTile) {
		this.currentTileX = (int) (position.x / this.tileMap.tileSize);
		this.currentTileY = (int) (position.y / this.tileMap.tileSize);
		this.numberOfTile = numberOfTile;
		this.numberOfTile += adjustFor(direction);
		
		this.moving[direction] = true; //this is attribute is check player to move in one of direction
	}
	
	public void render(SpriteBatch batch, boolean looping) {
		if(!destroyed) {
			batch.draw(am.getKeyFrame(looping), position.x, position.y);
		}
	}
	
	/**
	 * check if 4 direction in array boolen movement is all false 
	 * @return 
	 */
	
	public boolean canMove() {
		for(boolean i : moving) if (i) return false;
		return true;
	}
	
	/**
	 * this method is move entity from current position to target position
	 */
	public int adjust(int direction) {
		switch(direction) {
			case 0://up
				for(int i = this.currentTileY;i <= this.currentTileY+this.numberOfTile;i++) {
					if(tileMap.getTile(currentTileX, i).isExtra() || i >= tileMap.mapHeight - 1) {
						if(i == currentTileY + 1){
							return currentTileY;
						}
						else return i - 1;
					}
					if(tileMap.getTile(this.currentTileX, i).containEntity()){
						return i;
					}
				}
				return currentTileY + this.numberOfTile;
			
			case 1://down
				for(int i = this.currentTileY;i >= this.currentTileY-this.numberOfTile;i--) {
					if(tileMap.getTile(currentTileX, i).isExtra() || i <= 0) {
						if(i == currentTileY - 1) return currentTileY;
						else return i + 1;
					}
					if(tileMap.getTile(this.currentTileX, i).containEntity()){
						return i;
					}
				}
				return currentTileY - this.numberOfTile;
				
			case 2://right
				for(int i = this.currentTileX;i<=this.currentTileX+this.numberOfTile;i++) {
					if(tileMap.getTile(i, currentTileY).isExtra() ||i>=tileMap.mapWidth-1) {
						if(i == currentTileX +1) return currentTileX;
						else return i - 1;
					}
					if(tileMap.getTile(i, this.currentTileY).containEntity()){
						return i;
					}
				}
				return currentTileX + this.numberOfTile;
			case 3://left
				for(int i = this.currentTileX;i >= this.currentTileX - this.numberOfTile;i--) {
					if(tileMap.getTile(i, currentTileY).isExtra() || i <= 0) {
						if (i == currentTileX - 1) return currentTileX;
                        else return i + 1;
					}
					if(tileMap.getTile(i, this.currentTileY).containEntity()){
						return i;
					}
					
				}
				return currentTileX - this.numberOfTile;
		}
		return 0;
		
	}
	
	public void handle() {
		//handle up
		if(this.moving[0]) {
			int targetY = adjust(0);
			if(targetY == this.currentTileY) {
				this.moving[0] = false;
			}else {
				position.y += this.speed;
				 if (position.y >= targetY * tileMap.tileSize) {
	                    position.y = targetY * tileMap.tileSize;
	                    moving[0] = false;
	                }
			}
		}
		//handle dpwn
		else if(this.moving[1]) {
			int targetY = adjust(1);
			if(targetY == this.currentTileY) {
				this.moving[1] = false;
			}else {
				position.y -= this.speed;
				 if (position.y <= targetY * tileMap.tileSize) {
	                    position.y = targetY * tileMap.tileSize;
	                    moving[0] = false;
	                }
			}
		}
		//handle right
		else if(this.moving[2]) {
			int targetX = adjust(2);
			if(targetX == this.currentTileX) {
				this.moving[2] = false;
			}else {
				position.x += this.speed;
				 if (position.x >= targetX * tileMap.tileSize) {
	                    position.x = targetX * tileMap.tileSize;
	                    moving[2] = false;
	                }
			}
		}
		//handle left
		else if(this.moving[3]) {
			int targetX = adjust(3);
			if(targetX == this.currentTileX) {
				this.moving[3] = false;
			}else {
				position.x -= this.speed;
					if (position.x <= targetX * tileMap.tileSize) {
			             position.x = targetX * tileMap.tileSize;
			             moving[3] = false;
			                }
					}
				}
	}

	public int adjustFor(int dir){
		switch(dir){
			case 0://up
				for(int i=currentTileY;i<= currentTileY + this.numberOfTile;i++){
					if(i < tileMap.mapHeight - 1){
						if(tileMap.getTile(currentTileX, i).isChange())
							return tileMap.getTile(currentTileX, i).getMag();
					}
				}
				return 0;

			case 1://down
				for (int i =currentTileY;i >= currentTileY - this.numberOfTile;i--){
					if(i > 0){
						if(tileMap.getTile(currentTileX, i).isChange())
							return tileMap.getTile(currentTileX, i).getMag();
					}
				}
				return 0;
			case 2://right
				for(int i = currentTileX;i <= currentTileX + this.numberOfTile;i++){
					if(i < tileMap.mapWidth - 1){
						if(tileMap.getTile(i, currentTileY).isChange())
							return tileMap.getTile(i, currentTileY).getMag();
					}
				}
				return 0;
			case 3://left
				for(int i = currentTileX;i >= currentTileX - this.numberOfTile;i--){
					if(i > 0){
						if( tileMap.getTile(i, currentTileY).isChange())
							return tileMap.getTile(i, currentTileY).getMag();
					}
				}
				return 0;
		}
		return 0;
	}
	
	
	
	public void setPosition(Vector2 position) {
		this.position = position;
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

	
	public ResourceManage getRm() {
		return rm;
	}
	
	public boolean isMoving(int direction) {
		return this.moving[direction];
	}

	public AnimationManage getAm() {
		return am;
	}

	public Vector2 getPosition(){
		return position;
	}



	public float getSpeed() {
		return speed;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	
	
	public boolean isDestroyed() {
		return this.destroyed;
	}
	
	
	
}

