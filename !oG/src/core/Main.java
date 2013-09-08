package core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Entry point for windows desktop application
 * 
 * @author Andy
 *
 */
public class Main {
	public static void main(String[] args){
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MyGame";
		cfg.width = 800;
		cfg.height = 200;
		new LwjglApplication(new Og(), cfg);
	}
}
