package com.vaja.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * this class is use to loading store and manage all of resource is use in all class
 * @author khingbmc
 *
 */

public class ResourceManage {
	
	
	//asset manage
	public AssetManager asset;

	//2d texture arrays has contain sprite
	public TextureRegion[][] sprites;
	public TextureRegion[][] tiles16x16;
	public TextureRegion[][] dir20x20;
	
	public ResourceManage() {
		asset = new AssetManager();
		
		asset.load("res/map/test_map.png", Texture.class);
		asset.load("res/character/test1.png", Texture.class);
		asset.load("res/dir2.png", Texture.class);
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
//		asset.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
//		asset.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
//
//
//		FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
//		font.fontFileName = "res/font/arial.ttf";
//		font.fontParameters.size = 10;
//		font.fontParameters.magFilter = Texture.TextureFilter.Nearest;
//		font.fontParameters.minFilter = Texture.TextureFilter.Nearest;
//		asset.load("arial.ttf", BitmapFont.class, font);

		asset.finishLoading();
		
		this.tiles16x16 = TextureRegion.split(asset.get("res/map/test_map.png", Texture.class), 16, 16);
		
		
		this.sprites =  TextureRegion.split(asset.get("res/character/test1.png", Texture.class), 16, 16);
		this.dir20x20 = TextureRegion.split(asset.get("res/dir2.png", Texture.class), 40, 40);
	}
	
	public void dispose() {
		asset.dispose();
	}

}


