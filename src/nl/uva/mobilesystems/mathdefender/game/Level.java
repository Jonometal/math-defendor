package nl.uva.mobilesystems.mathdefender.game;

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
	
	

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public Level(){
		;
	}
}
