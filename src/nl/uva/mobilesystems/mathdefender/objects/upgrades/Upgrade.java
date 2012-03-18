package nl.uva.mobilesystems.mathdefender.objects.upgrades;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Class representing flyable objects, "bonuses" that come in the last wave
 * @author siemionides
 *
 */
public class Upgrade extends AnimatedSprite {

	
	private ObjectPositionEventListener listener; 
	
	private PhysicsHandler mPhysicsHandler;
	public Upgrade(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(-PhConstants.ENEMY_VELOCITY*2, 0);
		this.mPhysicsHandler.setAccelerationX(200);
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {

//		if(this.getX() <= GUIConstants.CAMERA_WIDTH/3*2)
//			this.mPhysicsHandler.setVelocity(0);
		if(this.mPhysicsHandler.getVelocityX() >= 0){
			this.mPhysicsHandler.setVelocity(0);
			this.mPhysicsHandler.setAcceleration(0);
		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	public synchronized Upgrade addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
		return this;
	}
	
	
	
	/**
	 * Currently those are collisions with Player
	 */
	public void collisionDetected(){
		fireEvent(EventsConstants.EVENT_OBJECT_UPGRADE_OUT_OF_SCENE);
	}

	public synchronized void removeObjectPositionEventListener(){
		this.listener = null;
	}
	
	private synchronized void fireEvent(int eventCode){
		ObjectPositionEvent event = new ObjectPositionEvent(this, eventCode);
		this.listener.handleObjectPositionEvent(event);
	}
}
