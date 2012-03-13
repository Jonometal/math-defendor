package nl.uva.mobilesystems.mathdefender;

import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.game.SuperMarketLevel;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.util.Log;

import android.graphics.Point;

/**
 * Tobi's class
 * 
 * @author siemionides
 *
 */
public class GameSuperMarketModel extends GameModel{

	public GameSuperMarketModel(InitialActivity activity, Scene scene) {
		super(activity, scene);
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
	public void setUpSimpleGame(int difficulty, int nrWaves, int nrTowers, Point screenDimensions, TiledTextureRegion textureEnemy,
							VertexBufferObjectManager objectManager, Font enemyFont)
	{
		Log.v("testingmarket", "Market running, over.2");
		currentLevel = new SuperMarketLevel(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, objectManager, enemyFont, this, 15);
		
		this.objectManager = objectManager;
		this.explosionFont = enemyFont;
	}		
}