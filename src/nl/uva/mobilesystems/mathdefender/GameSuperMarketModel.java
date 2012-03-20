package nl.uva.mobilesystems.mathdefender;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.game.SuperMarketLevel;
import nl.uva.mobilesystems.mathdefender.gui.OurHUD;
import nl.uva.mobilesystems.mathdefender.objects.Player;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

/**
 * Tobi's class
 * 
 * @author siemionides
 *
 */
public class GameSuperMarketModel extends GameModel{

	
	
	public GameSuperMarketModel(InitialActivity activity, Scene scene, OurHUD hud) {
		super(activity, scene, hud);
		Log.v("testingmarket", "Market running, over.");
	}
	
	
	public void handleObjectPositionEvent(ObjectPositionEvent e) {
		super.handleObjectPositionEvent(e);
		
		

	}
	

	
	/**
	 * See GameModel.performGlobalCollisionTest() for more details.
	 */
	public void performGlobalCollisionTest(){
		super.performGlobalCollisionTest();
	}
	
	/** 
	* Extends the base class' method + may give sth more in the future.
	 */
	@Override
	public void setUpSimpleGame(Point screenDimensions,	VertexBufferObjectManager objectManager)
	{
		Log.v("testingmarket", "Market running, over.2");
		
		final float centerX = 100;
		final float centerY = 100;
		this.screenDimensions = screenDimensions;
		this.player = new Player(centerX, centerY, objectManager, this);
		Log.v("testingmarket", "Player created: " + this.getPlayer());
		scene.attachChild(this.player);
		
		
		//this.currentLevel = new Level(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, objectManager, enemyFont, this);
		this.objectManager = objectManager;
		Log.v("testingmarket", "creating SMLevel with these vars: " + 15 );
		nextLevel();
		
	}	
	
	@Override
	public void nextLevel()
	{
		levelCounter++;
		Log.v("testingmarket", "creating SMLevel with counter = " + levelCounter );
	
		switch (levelCounter)
		{
					case 1:
						currentLevel = new SuperMarketLevel(1, 5, 0, screenDimensions, objectManager,  this, 15);
						currentLevel.startNewWave();
						break;
					case 2:
						currentLevel = new SuperMarketLevel(2, 5, 0, screenDimensions, objectManager, this, 15);
						currentLevel.startNewWave();
						break;
					case 3:
						currentLevel = new SuperMarketLevel(3, 5, 0, screenDimensions, objectManager, this, 250);
						currentLevel.startNewWave();
						break;
					case 4:
						currentLevel = new SuperMarketLevel(4, 5, 0, screenDimensions, objectManager, this, 500);
						currentLevel.startNewWave();
						break;
					//case 5:
						//currentLevel = new SuperMarketLevel(6, 5, 0, screenDimensions, objectManager, this, 1000);
						//currentLevel.startNewWave();
						//break;
					default:
						break;
		}
	}
}