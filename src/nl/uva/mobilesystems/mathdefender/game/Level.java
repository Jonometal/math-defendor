package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import nl.uva.mobilesystems.mathdefender.objects.Tower;

/**
 * Level class. Contains all of teh objects that can occur on the Scene.
 * @author siemionides
 *
 */
public class Level {
	// CONSTANTS
	public static int DIFF_TUTORIAL = 0;
	public static int DIFF_EASY = 1;
	public static int DIFF_MEDIUM = 2;
	public static int DIFF_HARD = 3;

	//VARIABLES
	private int difficulty;
	
	/** All of the towers that can be */
	private LinkedList<Tower> towers;
	
	private Wave currentWave;
	
	private LinkedList<Wave> waves;
	
	
	
	//SETTERS/GETTERS
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	

	public LinkedList<Wave> getWaves() {
		return waves;
	}

	public void setWaves(LinkedList<Wave> waves) {
		this.waves = waves;
	}

	public Wave getCurrentWave() {
		return currentWave;
	}


	public void setCurrentWave(Wave currentWave) {
		this.currentWave = currentWave;
	}
	
	
	// CONSTRUCTORS
	public Level(int difficulty){
		this.difficulty = difficulty;
		this.towers = new LinkedList<Tower>();
	}

	public LinkedList<Tower> getTowers() {
		return towers;
	}

	public void setTowers(LinkedList<Tower> towers) {
		this.towers = towers;
	}
	
	// ------------------- PUBLIC METHODS
	
	// ------------------- PRIVATE METHODS
	
	
}
