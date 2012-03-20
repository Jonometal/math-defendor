package nl.uva.mobilesystems.mathdefender.objects;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.InitialActivity;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.utils.HelperClass;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.AlertDialog;
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
public abstract class Tower extends TiledSprite{
	
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

	// -------------------- SETTERS & GETTERS 
	public LinkedList<TowerBullet> getBullets() {
		return bullets;
	}


	public void setBullets(LinkedList<TowerBullet> bullets) {
		this.bullets = bullets;
	}
	
	public GameModel getModel(){
		return this.model;
	}
	
	public float getSecondsElapsed() {
		return secondsElapsed;
	}


	public void setSecondsElapsed(float secondsElapsed) {
		this.secondsElapsed = secondsElapsed;
	}
	
	// -------------------- CONSTRUSTORS
	
	


	/**
	 * Constructor
	 * @param model
	 * @param towerType Type of the tower, See {@link Tower#TYPE_KILLER}, {@link Tower#TYPE_SIMPLIFY} {@link Tower#TYPE_SLOWS_DOWN}
	 * @param X
	 * @param Y
	 * @param pTowerTiledTextureRegion
	 * @param pTiledTowerBulletTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public Tower(GameModel model, final float X, final float Y, TiledTextureRegion towerTexture, final VertexBufferObjectManager pVertexBufferObjectManager){
		super(X, Y, towerTexture, pVertexBufferObjectManager);

		
		this.model = model;
		this.objectManager = pVertexBufferObjectManager;
		
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		
		this.bullets = new LinkedList<TowerBullet>();
	}
	
	
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
//		if(this.model.engine.isRunning()){
//			this.model.scene.
//			this.model.engine.stop();
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			this.model.engine.start();
//		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this.model.activity);
		builder.setMessage("hello!");
		AlertDialog aloert = builder.create();
		
			
		return true;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	/** When you detected Bullet collision call TowerBullet.collisionDetected() rather than this.
	 * The former will this method. */
	public void collisionBulletDetected(){
		this.increaseBulletsAvailable(1);	//increase tower's bullet by 1
		
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
			bullets.add(newBullet);
//			this.attachChild(newBullet);
			this.model.addObjectToScene(newBullet);
			bulletsAvailable--;
			return true;
		}else
			return false; //no bullets available
	}


	
	
}
