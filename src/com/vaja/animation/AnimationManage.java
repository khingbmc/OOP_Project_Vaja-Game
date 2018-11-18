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
}public class AnimationManager {

    // animation index constants
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;

    public float width;
    public float height;

    // animation holder instance
    public CustomAnimation currentAnimation;

    // for multi-directional animations
    // 0 - up, 1 - down, 2 - left, 3 - right
    private CustomAnimation[] animations;
    private TextureRegion[][] animationFrames;

    /**
     * Sets up for single animations
     *
     * @param sprites 2d array of sprites so that different sized animations can be used
     * @param numFrames the amount of frames in the single animation
     * @param delay
     */
    public AnimationManager(TextureRegion[][] sprites, int numFrames, int index, float delay) {
        TextureRegion[] frames = new TextureRegion[numFrames];

        width = sprites[index][0].getRegionWidth();
        height = sprites[index][0].getRegionHeight();

        for (int i = 0; i < numFrames; i++) {
            frames[i] = sprites[index][i];
        }

        currentAnimation = new CustomAnimation(delay, frames);
    }

    /**
     * Sets up the animation array for multi-directional animations
     *
     * @param sprites 2d array of sprites so that different sized animations can be used
     * @param numAnimations the number of animations in a row, not the number of frames
     * @param index a sprite's animation index/row on the spritesheet
     * @param numFrames the number of frames in one animation
     * @param delay
     */
    public AnimationManager(TextureRegion[][] sprites, int numAnimations, int index, int numFrames, float delay) {
        animations = new CustomAnimation[numAnimations];
        animationFrames = new TextureRegion[numAnimations][numFrames];

        width = sprites[index][0].getRegionWidth();
        height = sprites[index][0].getRegionHeight();

        // converting the animations frames row in the sprite texture to a 2d array to match the animation array
        for (int i = 0; i < sprites[index].length / numAnimations; i++) {
            for (int j = 0; j < animationFrames[0].length; j++) {
                animationFrames[i][j] = sprites[index][(j % numAnimations) + (i * numAnimations)];
            }
        }
        for (int i = 0; i < animations.length; i++) {
            animations[i] = new CustomAnimation(delay, animationFrames[i]);
        }

        currentAnimation = animations[0]; // initially set frame to idle down facing frame
    }

    /**
     * Specifically handles four directional animations animations
     *
     * @param sprites
     * @param index
     * @param delay
     */
    public AnimationManager(TextureRegion[][] sprites, int index, float delay) {
        animations = new CustomAnimation[4];
        animationFrames = new TextureRegion[4][4];

        width = sprites[index][0].getRegionWidth();
        height = sprites[index][0].getRegionHeight();

        // converting the animations frames row in the sprite texture to a 2d array to match the animation array
        for (int i = 0; i < sprites[index].length / 4; i++) {
            for (int j = 0; j < animationFrames[0].length; j++) {
                animationFrames[i][j] = sprites[index][(j % 4) + (i * 4)];
            }
        }
        for (int i = 0; i < animations.length; i++) {
            animations[i] = new CustomAnimation(delay, animationFrames[i]);
        }

        currentAnimation = animations[0]; // initially set frame to idle down facing frame
    }

    public void update(float dt) {
        currentAnimation.update(dt);
        currentAnimation.play();
    }

    /**
     * Sets currentAnimation to an animation established in the animation array
     *
     * @param index
     */
    public void setAnimation(int index) {
        currentAnimation = animations[index];
    }

    public void stopAnimation() {
        currentAnimation.stop();
    }

    public TextureRegion getKeyFrame(boolean looping) {
        return currentAnimation.getKeyFrame(looping);
    }


}
