package com.vaja.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vaja.main.Vaja;

/**
 * Desktop app access
 * @author khingbmc
 *
 */

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Vaja.V_WIDTH * Vaja.V_SCALE;
        config.height = Vaja.V_HEIGHT * Vaja.V_SCALE;
        config.resizable = true;
        config.vSyncEnabled = false;
        config.title = Vaja.TITLE;

		new LwjglApplication(new Vaja(), config);
	}

}
