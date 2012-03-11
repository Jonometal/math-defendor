package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import nl.uva.mobilesystems.mathdefender.utils.HelperClass;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

public class TowerSlower extends Tower {
	
	private float slowDownRatio = PhConstants.TOWER_SLOWER_RATIO;

	public TowerSlower(GameModel model, float X, float Y,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(model, X, Y, TexMan.getIt().mTowerSlowDownerTextureRegion, pVertexBufferObjectManager);
	}

	public float getSlowDownRatio() {
		return slowDownRatio;
	}

	public void setSlowDownRatio(float slowDownRatio) {
		this.slowDownRatio = slowDownRatio;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		this.setSecondsElapsed(this.getSecondsElapsed() + pSecondsElapsed);
		for(AnimatedSprite object : getModel().getCurrentWaveObjects()){
//			Log.d("Enemy", ((Enemy)object).getVelocityX() + " " + PhConstants.ENEMY_VELOCITY);
			if(object instanceof Enemy && 	//shot only towards Enemies moving with default speed
					HelperClass.calculateDistance(this.getX() ,this.getY(),object.getX() , object.getY()) < PhConstants.TOWER_RANGE &&
					Math.abs(((Enemy)object).getVelocityX()) == Math.abs(PhConstants.ENEMY_VELOCITY)){
				if(this.getSecondsElapsed() >= PhConstants.TOWER_RELOAD_TIME){
//					Log.d("Enemy", "shoooting!");
					//so Enemy  is within our range AND it was enough time -> Fire at HIM!
					shotAt(object.getX(),object.getY());
					this.setSecondsElapsed(0);
				}
			}
		}
		super.onManagedUpdate(pSecondsElapsed);
	}

}
