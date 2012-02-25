package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import android.graphics.PointF;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


/** Class representing single "enemy" in wave **/ 
public class Enemy extends AnimatedSprite{
	
	

	private ObjectPositionEventListener listener; 
	
	private final PhysicsHandler mPhysicsHandler;
	
	private int sum;
	
	public Enemy(final float pX, final float pY, final TiledTextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager){
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(-PhConstants.ENEMY_VELOCITY, 0);
		Entity a = new Entity();
	}
	

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	
	public synchronized void addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
	}
	
	
	public synchronized void removeObjectPositionEventListener(){
		this.listener = null;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {

		if (this.getX() < 0){
			fireEvent(EventsConstants.EVENT_OBJECT_OUT_OF_SCENE);
			
		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	private synchronized void fireEvent(int eventCode){
		ObjectPositionEvent event = new ObjectPositionEvent(this, eventCode);
		this.listener.handleObjectPositionEvent(event);
	}
	
	
	

}
