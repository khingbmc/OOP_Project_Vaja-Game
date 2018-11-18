package com.vaja.scene;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.vaja.entity.Player;
import com.vaja.main.Vaja;
import com.vaja.resource.ResourceManage;
import com.vaja.resource.Util;

/**
 * handle input button and everything is not in cam
 * @author khingbmc
 *
 */

public class Hud implements Disposable {

	private Random rand;
	private ResourceManage rm;
	private Player player;
	
	//scene in 2d
	public Stage stage;
	private Viewport viewport;
	/*
	 * Button 
	 * Direction(0-up, 1-down, 2-right, 3-left)
	 * 
	 */
	
	private ImageButton[] dirpad;
	
	//this attribute is random magnitude for each four direction
	private int[] magnitude;
	//label for magnitude
	private Label[] magnitudeLabel;
	
	
	public Hud(Player player,SpriteBatch batch, ResourceManage rm) {
		this.player = player;
		this.rm = rm;
		
		this.rand = new Random();
		//need more pixel in HUD
		viewport = new ExtendViewport(Vaja.V_WIDTH*2, Vaja.V_HEIGHT*2, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		Gdx.input.setInputProcessor(stage);
		
		this.magnitude = new int[4];
		shuffleMag();
		
//		createDirPad();
//		createMagLabel();
	}
	
	/**
	 * Set random magnitude for each direction
	 */
	
	public void shuffleMag() {
		for(int i =0;i<4;i++) {
			//each magnitude is value between 1-4
			this.magnitude[i] = rand.nextInt(4) + 1;
		}
	}
	
	//update number in pad after click down
	
	public void updateMag() {
		for(int i = 0;i<4;i++) {
			this.magnitudeLabel[i].setText(String.valueOf(this.magnitude[i]));
		}
	}
	
	/**
	 * Draw directional pad and apply Drawable effect
	 */
	
	private void createDirPad() {
		this.dirpad = new ImageButton[4];
		
		//when press button it change for visible effect
		ImageButton.ImageButtonStyle[] style = rm.loadImageStyles(4, rm.dir20x20);
		
		
		//up
		dirpad[0] = new ImageButton(style[0]);
		this.dirpad[0].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, (Util.DIR_SIZE * 2) + Util.DIR_OFFSET);
		
		//down
		dirpad[1] = new ImageButton(style[1]);
		this.dirpad[1].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, Util.DIR_OFFSET);
		
		//right
		dirpad[2] = new ImageButton(style[2]);
		this.dirpad[2].setPosition((Util.DIR_SIZE * 2) + Util.DIR_OFFSET, Util.DIR_SIZE + Util.DIR_OFFSET);
		
		//left
		dirpad[3] = new ImageButton(style[3]);
		this.dirpad[3].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, Util.DIR_OFFSET);
		
		handleEvent();
		
		for (int i = 0; i < dirpad.length; i++) {
            stage.addActor(dirpad[i]);
        }
	}
	
	
	//draw label random magnitude in dirpad
	
	public void createMagLabel() {
		this.magnitudeLabel = new Label[4];
		
		BitmapFont bitmapFont = rm.asset.get("arial.ttf", BitmapFont.class);
		Label.LabelStyle font = new Label.LabelStyle(bitmapFont, new Color(0, 0 ,255, 255));
		
		for(int i = 0;i < 4;i++) {
			this.magnitudeLabel[i] = new Label(String.valueOf(this.magnitude[i]), font);
			this.magnitudeLabel[i].setSize(Util.DIR_SIZE, Util.DIR_SIZE);
			this.magnitudeLabel[i].setAlignment(Align.center);
			this.magnitudeLabel[i].setTouchable(Touchable.disabled);
			
		}
		this.magnitudeLabel[0].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, (Util.DIR_SIZE * 2) + Util.DIR_OFFSET);
		this.magnitudeLabel[1].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, Util.DIR_OFFSET);
		this.magnitudeLabel[2].setPosition(Util.DIR_SIZE*2 + Util.DIR_OFFSET, (Util.DIR_SIZE ) + Util.DIR_OFFSET);
		this.magnitudeLabel[3].setPosition(Util.DIR_SIZE, Util.DIR_OFFSET+ Util.DIR_OFFSET);
		
		for(int i=0;i<4;i++) {
			stage.addActor(this.magnitudeLabel[i]);
		}
	}
	
	/**
	 * HANDLE player movement cmd
	 */
	
	public void handleEvent() {
		for(int i = 0;i < 4;i++) {
			final int index = i;
			dirpad[i].addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					movePlayer(index);
				}
			});
		}
	}
	
	private void movePlayer(int dir) {
		if(player.canMove()) {
			player.move(dir, this.magnitude[dir]);
			player.getAm().setAnimation(dir);
			this.shuffleMag();
			this.updateMag();
		}
	}
	
	
	
	
	@Override
	public void dispose() {
		this.stage.dispose();
		
	}

	

}
