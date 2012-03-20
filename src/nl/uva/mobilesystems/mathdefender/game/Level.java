package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.objects.Tower;
import nl.uva.mobilesystems.mathdefender.objects.TowerKiller;
import nl.uva.mobilesystems.mathdefender.objects.TowerSimplificator;
import nl.uva.mobilesystems.mathdefender.objects.TowerSlower;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.entity.IEntity;
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
	protected int myDiff;

	//VARIABLES
	protected int difficulty;

	protected GameModel model;
	protected VertexBufferObjectManager objectManager;
	
	/** All of the towers that can be */
	protected LinkedList<Tower> towers;
	
	protected int nrWaves;
	protected int wavesLeft;
	protected Wave currentWave;
	
	protected LinkedList<Wave> waves;
	
	
	public Level(int difficulty, int nrWaves, int nrTowers, Point screenDimensions,
			VertexBufferObjectManager objectManager, GameModel model)
	{
		this.setWaves(new LinkedList<Wave>());
		this.myDiff = difficulty;
		this.model = model;
		this.nrWaves = nrWaves;
		this.wavesLeft = this.nrWaves;
		this.objectManager = objectManager;
		// it's little bit Debug in here, so manually attaching different Towers to screen
		setNewTowerAt(350, 400, objectManager);		
	}
	
	public void startNewWave()
	{
		;
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
	
	public int getNrWaves(){
		return this.nrWaves;
	}


	public void setCurrentWave(Wave currentWave) {
		this.currentWave = currentWave;
	}
	
	
	public LinkedList<Tower> getTowers() {
		int a = 4;
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
//		Tower newTower = new TowerSimplificator(this.model, X,Y, pVertexBufferObjectManager);
//		this.addTower(newTower);
//		this.model.addObjectToScene(newTower);
//		this.model.scene.registerTouchArea(newTower);
		
		Tower newSlowindDownTower = new TowerSimplificator(this.model, 350, 100, pVertexBufferObjectManager);
		this.addTower(newSlowindDownTower);
		this.model.addObjectToScene(newSlowindDownTower);
		this.model.scene.registerTouchArea(newSlowindDownTower);
		// Tower newTower = new TowerSimplificator(this.model, X,Y, pVertexBufferObjectManager);
//		Tower newTower = new TowerKiller(this.model, X,Y, pVertexBufferObjectManager);
//		this.addTower(newTower);
//		this.model.addObjectToScene(newTower);
//		this.model.scene.registerTouchArea(newTower);
	}
	// ------------------- PRIVATE METHODS
	
	
}
