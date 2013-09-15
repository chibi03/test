package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Chicken extends AnimatedActor {

	public Chicken(Sprite s, float x, float y) {
		super(s, x, y, new Texture(Gdx.files.local("src/img/chicken_sprite_walking.jpg")));
	}

}
