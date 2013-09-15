package controllers;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import core.Og;

import enums.ObstacleType;

import actors.Obstacle;

/**
 * Controller method for creation, storage and removal of obstacles. Will create
 * obstacles at random points in time, and remove them when they are no longer
 * displayed
 * 
 * @author Andy
 * 
 */
public class ObstacleController {
	private static Dictionary<ObstacleType, Texture> textures = new Hashtable<ObstacleType, Texture>();
	private static boolean areTexturesLoaded = false;
	
	final private float baseCreationChance = 0.1f;
	final private float baseCreationCooldown = 1.0f;
	
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();	
	private float creationCooldown = 0.0f;
	private float creationChance = baseCreationChance;
	private Random rand;
	
	// Used to dispose of off screen obstacles
	private ArrayList<Obstacle> deadObstacles = new ArrayList<Obstacle>();

	/**
	 * Create a new Obstacle Controller
	 */
	public ObstacleController() {
		if(!ObstacleController.areTexturesLoaded){
			ObstacleController.loadTextures();
		}
		rand = new Random();
	}

	/**
	 * Call the update method of all the obstacles
	 */
	public void update() {
		for (Obstacle o : obstacles) {
			o.update();
			if (isObjectOffScreen(o)) {
				o.dispose();
				deadObstacles.add(o);
			}
		}
		if(deadObstacles.size() > 0){
			obstacles.removeAll(deadObstacles);
			deadObstacles.removeAll(deadObstacles);
		}
		generate();
	}

	/**
	 * Loop over the loaded Obstacles and dispose them.
	 * Loop over the loaded textures and dispose them.
	 * Flip the areTexturesLoaded flag back to false.
	 */
	public void dispose(){
		for(Obstacle o : obstacles){
			o.dispose();
		}
		obstacles.removeAll(obstacles);
		for(ObstacleType type : ObstacleType.values()){
			Texture tex = ObstacleController.textures.get(type);
			ObstacleController.textures.remove(tex);
			tex.dispose();
		}
		ObstacleController.areTexturesLoaded = false;
	}
	
	/**
	 * Getter for the obstacle list. Will be called by render to allow for batch
	 * drawing of obstacles
	 * 
	 * @return
	 */
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * Check to see if an obstacle is outside the bounds of the screen
	 * 
	 * @param obstacle
	 * @return
	 */
	private boolean isObjectOffScreen(Obstacle obstacle) {
		if (obstacle.getX() > Og.getWidth() / 2
				|| obstacle.getX() < -Og.getWidth() / 2) {
			return true;
		}
		return false;
	}

	/**
	 * Decide randomly if we want to create a new obstacle this frame (if we
	 * haven't created one recently)
	 */
	private void generate() {
		if (creationCooldown > 0) {
			creationCooldown -= Gdx.graphics.getDeltaTime();
			return;
		}
		creationCooldown = 0;
		if (rand.nextFloat() < creationChance * Gdx.graphics.getDeltaTime()) {
			createRandomObstacle();
			creationCooldown = baseCreationCooldown;
			creationChance = baseCreationChance;
		} else {
			creationChance += 0.001f * Gdx.graphics.getDeltaTime();
		}
	}

	/**
	 * Select a random obstacle type from list of all types, then generate a new
	 * obstacle of that type
	 */
	private void createRandomObstacle() {
		int randInt = rand.nextInt(ObstacleType.values().length);
		ObstacleType type = ObstacleType.values()[randInt];
		createObstacle(type);
	}

	/**
	 * Create a new obstacle of the specified type and add it to the list of
	 * obstacles
	 * 
	 * @param type
	 */
	private void createObstacle(ObstacleType type) {
		try {
			Obstacle obstacle = new Obstacle(type,
					-Og.getWidth() / 2, 0);
			obstacles.add(obstacle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Static method for loading obstacle textures
	 */
	private static void loadTextures(){
		if(!ObstacleController.areTexturesLoaded){
			for(ObstacleType oType : ObstacleType.values()){
				Texture tex = new Texture(Gdx.files.local("assets/TestTexture.png"));
				ObstacleController.textures.put(oType, tex);
			}
			ObstacleController.areTexturesLoaded = true;
		}
	}
	
	/**
	 * Getter for Obstacle to grab the correct texture for itself
	 * @throws Exception 
	 */
	public static Texture getTexture(ObstacleType type) throws Exception{
		if(ObstacleController.areTexturesLoaded){
			return ObstacleController.textures.get(type);
		}
		throw new Exception("Textures have not been loaded");
	}
}
