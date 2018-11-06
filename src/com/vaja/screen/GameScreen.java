package com.vaja.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.vaja.entity.Player;
import com.vaja.main.Vaja;
import com.vaja.map.TileMap;
import com.vaja.resource.ResourceManage;
import com.vaja.scene.Hud;

/**
 * this method is handle gameplay of user
 * @author khingbmc
 *
 */

public class GameScreen extends AbstractScreen {
	
	private TileMap test;
	private Player player;
	private Hud hud;
	
	
	
	public GameScreen(Vaja game, ResourceManage rm) {
		super(game, rm);
		
		test = new TileMap(16, "res/map/test_map.txt", new Vector2(0, 0), rm);
		player = new Player("player", test.toCoor(7, 7), test, rm);	
		hud = new Hud(player, game.batch, rm);
	}
	
	public void update(float delta) {
		//camera follow player
		cam.position.x = player.getPosition().x + 8;
		cam.position.y = player.getPosition().y + 4;
		this.player.update(delta);
		cam.update();
	}
	
	public void render(float delta) {
		update(delta);
		
		//clear screen every render
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		
		test.render(game.batch);
		this.player.render(game.batch);
		
		game.batch.end();
		
		hud.stage.act(delta);
		hud.stage.draw();
	}
	
	public void dispose() {
		super.dispose();
		hud.dispose();
	}
	
	
}
