package nl.uva.mobilesystems.mathdefender.andengine;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import android.graphics.PointF;

/**
 * Class representing player's drone.
 * @author siemionides
 *
 */
public class AndPlayer extends AnimatedSprite{
	
	private final PhysicsHandler mPhysicsHandler;
	
	

	public AndPlayer(final float pX, final float pY, final TiledTextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager){
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
//		this.mPhysicsHandler.setVelocity(AndPhConstants.DEMO_VELOCITY, AndPhConstants.DEMO_VELOCITY);
		
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		;
	}
	
	public PhysicsHandler getPhysicsHanlder(){
		return this.mPhysicsHandler;
	}


	
}
