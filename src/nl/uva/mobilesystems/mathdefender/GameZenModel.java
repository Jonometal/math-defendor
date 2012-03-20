package nl.uva.mobilesystems.mathdefender;

import java.util.Iterator;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.game.ZenLevel;
import nl.uva.mobilesystems.mathdefender.gui.OurHUD;
import nl.uva.mobilesystems.mathdefender.gui.SceneManager;
import nl.uva.mobilesystems.mathdefender.objects.Player;
import nl.uva.mobilesystems.mathdefender.objects.Tower;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.Upgrade;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

/**
 * Represents Michal's mode of the game - ZEN
 * @author siemionides
 *
 */
public class GameZenModel extends GameModel {


	
	public GameZenModel(InitialActivity activity, Scene scene, OurHUD _hud) {
		super(activity, scene, _hud);

	}
	
	
	public void handleObjectPositionEvent(ObjectPositionEvent e) {
		super.handleObjectPositionEvent(e);
		
		//additional funtionality that is mode-dependend goes here
		switch(e.getEventCode()){
		case EventsConstants.EVENT_OBJECT_UPGRADE_OUT_OF_SCENE:
			//so bonus object was hit, we should start new wave now!
			Iterator<AnimatedSprite> iter = this.currentLevel.getCurrentWave().getObjects().iterator();
			AnimatedSprite upgradeObject;
			while(iter.hasNext()){
				upgradeObject = iter.next();
				if(upgradeObject instanceof Upgrade){
					removeObjectFromScene(upgradeObject);
//					iter.remove();
				}
			}
//			wavesPassedSinceTheBeginnig += this.currentLevel.getNrWaves();
			SceneManager.showZenLevelFinishedScene(this.player.getScore(), wavesPassedSinceTheBeginnig);	
			nextLevel();
			break;
		}

	}
	
	/**
	 * Generates next Level according to current user's performance.
	 */
	public void nextLevel(){
		
		int numberOfWaves = 3;
		//Erase all of the towers on-screen FIRST
		if(this.currentLevel != null && this.currentLevel.getTowers() != null){
			Iterator<Tower> iter = this.currentLevel.getTowers().iterator();
			Tower t = null;
			while(iter.hasNext()){
				t = iter.next();
				removeObjectFromScene(t);
				iter.remove();
			}
		}
		
		if(this.currentLevel == null){
			this.currentLevel = new ZenLevel(1, numberOfWaves, 0, screenDimensions, objectManager, this);
		}else{
			this.currentLevel = new ZenLevel(this.currentLevel.getDifficulty()+1, numberOfWaves, 0, screenDimensions, objectManager, this);
			this.player.setPosition(PhConstants.PLAYER_START_POSITION_X, PhConstants.PLAYER_START_POSITION_Y);
		}
		
		
		
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
	public void setUpSimpleGame(Point screenDimensions, VertexBufferObjectManager _objectManager){
		super.setUpSimpleGame(screenDimensions, _objectManager);
//		super.setUpSimpleGame(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, 
//							 objectManager, enemyFont);
	
		
		this.screenDimensions = screenDimensions;
		this.player = new Player(PhConstants.PLAYER_START_POSITION_X, PhConstants.PLAYER_START_POSITION_Y, objectManager, this);
		Log.v("testingmarket", "Player created: " + this.getPlayer());
		scene.attachChild(this.player);
		nextLevel();
		
	}
}
