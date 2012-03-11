package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TowerKiller extends Tower {

	public TowerKiller(GameModel model, float X, float Y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(model, X, Y, TexMan.getIt().mTowerKillerTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

}
