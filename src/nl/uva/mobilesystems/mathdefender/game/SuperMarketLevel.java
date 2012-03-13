package nl.uva.mobilesystems.mathdefender.game;

import nl.uva.mobilesystems.mathdefender.GameModel;

import org.andengine.entity.IEntity;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

public class SuperMarketLevel extends Level {
	
	public int myBudget;
	
	public SuperMarketLevel(int difficulty, int nrWaves, int nrTowers,
			Point screenDimensions,
			VertexBufferObjectManager objectManager,
			GameModel model, int budget)
	{
		super(difficulty, nrWaves, nrTowers, screenDimensions,
				objectManager, model);
		Log.v("testingmarket", "SMLevel created");
		this.myBudget = budget;
		model.getPlayer().setScore(budget);

	}

	public void startNewWave()
	{
		Log.v("testingmarket", "Waves left: " + this.getWaves().size());
		if(this.getWaves().size() == 0)
		{
			model.nextLevel();
		}
		else
		{
			this.setCurrentWave(this.getWaves().poll());
			for(IEntity object : this.getCurrentWave().getObjects())
			{
				model.addObjectToScene(object);
			}
		}
	}
	
	
}
