package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bear extends Rectangle{

	private Texture bearImage;
	private boolean jumped = false;
	private boolean hit = false;
	private int speed;
	
	public Bear(Texture image){
		bearImage = image;
		x = 80;
		y = 20;
		width = 64;
		height = 64;
		speed = 100;
	}
	
	public void setLocation(){
		if (x < 0){
			x = 0;
		}else if (x > 800 - 64){
			x = 800 - 64;
		} else {
			x += speed * Gdx.graphics.getDeltaTime();
		}
	}
	
	public Texture getImage(){
		return bearImage;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setHit(boolean hit){
		this.hit = hit;
	}
	
	public boolean wasHit(){
		if(hit ){
			hit = false;
			return true;
		}		
		return false;
	}
	
	public boolean jumped(){
		if(jumped){
			jumped = false;
			return true;
		}
		return false;
	}

	public void setJumping() {
		jumped = true;
	}

	public void incrementSpeed() {
		speed += 5;
	}
	
}
