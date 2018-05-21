package com.mygdx.sniper_expanse.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sniper.expanse.SniperExpanse;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SniperExpanse";
		config.height = 800;
		config.width = 1000;
		new LwjglApplication(new SniperExpanse(), config);
	}
}
