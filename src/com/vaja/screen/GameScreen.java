package com.vaja.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.main.Vaja;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;

/**
 * this method is handle gameplay of user
 * @author khingbmc
 *
 */

public class GameScreen extends AbstractScreen {
	
	private TileMap test;
	
	
	public GameScreen(Vaja game, ResourceManage rm) {
		super(game, rm);
		
		test = new TileMap(16, "res/map/test_map.txt", new Vector2(0, 0), rm);
		
	}
	
	public void update(float delta) {
		cam.update();
	}
	
	public void render(float delta) {
		update(delta);
		
		//clear screen every render
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		test.render(game.batch);
		
		game.batch.end();
	}
	
	
}
