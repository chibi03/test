package com.badlogic.drop;


import com.badlogic.drop.Bear.Status;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class BearVsChicken implements ApplicationListener{

	SpriteBatch batch;
	OrthographicCamera camera;
	long lastHitTime;
	private Bear bear;
	private Texture bearImage;
	private Chicken chicken;
	private Texture chickenImage;
	
	Texture background;
	float currentBgX;
	long lastTimeBg;

	
	@Override
	public void create() {
		bearImage = new Texture(Gdx.files.external("Workspaces/test/Drop/drop-desktop/assets/polarbear_sprite_walking.jpg"));
		chickenImage = new Texture(Gdx.files.internal("chicken.png"));
		// Init the background image
//		background = new Texture(Gdx.files.external("bg.jpg"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		bear = new Bear(bearImage);
		bear.startAnimation();
		chicken = new Chicken(chickenImage);
		
		// Init the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);


		// the separator first appear at the position 800 (the edge of the screen, see
		// the camera above)
		currentBgX = 800;

		// set lastTimeBg to current time
		lastTimeBg = TimeUtils.nanoTime();

	}

	
	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		// draw the first background
//		batch.draw(background, currentBgX - 800, 0);
		// draw the second background
//		batch.draw(background, currentBgX, 0);
		bear.startRender(batch);
		batch.draw(chicken.getImage(), chicken.x, chicken.y);
		batch.end();

		if (Gdx.input.isKeyPressed(Keys.SPACE) && !bear.getStatus().equals(Status.JUMPING)){
			bear.setStatus(Status.JUMPING);
		}
//		if(bear.getCounter() == 10){
//			bear.setStatus(Status.FALLING);
//		} else if (bear.getCounter() == 0){
//			bear.setStatus(Status.IDLE);
//		}
		chicken.setLocation();
		bear.setLocation();
		
		if(chicken.overlaps(bear)){
			bear.setHit(true);
			bear.resetTime();
		} else {
			bear.setTimeUnHit(TimeUtils.nanoTime());
			bear.setSpeed(bear.getSpeed()+ ((int)bear.getTimeUnHit()/6000000));
		}
		
		if(bear.wasHit()){
			System.out.println("Overlap");
			bear.setSpeed(100);
			lastHitTime = TimeUtils.nanoTime();
		}
		
		// move the separator each 1s
		if(TimeUtils.nanoTime() - lastTimeBg > 100000000){
			// move the separator 50px
			currentBgX -= 50;
			// set the current time to lastTimeBg
			lastTimeBg = TimeUtils.nanoTime();
		}

		// if the seprator reaches the screen edge, move it back to the first position
		if(currentBgX == 0){
			currentBgX = 800;
		}

	}

	@Override
	public void dispose() {
		bearImage.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
}
