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
import com.vaja.animation.AnimationManage;
import com.vaja.entity.Player;
import com.vaja.main.Vaja;
import com.vaja.resource.ResourceManage;
import com.vaja.resource.Util;

/**
 * handle input button and everything is not in cam
 * @author khingbmc
 *
 */

public class Hud implements Disposable , KeyListener{

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
	
	private ImageButton[] button;
	
	//this attribute is random magnitude for each four direction
	private int[] magnitude;
	//label for magnitude
	private Label[] magnitudeLabel;
	
	
	public Hud(Player player,SpriteBatch batch, ResourceManage rm) {
		this.player = player;
		this.rm = rm;
		
		this.rand = new Random();
		
		viewport = new ExtendViewport(Vaja.V_WIDTH, Vaja.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		Gdx.input.setInputProcessor(stage);
		
		this.magnitude = new int[4];
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
		this.button = new ImageButton[4];
		
		//when press button it change for visible effect
		TextureRegionDrawable upUp = new TextureRegionDrawable(rm.dir20x20[0][0]);
		TextureRegionDrawable upDown = new TextureRegionDrawable(rm.dir20x20[1][0]);
		TextureRegionDrawable downUp = new TextureRegionDrawable(rm.dir20x20[0][1]);
		TextureRegionDrawable downDown = new TextureRegionDrawable(rm.dir20x20[1][1]);
		TextureRegionDrawable rightUp = new TextureRegionDrawable(rm.dir20x20[0][2]);
		TextureRegionDrawable rightDown = new TextureRegionDrawable(rm.dir20x20[1][2]);
		TextureRegionDrawable leftUp = new TextureRegionDrawable(rm.dir20x20[0][3]);
		TextureRegionDrawable leftDown = new TextureRegionDrawable(rm.dir20x20[1][3]);
		
		ImageButton.ImageButtonStyle upStyle = new ImageButton.ImageButtonStyle();
		upStyle.imageUp = upUp;
		upStyle.imageDown = upDown;
		ImageButton.ImageButtonStyle downStyle = new ImageButton.ImageButtonStyle();
		downStyle.imageUp = downUp;
		downStyle.imageDown = downDown;
		ImageButton.ImageButtonStyle rightStyle = new ImageButton.ImageButtonStyle();
		rightStyle.imageUp = rightUp;
		rightStyle.imageDown = rightDown;
		ImageButton.ImageButtonStyle leftStyle = new ImageButton.ImageButtonStyle();
		leftStyle.imageUp = leftUp;
		leftStyle.imageDown = leftDown;
		
		
		//up
		this.button[0] = new ImageButton(upStyle);
		this.button[0].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, (Util.DIR_SIZE * 2) + Util.DIR_OFFSET);
		
		//down
		this.button[1] = new ImageButton(downStyle);
		this.button[1].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, Util.DIR_OFFSET);
		
		//right
		this.button[2] = new ImageButton(rightStyle);
		this.button[2].setPosition((Util.DIR_SIZE * 2) + Util.DIR_OFFSET, Util.DIR_SIZE + Util.DIR_OFFSET);
		
		//left
		this.button[3] = new ImageButton(leftStyle);
		this.button[3].setPosition(Util.DIR_SIZE + Util.DIR_OFFSET, Util.DIR_OFFSET);
		
		handleEvent();
		
		for (int i = 0; i < button.length; i++) {
            stage.addActor(button[i]);
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
		button[0].addListener(new ClickListener()  {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(player.canMove()) {
					player.move(0, magnitude[0]);
					player.getAm().setAnimation(AnimationManage.UP);
					shuffleMag();
					updateMag();
				}
			}
			
		});
		button[1].addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(player.canMove()) {
					player.move(1, magnitude[1]);
					player.getAm().setAnimation(AnimationManage.DOWN);
					shuffleMag();
					updateMag();
				}
			}
			
		});
		
		button[2].addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(player.canMove()) {
					player.move(2, magnitude[2]);
					player.getAm().setAnimation(AnimationManage.RIGHT);
					shuffleMag();
					updateMag();
				}
			}
			
		});
		
		button[3].addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(player.canMove()) {
					player.move(3, magnitude[3]);
					player.getAm().setAnimation(AnimationManage.LEFT);
					shuffleMag();
					updateMag();
				}
			}
			
		});
	}
	
	
	
	
	@Override
	public void dispose() {
		this.stage.dispose();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
