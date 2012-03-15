package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeBulletTime;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeTowerSimplificator;
import nl.uva.mobilesystems.mathdefender.objects.upgrades.UpgradeTowerSlowDowner;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;

public class ZenLevel extends Level {

	public ZenLevel(int difficulty, int nrWaves, int nrTowers,
			Point screenDimensions, VertexBufferObjectManager objectManager,
			GameModel model) {
		
		super(difficulty, nrWaves, nrTowers, screenDimensions, objectManager, model);
		
		//add also bonus wave at the end
		this.waves.offer(createBonusWave());
		
	}
	
	public Wave createBonusWave(){
		float x = GUIConstants.CAMERA_WIDTH;
		float y = GUIConstants.CAMERA_HEIGHT /4;
		
		
		LinkedList<AnimatedSprite> bonusShop = new LinkedList<AnimatedSprite>();
			bonusShop.add(new UpgradeBulletTime(x, y, this.objectManager));
			bonusShop.add(new UpgradeTowerSimplificator(x, y*2, this.objectManager));
			bonusShop.add(new UpgradeTowerSlowDowner(x, y*3, this.objectManager)); 

		
		return new Wave(bonusShop);
	}
	
	

}
