package nl.uva.mobilesystems.mathdefender.physics;

/**
 * Class containing constants related to physics.
 * @author siemionides
 *
 */
public class PhConstants {
	public static final float DEMO_VELOCITY = 100.0f;
	
	public static final float ENEMY_VELOCITY = 150.0f;
	
	public static final int NR_ENEMIES_IN_WAVE = 4;
	
	// PLAYER
	
	public static final float PLAYER_VELOCITY = 350.0f;
	
	public static final float PLAYER_SWIPE_JUMP = 300f;
	
	
	// TOWERS
	public static final int TOWER_MAX_BULLETS = 4;
	
	public static final float TOWER_BULLET_SPEED = 350f;
	
	public static final float TOWER_RANGE = 250f; //the radius of area in which Tower can detect enemies and start shooting at them
	
	public static final float TOWER_RELOAD_TIME = 0.5f; //in seconds I guess
	
	public static final float TOWER_SLOWER_RATIO = 0.5f;
}
