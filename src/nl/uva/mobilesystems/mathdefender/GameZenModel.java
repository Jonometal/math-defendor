package nl.uva.mobilesystems.mathdefender;

import java.util.Iterator;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.game.ZenLevel;
import nl.uva.mobilesystems.mathdefender.gui.OurHUD;
import nl.uva.mobilesystems.mathdefender.objects.Player;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.Upgrade;

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
				
			nextLevel();
			break;
		}

	}
	
	/**
	 * Generates next Level according to current user's performance.
	 */
	public void nextLevel(){
		this.currentLevel = new ZenLevel(++levelCounter, 1, 0, screenDimensions, objectManager, this);
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
	
		
		final float centerX = 100;
		final float centerY = 100;
		this.screenDimensions = screenDimensions;
		this.player = new Player(centerX, centerY, objectManager, this);
		Log.v("testingmarket", "Player created: " + this.getPlayer());
		scene.attachChild(this.player);
		nextLevel();
		
	}
}
