package actors;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Actor {
	protected Sprite sprite;
	protected float posX;
	protected float posY;
	
	// Constructor
	// Required arguments ensure that we avoid null-pointer errors when using public getters
	protected Actor(Sprite s, float x, float y) {
		sprite = s;
		posX = x;
		posY = y;
	}
	
	// Getters
	public float getX(){
		return posX;
	}
	
	public float getY(){
		return posY;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	// Abstract methods
	public abstract void update();
	public abstract void dispose();
	
}
