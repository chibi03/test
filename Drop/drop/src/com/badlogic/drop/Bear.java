package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bear extends Rectangle{

	
	public enum Status{
		IDLE,
		JUMPING;
	}	
	
	private Status currentStatus = Status.IDLE;
	private Texture bearImage;
	private int air = 35;
	private int ground = 20;
	private boolean jumped = false;
	private boolean hit = false;
	private int speed;
	private int counter = 0;
	private long timeUnHit = 0;
	
	public Bear(Texture image){
		bearImage = image;
		x = 80;
		y = 20;
		width = 64;
		height = 64;
		speed = 50;
	}
	
	public Status getStatus(){
		return currentStatus;
	}
	
	public void setLocation(){
		if(currentStatus == Status.JUMPING && y < air){
			System.out.println("jumping "+y);
			y += 15 * Gdx.graphics.getDeltaTime();
		} else if (currentStatus == Status.JUMPING && y >= air){
			setStatus(Status.IDLE);
			System.out.println("threshold");
		} else if (currentStatus == Status.IDLE && y > ground){
			System.out.println("falling");
			y -= 15 * Gdx.graphics.getDeltaTime();
		}
		
		if (x < 0){
			x = 0;
		}else if (x > 800 - 64){
			x = 800 - 64;
		} else {
			x += speed * Gdx.graphics.getDeltaTime();
		}
	}
	
	public void setTimeUnHit(long time){
		timeUnHit = time;
	}
	
	public long getTimeUnHit(){
		return timeUnHit;
	}
	
	public void resetTime(){
		timeUnHit = 0;
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

	public void setStatus(Status status) {
		currentStatus = status;
	}

	public void incrementSpeed() {
		speed += 0.5;
	}

	public int getCounter() {
		return counter;
	}
	
}
