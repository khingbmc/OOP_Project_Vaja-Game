package com.vaja.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.vaja.battle.Move;

/**
 * this class is use to loading store and manage all of resource is use in all class
 * @author khingbmc
 *
 */

public class ResourceManage {
	
	
	//asset manage
	public AssetManager asset;

	//2d texture arrays has contain sprite
	public TextureRegion[][] sprites16x16;
	public TextureRegion[][] tiles16x16;
	public TextureRegion[][] dir20x20;
	public TextureRegion[][] movebutt110x40;
	
	//Array is contain each type of moveset
	public Array<Move> accurateMove = new Array<Move>();
	public Array<Move> wideMove = new Array<Move>();
	public Array<Move> critMove = new Array<Move>();
	public Array<Move> healMove = new Array<Move>();
	
	public ResourceManage() {
		asset = new AssetManager();
		
		asset.load("res/map/test_map.png", Texture.class);
		asset.load("res/character/test1.png", Texture.class);
		asset.load("res/button.png", Texture.class);
		
		
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
		
		loadMoveset();

		asset.finishLoading();
		
		this.tiles16x16 = TextureRegion.split(asset.get("res/map/test_map.png", Texture.class), 16, 16);
		
		
		this.sprites16x16 =  TextureRegion.split(asset.get("res/character/test1.png", Texture.class), 16, 16);
		this.movebutt110x40 = TextureRegion.split(asset.get("res/button.png", Texture.class), 110, 40);
	}
	
	/**
	 * load img button style with effect button up and down
	 */
	
	public ImageButton.ImageButtonStyle[] loadImageStyles(int numButtons, TextureRegion[][] sprites){
		ImageButton.ImageButtonStyle[] ret = new ImageButton.ImageButtonStyle[numButtons];
		for(int i = 0;i < numButtons;i++) {
			ret[i] = new ImageButton.ImageButtonStyle();
			ret[i].imageUp = new TextureRegionDrawable(sprites[0][i]);
			ret[i].imageDown = new TextureRegionDrawable(sprites[1][i]);
		}
		return ret;
	}
	
	public void dispose() {
		asset.dispose();
	}
	
	private void loadMoveset() {
		//parsing moveset.json
		JsonReader json = new JsonReader();
		JsonValue baseVal = json.parse(Gdx.files.internal("res/moveset.json"));
		
		//accurate
		for(JsonValue move : baseVal.get("accurate")) {
			Move m = new Move(move.getInt("type"), move.getString("name")
					,move.getString("description"), move.getInt("minDamage")
					, move.getInt("maxDamage"));
			this.accurateMove.add(m);
		}
		
		//wide
		for(JsonValue move : baseVal.get("wide")) {
			Move m = new Move(move.getInt("type"), move.getString("name")
							,move.getString("description"), move.getInt("minDamage")
							, move.getInt("maxDamage"));
			this.wideMove.add(m);
		}
		
		//critical
		for(JsonValue move : baseVal.get("critical")) {
			Move m = new Move(move.getString("name"),move.getString("description"), 
					move.getInt("damage"), move.getInt("critical"));
			this.critMove.add(m);
		}
		
		//healing
		for(JsonValue move : baseVal.get("healing")) {
			Move m = new Move(move.getInt("type"), move.getString("name")
							,move.getString("description"), move.getInt("minHeal")
							, move.getInt("maxHeal"));
			this.healMove.add(m);
		}
	}

}


