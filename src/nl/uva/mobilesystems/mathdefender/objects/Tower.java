package nl.uva.mobilesystems.mathdefender.objects;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.physics.HelperClass;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;
/**
 * Class that will represent Tower that's going to shoot towards Enemies.
 * Currently it extends TiledSprite (no just sprite, no animation) but we can add
 * Animation functionality further.
 * 
 * 
 * @author siemionides
 *
 */
public class Tower extends TiledSprite{
	
	/**
	 * It's ugly but.. this way GameModel can be registered as a listener of TowerBulletEvents (getting out of the scene)
	 */
	private GameModel model;
	
	private final PhysicsHandler mPhysicsHandler;
	
	private LinkedList<TowerBullet> bullets;
	
	private int bulletsAvailable = PhConstants.TOWER_MAX_BULLETS;
	
	//texture and this buffer-sth that should be kept in oder to start TowerBullet
	private ITiledTextureRegion pTowerBulletTiledTextureRegion;
	
	private VertexBufferObjectManager objectManager;
	
	/** Tower needs some reload time between shots.*/
	private float secondsElapsed = 0;

	/**
	 * Constructor
	 * @param model
	 * @param X
	 * @param Y
	 * @param pTowerTiledTextureRegion
	 * @param pTiledTowerBulletTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public Tower(GameModel model,final float X, final float Y,  ITiledTextureRegion pTowerTiledTextureRegion,
			final ITiledTextureRegion pTiledTowerBulletTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager){
		
		super(X, Y, pTowerTiledTextureRegion, pVertexBufferObjectManager);
		this.model = model;
		this.pTowerBulletTiledTextureRegion = pTiledTowerBulletTextureRegion;
		this.objectManager = pVertexBufferObjectManager;
		
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
	}
	
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		Log.v("tower", "touched");
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
		shotAt(100,100);
		return true;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.secondsElapsed += pSecondsElapsed;
		for(AnimatedSprite object : model.getCurrentWaveObjects()){
			if(object instanceof Enemy && 
					HelperClass.calculateDistance(this.getX() ,this.getY(),object.getX() , object.getY()) < PhConstants.TOWER_RANGE){
				if(this.secondsElapsed >= PhConstants.TOWER_RELOAD_TIME){
					//so Enemy  is within our range AND it was enough time -> Fire at HIM!
					shotAt(object.getX(),object.getY());
					this.secondsElapsed = 0;
				}
			}
		}
//		Log.d("towerBullet2",  + this.getX() + " " + this.getY());
//		if (HelperClass.isOutSideScene(this.getX(), this.getY())){
//			fireEvent(EventsConstants.EVENT_OBJECT_BULLET_OUT_OF_SCENE);
//		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	
	public void increaseBulletsAvailable(int bullets){
		this.bulletsAvailable += bullets;
	}
	
	
	/**
	 * Shoots (if possible) towards given direction. Creates TowerBullet objects and adds it to the scene.
	 * 
	 * @param _x
	 * @param _y
	 * @return
	 */
	public boolean shotAt(final float _x, final float _y){
		if(bulletsAvailable > 0){
			
			TowerBullet newBullet = new TowerBullet(_x-this.getX(), _y-this.getY(),this, pTowerBulletTiledTextureRegion, objectManager);
			newBullet.addObjectPositionEventListener(model);
//			this.attachChild(newBullet);
			this.model.addObjectToScene(newBullet);
			bulletsAvailable--;
			return true;
		}else
			return false; //no bullets available
	}
	
	
}
