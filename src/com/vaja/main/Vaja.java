package com.vaja.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vaja.screen.GameScreen;

public class Vaja extends ApplicationAdapter {
	public static final String TITLE = "VAJA";
	
	//Screen Dimensions
	public static final int V_WIDTH = 160;
	public static final int V_HEIGHT = V_WIDTH / 12 *9;
	public static final int V_SCALE = 5;
	
	//batch
	public SpriteBatch batch;
	
	public void create() {
		batch = new SpriteBatch();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	
}
