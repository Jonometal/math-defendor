package nl.uva.mobilesystems.mathdefender;

import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;

import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;

/**
 * Represents Michal's mode of the game - ZEN
 * @author siemionides
 *
 */
public class GameZenModel extends GameModel {

	public GameZenModel(InitialActivity activity, Scene scene) {
		super(activity, scene);

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
	public void setUpSimpleGame(int difficulty, int nrWaves, int nrTowers, Point screenDimensions, TiledTextureRegion textureEnemy,
								TiledTextureRegion textureTower, TiledTextureRegion textureTowerBullet,
								VertexBufferObjectManager objectManager, Font enemyFont){
		super.setUpSimpleGame(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, 
							textureTower, textureTowerBullet, objectManager, enemyFont);
		
	}
	
	public void setNewTowerAt(final float X, final float Y, TiledTextureRegion pTowerTiledTextureRegion,TiledTextureRegion pTowerBulletTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ){
		super.setNewTowerAt(X, Y, pTowerTiledTextureRegion, pTowerBulletTiledTextureRegion, pVertexBufferObjectManager);
	}


}
