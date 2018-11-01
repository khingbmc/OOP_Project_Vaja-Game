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

	protected Vaja game;
	protected ResourceManage rm;
	protected InputManage im;
	
	//camera use to focus screen
	protected OrthographicCamera cam;
	//viewpost is aspect to ratio of screen if user resizing screen
	protected Viewport viewPort;
	//stage
	protected Stage stage;
	
	public AbstractScreen(Vaja game, ResourceManage rm) {
		this.game = game;
		this.rm = rm;
		
		cam = new OrthographicCamera(Vaja.V_WIDTH, Vaja.V_HEIGHT);
		cam.setToOrtho(false);
		
		im = new InputManage(cam);
		Gdx.input.setInputProcessor(im);
		
		viewPort = new ExtendViewport(Vaja.V_WIDTH, Vaja.V_HEIGHT, cam);
		
		this.stage = new Stage(this.viewPort, game.batch);
	}

	@Override
	public void dispose() {
		stage.dispose();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		
		
	}

	@Override
	public void resize(int w, int h) {
		this.viewPort.update(w, h);
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public Stage getStage() {
		return stage;
	}
	

}
