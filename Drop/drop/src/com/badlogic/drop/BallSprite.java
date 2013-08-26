package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class BallSprite extends Rectangle{

	private Texture image;
	private boolean reversed = false;
	
	public BallSprite(Texture image){
		this.image = image;
		
	}
	
	public void spawn(){
		x = MathUtils.random(0, 800 - 64);
		y = 480;
		width = 64;
		height = 64;
	}

	public void setLocation(){
		if(!reversed){
			y -= 200 * Gdx.graphics.getDeltaTime();
		}else{
			y += 200 * Gdx.graphics.getDeltaTime();
		}
	}
	
	public Texture getTexture(){
		return image;
	}
	
	public void setTexture(Texture image){
		this.image =  image;
	}
	
	public float getLocation(){
		return y;
	}
	
	public void reverse(){
		reversed  = true;
	}
	
	public void unreverse(){
		reversed = false;
	}
	
	public boolean isReversed(){
		return reversed;
	}
}
