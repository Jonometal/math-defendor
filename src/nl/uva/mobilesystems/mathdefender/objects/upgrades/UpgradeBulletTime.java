package nl.uva.mobilesystems.mathdefender.objects.upgrades;

import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class UpgradeBulletTime extends Upgrade {

	public UpgradeBulletTime(float pX, float pY,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, TexMan.getIt().mUpgradeBuletTimeTextureRegion, pVertexBufferObjectManager);
		
	}

}
