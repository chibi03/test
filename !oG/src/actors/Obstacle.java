package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import controllers.ObstacleController;

import enums.ObstacleType;

/**
 * Obstacle class - we wish to create a range of obstacles for the runner to
 * need to avoid. The obstacle should not be required to move, but will slide
 * across the screen to simulate movement for the runner
 * TODO: link to runner in javadoc
 * 
 * @author Andy
 * 
 */
public class Obstacle extends Actor {
	ObstacleType type;

	public Obstacle(ObstacleType obstacleType, float x, float y)
			throws Exception {
		super(loadSprite(obstacleType), x, y);
		this.type = obstacleType;
		this.sprite.setPosition(posX, posY);
	}

	/**
	 * Move the Obstacle the expected distance along the x axis.
	 * "Apparent velocity" of the obstacle should depend on the velocity of the
	 * bear. TODO: Link to bear in javadoc
	 */
	@Override
	public void update() {
		posX += 100 * Gdx.graphics.getDeltaTime(); // TODO: Update speed based
													// on bear velocity
		this.sprite.setPosition(posX, posY);
	}

	/**
	 * Does nothing for the Obstacle. Present as part of {@link actors.Actor}
	 * inheritence
	 */
	@Override
	public void dispose() {
		// Nothing to do here
	}

	/**
	 * Static method to load the correct
	 * {@link com.badlogic.gdx.graphics.g2d.Sprite} for the type of obstacle
	 * being generated. This is called by the
	 * {@link actors.Obstacle#Obstacle(ObstacleType, float, float) constructor}
	 * 
	 * @param obstacleType
	 * @return
	 * @throws Exception
	 */
	private static Sprite loadSprite(ObstacleType obstacleType)
			throws Exception {
		Texture tex = ObstacleController.getTexture(obstacleType);
		Sprite sprite = new Sprite(tex);
		sprite.setSize(5, 20);
		return sprite;
	}

}
