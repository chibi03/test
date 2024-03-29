package com.badlogic.drop;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Drop implements ApplicationListener {
	Texture dropImage;
	Texture bucketImage;
	Music backgroundMusic;
	Sound hitSound;
	SpriteBatch batch;
	OrthographicCamera camera;
	Rectangle bucket;
	Array<BallSprite> balls;
	long lastDropTime;
	Sound fallSound;
	BitmapFont font;
	private Counter counter;
	private Texture tileImage;
	Array<Tile> wall;
	
	@Override
	public void create() {
		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("ball.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		tileImage = new Texture(Gdx.files.internal("tile.png"));
		
		counter = new Counter();
		
		font = new BitmapFont(Gdx.files.internal("font/test.fnt"), false);
		font.setColor(0f, 0f, 0f, 1.0f);

		// load the drop sound effect and the rain background "music"
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		hitSound = Gdx.audio.newSound(Gdx.files.internal("hit.mp3"));
		fallSound = Gdx.audio.newSound(Gdx.files.internal("fall2.wav"));
		
		// start the playback of the background music immediately
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		// create a Rectangle to logically represent the bucket
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 64;
		bucket.height = 16;

		// create the raindrops array and spawn the first raindrop
		wall = new Array<Tile>();
		balls = new Array<BallSprite>();
		spawnRaindrop();
		createWall();
	}

	private void spawnRaindrop() {
		BallSprite ball = new BallSprite(dropImage);
		balls.add(ball);
		ball.spawn();
		lastDropTime = TimeUtils.nanoTime();
	}
	
	private void createWall(){
		for(int i = 0; i<=5; i++){
			for(int j = 0; j<=10; j++){
				Tile tile = new Tile(tileImage);
				tile.setPosition(40+68*j, 280+18*i);
				wall.add(tile);
			}
		}
	}
	
	
	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		for (Tile tile : wall) {
			batch.draw(tile.getTexture(), tile.x, tile.y);
		}
		font.draw(batch, "Hits: "+counter.getCount(), 650, 450);
		batch.draw(bucketImage, bucket.x, bucket.y);
		for (BallSprite ball : balls) {
			batch.draw(ball.getTexture(), ball.x, ball.y);
		}
		
		batch.end();

		// process user input
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			bucket.x -= 250 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			bucket.x += 250 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (bucket.x < 0)
			bucket.x = 0;
		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000 && balls.size < 1)
			spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<BallSprite> iter = balls.iterator();
		while (iter.hasNext()) {
			BallSprite ball = iter.next();
			
			ball.setLocation();
			if (ball.getLocation() <= 0){
				counter.setCount(0);
				fallSound.play();
				iter.remove();
			}
			if (ball.getLocation() >= 448){
				ball.unreverse();
			}			
			if (ball.overlaps(bucket) && !ball.isReversed()) {
				counter.increment();
				hitSound.play();
				ball.reverse();
			}
			Iterator<Tile> tileIter = wall.iterator();
			while(tileIter.hasNext()){
				if (tileIter.next().overlaps(ball)){
					tileIter.remove();
				}
			}
		}
		
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		dropImage.dispose();
		bucketImage.dispose();
		backgroundMusic.dispose();
		hitSound.dispose();
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