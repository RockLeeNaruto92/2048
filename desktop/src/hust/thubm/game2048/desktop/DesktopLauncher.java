package hust.thubm.game2048.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import hust.thubm.game2048.Game2048;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Title";
		cfg.useGL30 = true;
		cfg.height = 640;
		cfg.width = 400;
		new LwjglApplication(new Game2048(), cfg);
	}
}
