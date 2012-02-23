package nl.uva.mobilesystems.mathdefender.andengine;

import nl.uva.mobilesystems.mathdefender.gui.AndGUIConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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

		//don't let the player go outside Camera's bounds!
		if((this.getX() <= 0 && this.mPhysicsHandler.getVelocityX() < 0) || 
				(this.getX() >= AndGUIConstants.CAMERA_WIDTH - this.getWidth() && this.mPhysicsHandler.getVelocityX() > 0))
			this.mPhysicsHandler.setVelocityX(0);
		else if((this.getY() <= 0 && this.mPhysicsHandler.getVelocityY() < 0) || 
				(this.getY() >= AndGUIConstants.CAMERA_HEIGHT - this.getHeight() && this.mPhysicsHandler.getVelocityY() > 0))
			this.mPhysicsHandler.setVelocityY(0);
		
		super.onManagedUpdate(pSecondsElapsed);
		
	}
	
	public PhysicsHandler getPhysicsHanlder(){
		return this.mPhysicsHandler;
	}


	
}
