package com.vaja.resource;

import com.badlogic.gdx.assets.AssetManager;

/**
 * this class is use to loading store and manage all of resource is use in all class
 * @author khingbmc
 *
 */

public class ResourceManage {
	//asset manage
	private AssetManager asset;

	
	
	public ResourceManage() {
		asset = new AssetManager();
	}
	
	public void dispose() {
		asset.dispose();
	}

}


