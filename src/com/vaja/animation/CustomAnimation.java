package com.vaja.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class CustomAnimation extends Animation {

    private float stateTime;
    private boolean playing;

    public CustomAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
    }

    public CustomAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
    }

    public CustomAnimation(float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
    }

    public void play() {
        if (!playing) {
            playing = true;
        }
    }

    public void pause() {
        if (playing) {
            playing = false;
        }
    }

    public void stop() {
        if (stateTime != 0f) {
            stateTime = 0f;
        }
        if (playing) {
            playing = false;
        }
    }

    public void update(float delta) {
        if (playing) {
            stateTime += delta;
        }
    }

    public TextureRegion getKeyFrame(boolean looping) {
        return getKeyFrame(stateTime, looping);
    }

    public TextureRegion getKeyFrame() {
        return getKeyFrame(stateTime);
    }

    public int getKeyFrameIndex() {
        return getKeyFrameIndex(stateTime);
    }

    public boolean isAnimationFinished() {
        return isAnimationFinished(stateTime);
    }

    public boolean isPlaying() {
        return playing;
    }

}public class CustomAnimation extends Animation{
	private float stateTime;
	private boolean play;
	
	public CustomAnimation(float frameDuration, Array<? extends TextureRegion> keyFrame) {
		super(frameDuration, keyFrame);
		// TODO Auto-generated constructor stub
	}
	
	public CustomAnimation(float frameDuration, Array<? extends TextureRegion> keyFrame, PlayMode play) {
		super(frameDuration, keyFrame, play);
	}
	
	public CustomAnimation(float frameDuration, TextureRegion... keyFrame) {
		super(frameDuration, keyFrame);
	}
	
	public void play() {
		if(!this.play) {
			this.play = true;
		}
	}
	
	public void pause() {
		if(this.play) {
			this.play = false;
		}
	}
	
	public void stop() {
		if(this.stateTime != 0f) {
			this.stateTime = 0f;
		}
		if(this.play) {
			this.play = false;
		}
	}
	
	public void update(float delta) {
		if(this.play) {
			this.stateTime += delta;
		}
	}
	
	public TextureRegion getKeyFrame(boolean looping) {
//		System.out.println(this.stateTime);
		return getKeyFrame(this.stateTime, looping);
	}
	
	public TextureRegion getKeyFrame() {
		return getKeyFrame(this.stateTime);
	}
	
	public int getKeyFramIndex() {
		return getKeyFrameIndex(this.stateTime);
	}
	
	public boolean isAnimationFinished() {
		return isAnimationFinished(this.stateTime);
	}
	
	public boolean isPlaying() {
		return this.play;
	}

}
