package com.vaja.main;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vaja.resource.ResourceManage;
import com.vaja.screen.GameScreen;

public class Vaja extends Game {

    public static final String TITLE = "Vaja";

    // Desktop screen dimensions
    public static final int V_WIDTH = 160;
    public static final int V_HEIGHT = V_WIDTH / 12 * 9;
    public static final int V_SCALE = 6;

    // Rendering utilities
    public SpriteBatch batch;

    // Resources
    public ResourceManage rm;

	public void create() {
        batch = new SpriteBatch();
        rm = new ResourceManage();

        this.setScreen(new GameScreen(this, rm));
	}

	public void render() {
        super.render();
	}

	public void dispose() {
        batch.dispose();
        super.dispose();
	}

}

