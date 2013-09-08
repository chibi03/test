package com.ffs.AndysGame;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block {
	private static Texture blockTexture = new Texture(Gdx.files.local("AndysGame/assets/TestTexture.png"));
	private static ArrayList<Block> blockList = new ArrayList<Block>();
	private Sprite blockSprite;
	private float width;
	private float height;
	private boolean isActive;
	
	public Block(){
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		TextureRegion region2 = new TextureRegion(blockTexture, 50, 50);
		
		blockSprite = new Sprite(region2);
		blockSprite.setOrigin(0, 0);
		blockSprite.setPosition(0 - blockSprite.getRegionWidth()/2, height/2); // Top and centre
		blockList.add(this);
		
		isActive = true;
	}
	
	public void update(){
		if(isActive){
			processInput();
		}
		processGravity();
		
	}
	
	public static ArrayList<Block> getBlockList(){
		return blockList;
	}
	
	public static Block getActiveBlock(){
		int blockCount = blockList.size();
		Block activeBlock;
		if(blockCount == 0){
			activeBlock = new Block();
		} else {
			activeBlock = blockList.get(blockCount - 1);
		}
		return activeBlock;
	}
	
	public Sprite getSprite(){
		return blockSprite;
	}
	
	public float getX(){
		return blockSprite.getX();
	}
	
	public float getY(){
		return blockSprite.getY();
	}
	
	private void processInput(){
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			blockSprite.setX(blockSprite.getX() - 300 * Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			blockSprite.setX(blockSprite.getX() + 300 * Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			blockSprite.setY(blockSprite.getY() + 300 * Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			blockSprite.setY(blockSprite.getY() - 300 * Gdx.graphics.getDeltaTime());
		}
		
		// Check that sprite is within bounds of the screen
		if(blockSprite.getX() < -width/2){
			blockSprite.setX(-width/2);
		}
		if(blockSprite.getX() + blockSprite.getWidth() > width/2){
			blockSprite.setX(width/2 - blockSprite.getWidth());
		}
		if(blockSprite.getY() < -height/2){
			blockSprite.setY(-height/2);
			isActive = false;
			new Block();
		}
		if(blockSprite.getY() + blockSprite.getHeight() > height/2){
			blockSprite.setY(height/2 - blockSprite.getHeight());
		}
	}
	
	private void processGravity(){
		blockSprite.setY(blockSprite.getY() - 30 * Gdx.graphics.getDeltaTime());
	}
}
