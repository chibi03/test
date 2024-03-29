package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import core.Constants;

public abstract class AnimatedActor extends Actor {

	Texture animatedImage;
	Animation walkAnimation;
	TextureRegion[] walkFrames;
	TextureRegion currentFrame;
	float stateTime;

	protected AnimatedActor(Sprite s, float x, float y, Texture image) {
		super(s, x, y);
		animatedImage = image;
	}

	public void createAnimation() {
		TextureRegion[][] temp = TextureRegion.split(animatedImage,
				animatedImage.getWidth() / Constants.ACTOR_FRAME_COLS,
				animatedImage.getHeight() / Constants.ACTOR_FRAME_ROWS);
		walkFrames = new TextureRegion[Constants.ACTOR_FRAME_COLS
				* Constants.ACTOR_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < Constants.ACTOR_FRAME_ROWS; i++) {
			for (int j = 0; j < Constants.ACTOR_FRAME_COLS; j++) {
				walkFrames[index++] = temp[i][j];
			}
		}
		walkAnimation = new Animation(Constants.ACTOR_FRAME_DURATION,
				walkFrames);
		stateTime = 0f;
	}

	public void update(){
		//TODO
	}
	
	public void dispose(){
		//TODO
	}
	
	public void startRender(SpriteBatch spriteBatch) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		spriteBatch.draw(currentFrame, getX(), getY());
	}

	
	public Texture getAnimatedImage() {
		return animatedImage;
	}
	
	public void setAnimatedImage(Texture animatedImage) {
		this.animatedImage = animatedImage;
	}
	
	public float getStateTime() {
		return stateTime;
	}
	
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
}
