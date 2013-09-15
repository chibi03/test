package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bear extends AnimatedActor {

	Texture animatedImage;
	
	public Bear(Sprite s, float x, float y) {
		super(s, x, y, new Texture(Gdx.files.local("src/img/polarbear_sprite_walking.jpg")));
	}


}
