package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Chicken extends Rectangle{

	private Texture chickenImage;
	private boolean jumped = false;
	private boolean hit = false;
	private int speed;
	
	public Chicken(Texture image){
		chickenImage = image;
		x = 10;
		y = 20;
		width = 64;
		height = 64;
		speed = 50;
	}
	
	public void setLocation(){
//		if (x < 0){
//			x = 0;
//		}else if (x > 800 - 64){
//			x = 800 - 64;
//		} else {
//			x += speed * Gdx.graphics.getDeltaTime();
//		}
	}
	
	public Texture getImage(){
		return chickenImage;
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
		speed += 0.5;
	}
	
}
