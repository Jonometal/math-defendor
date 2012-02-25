package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

/**
 * Level class
 * @author siemionides
 *
 */
public class Level {
	
	public static int DIFF_TUTORIAL = 0;
	public static int DIFF_EASY = 1;
	public static int DIFF_MEDIUM = 2;
	public static int DIFF_HARD = 3;

	
	private int difficulty;
	
	private Wave currentWave;
	
	private LinkedList<Wave> waves;
	
	

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public Level(int difficulty){
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
}
