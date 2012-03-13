package nl.uva.mobilesystems.mathdefender;

import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.game.Level;
import nl.uva.mobilesystems.mathdefender.game.SuperMarketLevel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.objects.Player;

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
		
		final float centerX = 100;
		final float centerY = 100;
		this.player = new Player(centerX, centerY, TexMan.getIt().mPlayerTextureRegion, objectManager, TexMan.getIt().playerFont, this);
		Log.v("testingmarket", "Player created: " + this.getPlayer());
		scene.attachChild(this.player);
		
		
		this.currentLevel = new Level(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, objectManager, enemyFont, this);
		this.objectManager = objectManager;
		this.explosionFont = enemyFont;
		
		currentLevel = new SuperMarketLevel(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, objectManager, enemyFont, this, 15);
		
		this.objectManager = objectManager;
		this.explosionFont = enemyFont;
	}		
}