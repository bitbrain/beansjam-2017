package tv.rocketbeans.supermafiosi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		config.title = "Super Mafiosi (#beansjam edition)";
		new LwjglApplication(new SuperMafiosiGame(), config);
	}
}
