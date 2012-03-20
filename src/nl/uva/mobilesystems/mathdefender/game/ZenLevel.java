package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeBulletTime;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeTowerSimplificator;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeTowerSlowDowner;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

public class ZenLevel extends Level {

	public ZenLevel(int difficulty, int nrWaves, int nrTowers,
			Point screenDimensions, VertexBufferObjectManager objectManager,
			GameModel model) {
		
		super(difficulty, nrWaves, nrTowers, screenDimensions, objectManager, model);
		
		
		this.startNewWave();
		//add also bonus wave at the end
		
		this.wavesLeft++; //bonus wave too!
		
	}
	
	public Wave createBonusWave(){
		float x = GUIConstants.CAMERA_WIDTH;
		float y = GUIConstants.CAMERA_HEIGHT /4;
		
		
		LinkedList<AnimatedSprite> bonusShop = new LinkedList<AnimatedSprite>();
		
		
			bonusShop.add(new UpgradeBulletTime(x, y, this.objectManager).addObjectPositionEventListener(this.model));
			bonusShop.add(new UpgradeTowerSimplificator(x, y*2, this.objectManager).addObjectPositionEventListener(this.model));
			bonusShop.add(new UpgradeTowerSlowDowner(x, y*3, this.objectManager).addObjectPositionEventListener(this.model)); 

		
		return new Wave(bonusShop);
	}
	
	@Override
	public void startNewWave(){
		super.startNewWave();
		if(this.wavesLeft > 1){
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			for(int j=0; j< PhConstants.NR_ENEMIES_IN_WAVE; j++)
			{ //generating enemies
				int x = model.screenDimensions.x; //the edge of a screen
				int y = (model.screenDimensions.y / (PhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1)) -10;	//so equal distribution on screen Width
				
				Enemy tempEnemy = new Enemy(x,y, model.objectManager, this.myDiff, model, TexMan.getIt().mEnemyTextureregion);
				tempEnemy.addObjectPositionEventListener(model);
				tempEnemies.add(tempEnemy);				
			}
			Wave tempWave = new Wave(tempEnemies);
			Log.v("newWave", "new Wave created ");
			this.waves.addFirst(tempWave);
		}else if(this.wavesLeft == 1){ //so that's the bonuse wave!{
			this.waves.offer(createBonusWave());
		}
		
		if(this.wavesLeft >= 0){
			this.setCurrentWave(this.getWaves().poll());
			
			for(IEntity object : this.getCurrentWave().getObjects())
			{
				model.addObjectToScene(object);
			}
			this.wavesLeft--;
		}
		
	}
	
	

}
