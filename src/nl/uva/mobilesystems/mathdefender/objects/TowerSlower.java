package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TowerSlower extends Tower {

	public TowerSlower(GameModel model, float X, float Y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(model, X, Y, TexMan.getIt().mTowerSlowDownerTextureRegion, pVertexBufferObjectManager);
		
		
		// TODO Auto-generated constructor stub
	}

}
