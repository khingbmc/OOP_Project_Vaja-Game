package com.vaja.map;
//Generate TileMap

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.resource.ResourceManage;

import java.util.Random;
import java.util.Vector;

public class TileMap {
	//tile
	public int tileSize;
	
	//map
	public String mapInfo;
	public String[] mapInfoLines;
	
	
	
	//array is contain map information
	public Tile[] tileMap;
	public int mapWidth, mapHeight;
	public boolean[] collisionMap;
	
	public Vector2 origin;

	private Random rand;
	
	//resource
	private ResourceManage rm;
	
	public TileMap(int tileSize, String path, Vector2 og, ResourceManage rm) {
		this.tileSize = tileSize;
		this.origin = og;
		this.rm = rm;

		rand = new Random();
		
		//read file
		FileHandle file = Gdx.files.internal(path);
		this.mapInfo = file.readString();
		//split String by new Lines
		
		this.mapInfoLines = this.mapInfo.split("\\r?\\n");
		
		this.mapHeight = Integer.parseInt(this.mapInfoLines[1]);
		this.mapWidth= Integer.parseInt(this.mapInfoLines[0]);
		
		
		this.tileMap = new Tile[this.mapWidth * this.mapHeight];
		
		
		convert();
		this.collisionMap = new boolean[mapWidth*mapHeight];
		for(int i = 0;i < collisionMap.length;i++){
			this.collisionMap[i] = tileMap[i].isExtra();
		}
		
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
				
				
				
				int rows = Math.abs(index) / rm.tiles16x16[0].length;
				int column = Math.abs(index) % rm.tiles16x16[0].length;

				
				Tile t = new Tile(index, rm.tiles16x16[rows][column], new Vector2(x, y), rand);
				this.tileMap[k] = t;
			}
		}
	}
	
	/**
	 * this method is render the img of map
	 */
	
	public void render(SpriteBatch batch) {
		for (int i= 0;i < this.tileMap.length;i++) {
			int row = i / this.mapWidth;
			int column = i % this.mapWidth;
			
			batch.draw(this.tileMap[i].sprite, this.origin.x + column *this.tileSize, 
					this.origin.y+ row * this.tileSize);
			
			//draw entity on tile
			if(this.tileMap[i].containEntity()) {
				this.tileMap[i].getEntity().render(batch, true);
			}
			
		}
	}
	
	public void update(float delta) {
		for(int i = 0;i < this.tileMap.length;i++) {
			if(this.tileMap[i].containEntity()) {
				this.tileMap[i].getEntity().update(delta);
			}
		}
	}
	
	public void setTile(int tileX, int tileY, Tile tile) {

		this.tileMap[tileX * this.mapWidth + tileY] = tile;
	}
	
	public Tile getTile(int tileX, int tileY) {

		return tileMap[tileY * mapWidth + tileX];
	}
	
	public Tile getTile(Vector2 coordinate) {
		System.out.println(this.tileMap);
		return this.tileMap[(int) (coordinate.x*this.mapWidth + coordinate.y)];
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
	
	/**
	 * replace tile by id
	 */
	
	public void setTile(int tileX, int tileY, int id) {
		int row = id/rm.tiles16x16[0].length;
		int column = id%rm.tiles16x16.length;
		this.tileMap[tileX * mapWidth + tileY] = new Tile(id, rm.tiles16x16[row][column], new Vector2(tileX, tileY), rand);
		
	}
	
	/**
	 * convert tile to map coordinate
	 */
	
	public Vector2 toCoor(int tileX, int tileY) {
		return new Vector2(tileX*this.tileSize, tileY*this.tileSize);
	}
	
	public Vector2 toCoor(Vector2 coordinate) {
		return new Vector2(coordinate.x*this.tileSize, coordinate.y*this.tileSize);
	}
	
	/**
	 * convert map coordinate to tile
	 */
	
	public Vector2 toTile(int mapX, int mapY) {
		return new Vector2(mapX/this.tileSize, mapY/this.tileSize);
	}
	
	public Vector2 toTile(Vector2 coordinate) {
		return new Vector2(coordinate.x/this.tileSize, coordinate.y/this.tileSize);
	}
}
