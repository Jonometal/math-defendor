package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import nl.uva.mobilesystems.mathdefender.utils.HelperClass;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TowerKiller extends Tower {

	public TowerKiller(GameModel model, float X, float Y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(model, X, Y, TexMan.getIt().mTowerKillerTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.setSecondsElapsed(this.getSecondsElapsed() + pSecondsElapsed);
		for(AnimatedSprite object : getModel().getCurrentWaveObjects()){
			if(object instanceof Enemy && 
					HelperClass.calculateDistance(this.getX() ,this.getY(),object.getX() , object.getY()) < PhConstants.TOWER_RANGE){
				if(this.getSecondsElapsed() >= PhConstants.TOWER_RELOAD_TIME){
					//so Enemy  is within our range AND it was enough time -> Fire at HIM!
					shotAt(object.getX(),object.getY());
					this.setSecondsElapsed(0);
				}
			}
		}
		super.onManagedUpdate(pSecondsElapsed);
	}

}
