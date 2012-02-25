package nl.uva.mobilesystems.mathdefender.objects;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
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
	
	private final PhysicsHandler mPhysicsHandler;


	public Tower(final float X, final float Y,  ITiledTextureRegion pTiledTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager){
		super(X, Y, pTiledTextureRegion, pVertexBufferObjectManager);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
	}
	
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

		return true;
	}
	
	
}
