package com.vaja.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * this class is use to loading store and manage all of resource is use in all class
 * @author khingbmc
 *
 */

public class ResourceManage {
	
	
	//asset manage
	private AssetManager asset;

	//2d texture arrays has contain sprite
	public TextureRegion[][] sprites;
	public TextureRegion[][] tiles16x16;
	
	
	public ResourceManage() {
		asset = new AssetManager();
		
		asset.load("res/map/test_map.png", Texture.class);
		asset.load("res/character/test1.png", Texture.class);
		asset.finishLoading();
		
		this.tiles16x16 = TextureRegion.split(asset.get("res/map/test_map.png", Texture.class), 16, 16);
		
		
		this.sprites =  TextureRegion.split(asset.get("res/character/test1.png", Texture.class), 16, 16);
	}
	
	public void dispose() {
		asset.dispose();
	}

}


