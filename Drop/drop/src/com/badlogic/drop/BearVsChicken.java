package com.badlogic.drop;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class BearVsChicken implements ApplicationListener{

	Texture bearImage;
	SpriteBatch batch;
	OrthographicCamera camera;
	long lastHitTime;
	private Bear bear;
	private Chicken chicken;
	private Texture chickenImage;
	
	@Override
	public void create() {
		bearImage = new Texture(Gdx.files.internal("bear.png"));
		chickenImage = new Texture(Gdx.files.internal("chicken.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		bear = new Bear(bearImage);
		chicken = new Chicken(chickenImage);
	}

	
	@Override
	public void render() {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(bear.getImage(), bear.x, bear.y);
		batch.draw(chicken.getImage(), chicken.x, chicken.y);
		batch.end();

		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			bear.y += 10;
			bear.setJumping();
		}
		chicken.setLocation();
		bear.setLocation();
		bear.incrementSpeed();
		
		if(chicken.overlaps(bear)){
			bear.setHit(true);
		}
		
		if(bear.wasHit()){
			System.out.println("Overlap");
			bear.setSpeed(100);
			lastHitTime = TimeUtils.nanoTime();
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
