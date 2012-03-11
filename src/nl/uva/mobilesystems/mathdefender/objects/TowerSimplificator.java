package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TowerSimplificator extends Tower {

	public TowerSimplificator(GameModel model, float X, float Y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(model, X, Y, TexMan.getIt().mTowerSimpTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}


	
}
