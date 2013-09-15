package core;

public final class Constants {
	/**
	 * The minimum time between obstacle creation
	 */
	public static final float OBSTACLE_RESPAWN_COOLDOWN = 1.0f;
	
	/**
	 * Chance of obstacle creation immediately afternoon cooldown elapses
	 */
	public static final float OBSTACLE_RESPAWN_BASE_CHANCE = 0.1f;
	
	/**
	 * Increase in obstacle creation chance per frame
	 */
	public static final float OBSTACLE_RESPAWN_CHANCE_INCREMENT = 0.005f;
	
	/**
	 * Duration of Frames for Sprite animation
	 */
	public static final float ACTOR_FRAME_DURATION = 0.05f;
	
	public static final int ACTOR_FRAME_COLS = 4; 
	public static final int ACTOR_FRAME_ROWS = 1; 
}
