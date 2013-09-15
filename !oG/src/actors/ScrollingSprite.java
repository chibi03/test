package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingSprite extends Actor {

	 Texture spriteTexture;
	 float scrollTimer;
	 
	        
	 public void create() {
	     spriteTexture = new Texture(Gdx.files.local("src/img/bg.jpg"));
	     spriteTexture.setWrap(TextureWrap.Repeat,TextureWrap.Repeat);
	 }
	 
	 public void startScroll(SpriteBatch spriteBatch) {
	     scrollTimer+=Gdx.graphics.getDeltaTime();
	     if(scrollTimer>1.0f)
	         scrollTimer = 0.0f;
	     
	     getSprite().setU(scrollTimer);
	     getSprite().setU2(scrollTimer+1);
	                
	     spriteBatch.draw(getSprite(), getX(), getY());
	 }      
	
	public ScrollingSprite(Sprite s, float x, float y) {
		super(s, x, y);
		sprite.setSize(800, 200);
		scrollTimer = 0.0f;
	}

	@Override
	public void update() {
	}

	@Override
	public void dispose() {
	}

}
