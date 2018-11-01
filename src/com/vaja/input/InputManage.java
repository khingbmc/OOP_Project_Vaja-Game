package com.vaja.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;
import com.vaja.entity.Player;
import com.vaja.resource.Util;

/**
 * handle input Desktop is use left mouse to clicked
 * @author khingbmc
 *
 */

public class InputManage implements InputProcessor{

	private OrthographicCamera cam;
	
	public Vector3 input;
	public boolean drag;
	
	public InputManage(OrthographicCamera cam) {
		this.cam = cam;
		input = new Vector3();
	}
	
	
	/**
	 * 
	 * Return user touched in certain screen
	 * @return boolean
	 */
	
	public boolean clickedAt(int x0, int y0, int x1, int y1) {
		if(input.x >= x0 && input.x <= x1 && input.y >= y0 && input.y <= y1 && Gdx.input.isTouched())
			return true;
		return false;
	}
	
	/**
	 * keypad
	 */
	
	public boolean clickedUp() {
		return clickedAt(Util.DIR_SIZE + Util.DIR_OFFSET,
				Util.DIR_SIZE * 2 + Util.DIR_OFFSET,
				Util.DIR_SIZE*2 + Util.DIR_OFFSET,
				Util.DIR_SIZE * 3 + Util.DIR_OFFSET); // is continue 3 button so multiply 3
	}
	
	public boolean clickedDown() {
		return clickedAt(Util.DIR_SIZE + Util.DIR_OFFSET,
				Util.DIR_OFFSET,
				Util.DIR_SIZE*2 + Util.DIR_OFFSET,
				Util.DIR_SIZE  + Util.DIR_OFFSET);
	}
	
	public boolean clickedLeft() {
		return clickedAt(Util.DIR_OFFSET,
				Util.DIR_SIZE + Util.DIR_OFFSET,
				Util.DIR_SIZE + Util.DIR_OFFSET,
				Util.DIR_SIZE * 2 + Util.DIR_OFFSET);
	}
	
	public boolean clickedRight() {
		return clickedAt(Util.DIR_SIZE * 2 + Util.DIR_OFFSET,
				Util.DIR_SIZE + Util.DIR_OFFSET,
				Util.DIR_SIZE*3 + Util.DIR_OFFSET,
				Util.DIR_SIZE * 2 + Util.DIR_OFFSET);
	}
	
	
	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0) return false;
		this.cam.unproject(this.input.set(screenX, screenY, 0)); //Vector3 3 direction
		this.drag = true;
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(!this.drag) return false;
		this.cam.unproject(this.input.set(screenX, screenY, 0));
		return true;
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//desktop
		this.cam.unproject((this.input.set(screenX, screenY, 0)));
		return true;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0) return false;
		this.cam.unproject(this.input.set(screenX, screenY, 0));
		this.drag = false;
		return true;
	}
	
	
	
	
	
}
