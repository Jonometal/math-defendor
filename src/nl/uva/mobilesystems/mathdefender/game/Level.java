package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.objects.Tower;
import nl.uva.mobilesystems.mathdefender.objects.TowerKiller;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;


/**
 * Level class. Contains all of teh objects that can occur on the Scene.
 * @author siemionides
 *
 */
public class Level {
	// CONSTANTS
	private int myDiff;

	//VARIABLES
	private int difficulty;

	private GameModel model;
	
	/** All of the towers that can be */
	private LinkedList<Tower> towers;
	
	private Wave currentWave;
	
	private LinkedList<Wave> waves;
	
	
	public Level(int difficulty, int nrWaves, int nrTowers, Point screenDimensions, TiledTextureRegion textureEnemy, 
			VertexBufferObjectManager objectManager,
			Font enemyFont, GameModel model)
	{
		this.setWaves(new LinkedList<Wave>());
		this.myDiff = difficulty;
		this.model = model;
		
		setNewTowerAt(350, 400, objectManager);
		
		for(int i=0; i<nrWaves; i++){
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			for(int j=0; j< PhConstants.NR_ENEMIES_IN_WAVE; j++)
			{ //generating enemies
				int x = screenDimensions.x; //the edge of a screen
				int y = screenDimensions.y / (PhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1);	//so equal distribution on screen Width
				
				Enemy tempEnemy = new Enemy(x,y, objectManager, difficulty, enemyFont, model);
				tempEnemy.addObjectPositionEventListener(model);
				tempEnemies.add(tempEnemy);
				
			}
			Wave tempWave = new Wave(tempEnemies);
			Log.v("newWave", "new Wave created ");
			this.waves.offer(tempWave);
		}
		
		this.setCurrentWave(this.getWaves().poll());
		
	}
	
	
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
	
	
	public LinkedList<Tower> getTowers() {
		return towers;
	}
	
	public void addTower(Tower tower)
	{
		if(this.towers == null)
			this.towers = new LinkedList<Tower>();
		this.towers.add(tower);
	}
	
	public void setTowers(LinkedList<Tower> towers) {
		this.towers = towers;
	}
	
	// ------------------- PUBLIC METHODS
	
	/**
	 * Sets new Tower to current Level and adds it to the scene.
	 * @param X
	 * @param Y
	 * @param pTiledTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public void setNewTowerAt(final float X, final float Y,  VertexBufferObjectManager pVertexBufferObjectManager ){
		// Tower newTower = new TowerSimplificator(this.model, X,Y, pVertexBufferObjectManager);
		Tower newTower = new TowerKiller(this.model, X,Y, pVertexBufferObjectManager);
		this.addTower(newTower);
		this.model.addObjectToScene(newTower);
		this.model.scene.registerTouchArea(newTower);
	}
	// ------------------- PRIVATE METHODS
	
	
}
