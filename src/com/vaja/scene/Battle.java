package com.vaja.scene;

import java.util.Random;

import com.vaja.resource.ResourceManage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vaja.entity.Monster;
import com.vaja.entity.Player;
import com.vaja.main.Vaja;;

/**
 * this class is UI for the battle scene
 * @author khingbmc
 *
 */
public class Battle {

    private Random rand;
    private ResourceManage rm;
    private Player player;
    private Monster monster;

    // Scene2D
    public Stage stage;
    private Viewport viewport;

    public Battle(Player player, Monster enemy, SpriteBatch batch, ResourceManage rm) {
        rand = new Random();

        this.player = player;
        this.monster = enemy;
        this.rm = rm;

        viewport = new ExtendViewport(Vaja.V_WIDTH * 2, Vaja.V_HEIGHT * 2, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Gdx.input.setInputProcessor(stage);
    }

}

