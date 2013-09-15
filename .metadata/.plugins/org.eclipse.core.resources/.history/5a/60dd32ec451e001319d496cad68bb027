package core;

import actors.Obstacle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controllers.ObstacleController;

public class Og implements ApplicationListener {
	private static float width;
	private static float height;

	private OrthographicCamera camera;
	private ObstacleController obsController;
	private SpriteBatch batch;

	/**
	 * Get the width of the visible area
	 * 
	 * @return
	 */
	public static float getWidth() {
		return width;
	}

	/**
	 * Get the height of the visible area
	 * 
	 * @return
	 */
	public static float getHeight() {
		return height;
	}

	/**
	 * Standard ApplicationListener first entry point. Put code here if we need
	 * it to run before render loop kicks off
	 */
	@Override
	public void create() {
		Og.width = Gdx.graphics.getWidth() * 3;
		Og.height = Gdx.graphics.getHeight() * 3;
		camera = new OrthographicCamera(Og.getWidth(), Og.getHeight());
		obsController = new ObstacleController();
		batch = new SpriteBatch();
	}

	/**
	 * Standard ApplicationListener entry point. This is the last thing we will
	 * run. Be sure to clean up all remaining objects at this point
	 */
	@Override
	public void dispose() {
		obsController.dispose();
		batch.dispose();
	}

	/**
	 * ApplicationListener pause method. TODO: Decide on expected behaviour
	 * during a pause and implement
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * Render loop. This loop will be called every frame. Should update all
	 * known objects and then draw them by adding them to the batch
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		obsController.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Obstacle obstacle : obsController.getObstacles()) {
			batch.draw(obstacle.getSprite(), obstacle.getX(), obstacle.getY());
		}
		batch.end();
	}

	/**
	 * Method that will be called when the user resizes the window
	 * TODO: Resize the background and all known objects 
	 */
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * Partner method to {@link #pause()}. Will be called when the player unpauses the game
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
