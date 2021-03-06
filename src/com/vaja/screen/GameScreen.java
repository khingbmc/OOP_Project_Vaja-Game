package com.vaja.screen;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import com.vaja.entity.Player;
import com.vaja.entity.infomons.Slimeboy;
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

    private TileMap map;
    private Player player;
    private Hud hud;

    public GameScreen(final Vaja game, final ResourceManage rm) {
        super(game, rm);

        map = new TileMap(16, "res/map/test_map.txt", new Vector2(0, 0), rm);
        player = new Player("player", map.toMapCoords(5, 8), map, rm);
        hud = new Hud(player, game.batch, rm);

        map.getTile(4, 4).addEntity(new Slimeboy("slime_boy", map.toMapCoords(4, 4), map, rm));
    }

    public void update(float dt) {
        // camera directs on the player
        cam.position.x = player.getPosition().x + 8;
        cam.position.y = player.getPosition().y + 4;

        player.update(dt);
        map.update(dt);

        cam.update();
    }

    public void render(float dt) {
        update(dt);

        // clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();

        map.render(game.batch);
        player.render(game.batch);

        game.batch.end();

        hud.stage.act(dt);
        hud.stage.draw();
    }

    public void dispose() {
        super.dispose();
        hud.dispose();
    }

}

