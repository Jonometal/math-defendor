package nl.uva.mobilesystems.mathdefender.game;

import nl.uva.mobilesystems.mathdefender.GameModel;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;

public class SuperMarketLevel extends Level {
	
	public int myBudget;
	
	public SuperMarketLevel(int difficulty, int nrWaves, int nrTowers,
			Point screenDimensions, TiledTextureRegion textureEnemy,
			VertexBufferObjectManager objectManager, Font enemyFont,
			GameModel model, int budget) {
		super(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy,
				objectManager, enemyFont, model);
		
		this.myBudget = budget;
		model.getPlayer().setScore(budget);

	}

}
