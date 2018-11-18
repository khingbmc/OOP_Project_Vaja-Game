package com.vaja.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * this class is manage animation in four direction
 * @author khingbmc
 *
 */

public class AnimationManage {
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int LEFT = 3;
	
	public float width;
	public float height;
	
	//animation holder
	public CustomAnimation current;
	
	private TextureRegion[][] animationFrame;
	private CustomAnimation[] animations;
	
	public AnimationManage(TextureRegion[][] sprites, int numFrame, int index, float delay) {
		TextureRegion[] frames = new TextureRegion[numFrame];
		
		this.width = sprites[index][0].getRegionWidth();
		this.height = sprites[index][0].getRegionHeight();
		
		for (int i = 0;i < numFrame;i++) {
			frames[i] = sprites[index][i];
		}
		
		this.current = new CustomAnimation(delay, frames);
	}
	
	/**
	 * sprite
	 */
	
	public AnimationManage(TextureRegion[][] sprites,int numAnimation , int index,int numFrame, float delay) {
		animations = new CustomAnimation[numAnimation];
		animationFrame = new TextureRegion[numAnimation][numFrame];
		
		this.width = sprites[index][0].getRegionWidth();
		this.height = sprites[index][0].getRegionHeight();
		
		//convert animation frame row in sprite to 2d array
		for(int i = 0;i < sprites[index].length / numAnimation; i++) {
			for(int j=0;j<this.animationFrame[0].length;j++) {
				this.animationFrame[i][j] = sprites[index][(j % numAnimation) + (i * numAnimation)];
			}
		}
		for(int i=0;i < this.animations.length; i++) {
			this.animations[i] = new CustomAnimation(delay, animationFrame[i]);
 		}
		
		this.current = this.animations[0];
	}
	
	/**
	 * handle four direction
	 */
	public AnimationManage(TextureRegion[][] sprites, int index, float delay) {
		this.animations = new CustomAnimation[4];
		this.animationFrame = new TextureRegion[4][4];
		
		this.width = sprites[index][0].getRegionWidth();
		this.height = sprites[index][0].getRegionHeight();
		
		//convert animation frame row in sprite to 2d array
		for(int i = 0;i<sprites[index].length/4;i++) {
//			System.out.println(sprites[index].toString());
			for(int j=0;j<this.animationFrame[0].length;j++) {
				
				this.animationFrame[i][j] = sprites[index][(j%4) + (i*4)];
			}
		}
		for(int i=0;i<this.animations.length;i++) {
			this.animations[i] = new CustomAnimation(delay, this.animationFrame[i]);
		}
		this.current = this.animations[0];
	}
	
	public void update(float delta) {
		this.current.update(delta);
		this.current.play();
	}
	
	/**
	 * 
	 */
	public void setAnimation(int index) {
		this.current = this.animations[index];
	}
	
	public void stopAnimation() {
		this.current.stop();
	}
	
	public TextureRegion getKeyFrame(boolean looping) {
		return this.current.getKeyFrame(looping);
	}
}
