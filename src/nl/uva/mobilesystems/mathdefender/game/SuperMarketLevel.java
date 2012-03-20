package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

public class SuperMarketLevel extends Level {
	
	public int myBudget;
	private GameModel myModel;
	String[]array1 = {														//Demosums are written here, will only work if exact right number of waves and sums per wave are set
			"2+3","9-9","-1+6","4-8",													//Level 1
			"-17+15","-3+6","8-13","20-16",	
			"33-37","-54+53","-29+33","4+1",	
			"64-58","-62+58","-53+59","40-39",	
			"-78+79","93-89","48-52","42-42"			
			};
	String[]array2 = {														//Demosums are written here, will only work if exact right number of waves and sums per wave are set
			"2+8-5","9-3+12","-1+8-2","5-4-5",													//Level 1
			"-17+6+7","-5-6+14","8-10-3","20-8-8",	
			"25-37+8","-24+13+10","-15+23-4","12-9+2",	
			"44-28-10","-82+58+21","-53+56+2","40-39+1",	
			"-38+29+9","93-99+12","48-12-40","42-42+0"			
			};
	String[]array3 = {														//Demosums are written here, will only work if exact right number of waves and sums per wave are set
			"8*9","6*7","150/3","192/8",													//Level 1
			"180/6","11*9","17*3","451/11",	
			"198/2","6*9","136/3","400/50",	
			"9*9","212/4","31*1","900/30",	
			"-6*-19","-144/-12","4*13","1764/42"
			};
	String[]array4 = {														//Demosums are written here, will only work if exact right number of waves and sums per wave are set
			"6*(12-6)","9-9","-1+6","4-8",													//Level 1
			"2*4-2","-3+6","8-13","20-16",	
			"4-8/2","-54+53","-29+33","4+1",	
			"64-58","-62+58","-53+59","40-39",	
			"-78+79","93-89","48-52","42-42"
			};
	String[]array5 = {														//Demosums are written here, will only work if exact right number of waves and sums per wave are set
			"2+3","9-9","-1+6","4-8",													//Level 1
			"-17+15","-3+6","8-13","20-16",	
			"33-37","-54+53","-29+33","4+1",	
			"64-58","-62+58","-53+59","40-39",	
			"-78+79","93-89","48-52","42-42"
			};
	private LinkedList<String> demoSums = new LinkedList<String>();

	
	public SuperMarketLevel(int difficulty, int nrWaves, int nrTowers,
			Point screenDimensions,
			VertexBufferObjectManager objectManager,
			GameModel model, int budget)
	{
		super(difficulty, nrWaves, nrTowers, screenDimensions,
				objectManager, model);
		//Log.v("testingmarket", "SMLevel created with diff: " + difficulty + this.myDiff);
		this.myBudget = budget;
		this.myModel = model;
		model.getPlayer().setScore(budget);
		if (model.demo)
		{
			switch (myDiff)
			{
						case 1:
							for(String string : array1)
							{
								demoSums.add(string);
							}
							break;
						case 2:
							for(String string : array2)
							{
								demoSums.add(string);
							}
							break;
						case 3:
							for(String string : array3)
							{
								demoSums.add(string);
							}
							break;
						case 4:
							for(String string : array4)
							{
								demoSums.add(string);
							}
							break;
						case 5:
							for(String string : array5)
							{
								demoSums.add(string);
							}
							break;
			}
		}
	}

	public void startNewWave()
	{
		//Log.v("testingmarket", "Waves left: " + this.wavesLeft);
		if(this.wavesLeft == 0)
		{
			this.model.nextLevel();
		}
		else
		{			
			this.wavesLeft --;
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			
			
				for(int j=0; j< PhConstants.NR_ENEMIES_IN_WAVE; j++)
				{ //generating enemies
					//Log.v("testingmarket", "Creating Enemy with: " + this.myDiff);
					int x = this.model.screenDimensions.x; //the edge of a screen
					int y = (model.screenDimensions.y / (PhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1)) - 10;	//so equal distribution on screen Width
					
					if (myModel.demo)
					{
						Log.v("testingDemo", "Creating DemoWave");
						Enemy tempEnemy = new Enemy(x,y, this.model.objectManager, this.myDiff, this.model, TexMan.getIt().mSupermarketEnemyTextureregion);
						tempEnemy.setSum(demoSums.getFirst());
						demoSums.removeFirst();
						tempEnemy.addObjectPositionEventListener(this.model);
						tempEnemies.add(tempEnemy);
					}
					else
					{					
						Enemy tempEnemy = new Enemy(x,y, this.model.objectManager, this.myDiff, this.model, TexMan.getIt().mSupermarketEnemyTextureregion);
						tempEnemy.addObjectPositionEventListener(this.model);
						tempEnemies.add(tempEnemy);
					}
				}
				Wave tempWave = new Wave(tempEnemies);
				//Log.v("testingmarket", "new Wave created ");
				this.waves.offer(tempWave);
				
				this.setCurrentWave(this.getWaves().poll());
				for(IEntity object : this.getCurrentWave().getObjects())
				{
					this.model.addObjectToScene(object);
				}
			
		}
	}
}
