package nl.uva.mobilesystems.mathdefender.objects.upgrades;

import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class UpgradeTowerSlowDowner extends Upgrade {

	public UpgradeTowerSlowDowner(float pX, float pY,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, TexMan.getIt().mUpgradeTowerSlowerTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

}
