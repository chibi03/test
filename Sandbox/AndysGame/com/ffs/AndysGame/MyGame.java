package com.ffs.AndysGame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture backgroundTexture;
	private Sprite backgroundSprite;
	private Texture actorTexture;
	private Sprite actorSprite;
	private float width;
	private float height;
	
	/**
	 * Entry point for the game
	 * Create camera and sprite batch here.
	 */
	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(width, height);
		batch = new SpriteBatch();
		
		backgroundTexture = new Texture(Gdx.files.local("AndysGame/assets/TestBackground.png"));
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(backgroundTexture, (int)Math.floor(width), (int)Math.floor(height));
		
		backgroundSprite = new Sprite(region);
		backgroundSprite.setOrigin(0, 0);
		backgroundSprite.setPosition(-width/2, -height/2);
		
		
		actorTexture = new Texture(Gdx.files.local("AndysGame/assets/TestTexture.png"));
		actorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion region2 = new TextureRegion(actorTexture, 50, 50);
		
		actorSprite = new Sprite(region2);
		actorSprite.setOrigin(0, 0);
		actorSprite.setPosition(0, 0);
		
	}

	/**
	 * Called at the end of the game - we need to clear up all objects here
	 */
	@Override
	public void dispose() {
		batch.dispose();
		backgroundTexture.dispose();
		actorTexture.dispose();		
	}

	/**
	 * Required for Android development
	 */
	@Override
	public void pause() {
	}

	/**
	 * Main game update loop
	 * We need to process user input, update actor locations, and draw all actors
	 */
	@Override
	public void render() {
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		Block activeBlock = Block.getActiveBlock();
		activeBlock.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY());
		for(Block block : Block.getBlockList()){
			batch.draw(block.getSprite(), block.getX(), block.getY());
		}
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Required for Android development
	 */
	@Override
	public void resume() {		
	}
	
}
