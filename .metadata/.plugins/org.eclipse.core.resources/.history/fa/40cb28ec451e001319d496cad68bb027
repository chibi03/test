package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import controllers.ObstacleController;

import enums.ObstacleType;

/**
 * Obstacle class - we wish to create a range of obstacles for the runner to
 * need to avoid. The obstacle should not be required to move.
 * 
 * @author Andy
 * 
 */
public class Obstacle extends Actor {
	ObstacleType type;

	public Obstacle(ObstacleType obstacleType, float x, float y) throws Exception {
		super(loadSprite(obstacleType), x, y);
		this.type = obstacleType;
		this.sprite.setPosition(posX, posY);
	}

	/**
	 * Standard actor update method - should be called during render loop
	 */
	@Override
	public void update() {
		posX += 100 * Gdx.graphics.getDeltaTime(); //TODO: Update speed based on bear velocity
		this.sprite.setPosition(posX, posY);
	}

	/**
	 * Standard actor dispose method - should be called when actor is no longer
	 * needed
	 */
	@Override
	public void dispose() {
		// Nothing to do here
	}

	/**
	 * Static method to load the correct sprite for the type of obstacle being
	 * generated. This is called by the constructor
	 * 
	 * @param obstacleType
	 * @return
	 * @throws Exception
	 */
	private static Sprite loadSprite(ObstacleType obstacleType) throws Exception {
		Texture tex = ObstacleController.getTexture(obstacleType);
		Sprite sprite = new Sprite(tex);
		sprite.setSize(5, 20);
		return sprite;
	}

}
