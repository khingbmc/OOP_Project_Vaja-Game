package com.vaja.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vaja.input.InputManage;
import com.vaja.main.Vaja;
import com.vaja.resource.ResourceManage;

public class AbstractScreen implements Screen {

    protected final Vaja game;
    protected final ResourceManage rm;

    // camera that focuses on the player
    protected OrthographicCamera cam;
    // viewport that keeps aspect ratios of the game when resizing
    protected Viewport viewport;
    // main stage of each screen
    protected Stage stage;

    public AbstractScreen(final Vaja game, final ResourceManage rm) {
        this.game = game;
        this.rm = rm;

        cam = new OrthographicCamera(Vaja.V_WIDTH, Vaja.V_HEIGHT);
        cam.setToOrtho(false);
        // the game will retain it's scaled dimensions regardless of resizing
        viewport = new ExtendViewport(Vaja.V_WIDTH, Vaja.V_HEIGHT, cam);

        stage = new Stage(viewport, game.batch);
    }

    @Override
    public void render(float dt) {}

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return cam;
    }

}