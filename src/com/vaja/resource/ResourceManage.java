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

    public AssetManager assetManager;

    // 2D TextureRegion arrays that stores sprites of various sizes for easy animation
    public TextureRegion[][] sprites16x16;
    public TextureRegion[][] tiles16x16;
    public TextureRegion[][] dirpad20x20;
    public TextureRegion[][] movebutton110x40;

    // Arrays for each type of Move
    // Contains the entire pool of moves for each type
    public Array<Move> accurateMoves = new Array<Move>();
    public Array<Move> wideMoves = new Array<Move>();
    public Array<Move> critMoves = new Array<Move>();
    public Array<Move> healMoves = new Array<Move>();

    public ResourceManage() {
        assetManager = new AssetManager();

        assetManager.load("res/character/test1.png", Texture.class);
        assetManager.load("res/map/test_map.png", Texture.class);
        assetManager.load("res/button.png", Texture.class);
        

        FileHandleResolver resolver = new InternalFileHandleResolver();
//        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
//        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
//
//        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
//        font.fontFileName = "arial.ttf";
//        font.fontParameters.size = 10;
//        font.fontParameters.minFilter = Texture.TextureFilter.Nearest;
//        font.fontParameters.magFilter = Texture.TextureFilter.Nearest;
//        assetManager.load("arial.ttf", BitmapFont.class, font);

        loadMoves();

        assetManager.finishLoading();

        sprites16x16 = TextureRegion.split(
                assetManager.get("res/character/test1.png", Texture.class), 16, 16);
        tiles16x16 = TextureRegion.split(
                assetManager.get("res/map/test_map.png", Texture.class), 16, 16);
        dirpad20x20 = TextureRegion.split(assetManager.get("res/button.png", Texture.class), 40, 40);
    }

    /**
     * Loads the ImageButton styles for buttons with up and down image effects
     *
     * @param numButtons
     * @param sprites
     * @return a style array for ImageButtons
     */
    public ImageButton.ImageButtonStyle[] loadImageButtonStyles(int numButtons, TextureRegion[][] sprites) {
        ImageButton.ImageButtonStyle[] ret = new ImageButton.ImageButtonStyle[numButtons];
        for (int i = 0; i < numButtons; i++) {
            ret[i] = new ImageButton.ImageButtonStyle();
            ret[i].imageUp = new TextureRegionDrawable(sprites[0][i]);
            ret[i].imageDown = new TextureRegionDrawable(sprites[1][i]);
        }
        return ret;
    }

    private void loadMoves() {
        // parse moves.json
        JsonReader jsonReader = new JsonReader();
        JsonValue base = jsonReader.parse(Gdx.files.internal("res/moveset.json"));

        // accurate Moves
        for (JsonValue move : base.get("accurate")) {
            Move m = new Move(move.getInt("type"), move.getString("name"),
                    move.getString("description"), move.getInt("minDamage"), move.getInt("maxDamage"));
            accurateMoves.add(m);
        }
        // wide Moves
        for (JsonValue move : base.get("wide")) {
            Move m = new Move(move.getInt("type"), move.getString("name"),
                    move.getString("description"), move.getInt("minDamage"), move.getInt("maxDamage"));
            wideMoves.add(m);
        }
        // crit Moves
        for (JsonValue move : base.get("critical")) {
            Move m = new Move(move.getString("name"), move.getString("description"),
                    move.getInt("damage"), move.getInt("critical"));
            critMoves.add(m);
        }
        // heal Moves
        for (JsonValue move : base.get("healing")) {
            Move m = new Move(move.getInt("type"), move.getString("name"),
                    move.getString("description"), move.getInt("minHeal"), move.getInt("maxHeal"));
            healMoves.add(m);
        }
    }

    public void dispose() {
        assetManager.dispose();
    }

}


