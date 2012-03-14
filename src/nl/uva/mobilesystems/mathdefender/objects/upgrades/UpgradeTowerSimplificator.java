package nl.uva.mobilesystems.mathdefender.objects.upgrades;

import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class UpgradeTowerSimplificator extends AnimatedSprite {

	public UpgradeTowerSimplificator(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, TexMan.getIt().mUpgradeTowerSimplificatorTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

}
