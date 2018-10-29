package com.vaja.map;
//Generate TileMap

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.vaja.resource.ResourceManage;

public class TileMap {
	//tile
	public int tileSize;
	
	//map
	public String mapInfo;
	public String[] mapInfoLines;
	
	//1d sprite map
	public TextureRegion[] spriteMap;
	
	//array is contain map information
	public Tile[] tileMap;
	public int mapWidth, mapHeight;
	
	public Vector2 origin;
	
	//resource
	private ResourceManage rm;
	
	public TileMap(int tileSize, String path, Vector2 og, ResourceManage rm) {
		this.tileSize = tileSize;
		this.origin = og;
		this.rm = rm;
		
		//read file
		FileHandle file = Gdx.files.internal(path);
		this.mapInfo = file.readString();
		//split String by new Lines
		
		this.mapInfoLines = this.mapInfo.split("\\r?\\n");
		
		this.mapHeight = Integer.parseInt(this.mapInfoLines[1]);
		this.mapWidth= Integer.parseInt(this.mapInfoLines[0]);
		
		this.spriteMap = new TextureRegion[this.mapWidth * this.mapHeight];
		this.tileMap = new Tile[this.mapWidth * this.mapHeight];
		
		
		convert();
		
	}
	/**
	 * Convert lines 2 or n of mapInfo to 1d array of TextureRegion
	 */
	
	private void convert() {
		for(int i=2;i < this.mapHeight + 2;i++) {
			String[] row = this.mapInfoLines[i].split(",");
			for(int j = 0;j < this.mapWidth;j++) {
				int index = Integer.parseInt(row[row.length -1 -j]);
				
				int y = (i - 2) * this.mapWidth + j / this.mapWidth;
				int x = (i - 2) * this.mapWidth + j % this.mapWidth;
				
				int k = (this.mapWidth * this.mapHeight -1) - ((i-2)*this.mapWidth+j);
				
				Tile t = new Tile(index, new Vector2(x, y));
				this.tileMap[k] = t;
				
				int rows = Math.abs(index) / rm.tiles16x16[0].length;
				int column = Math.abs(index) % rm.tiles16x16[0].length;
				;
				
				this.spriteMap[k] = rm.tiles16x16[rows][column];
			}
		}
	}
	
	/**
	 * this method is render the img of map
	 */
	
	public void render(SpriteBatch batch) {
		for (int i= 0;i < this.spriteMap.length;i++) {
			int row = i / this.mapWidth;
			int column = i % this.mapWidth;
			
			batch.draw(spriteMap[i], this.origin.x + column *this.tileSize, 
					this.origin.y+ row * this.tileSize);
			
		}
	}
	
	public void setTile(int tileX, int tileY, Tile tile) {
		this.tileMap[tileX * this.mapWidth + tileY] = tile;
	}
	
	public Tile getTile(int tileX, int tileY) {
		return tileMap[tileX * mapWidth + tileY];
	}
	
	//check tilemap is contain tile?
	
	public boolean mapContain(Tile tile) {
		for(int i = 0;i < tileMap.length;i++) {
			if(this.tileMap[i].equals(tile)) return true;
		}
		return false;
	}
	
	public boolean mapContain(int id) {
		for(int i = 0;i< this.tileMap.length;i++) {
			if(this.tileMap[i].id == id) return true;
		}
		return false;
	}
}
