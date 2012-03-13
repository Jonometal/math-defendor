package nl.uva.mobilesystems.mathdefender;

import java.util.Iterator;
import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.game.Level;
import nl.uva.mobilesystems.mathdefender.game.Wave;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.objects.Explosion;
import nl.uva.mobilesystems.mathdefender.objects.Player;
import nl.uva.mobilesystems.mathdefender.objects.Tower;
import nl.uva.mobilesystems.mathdefender.objects.TowerBullet;

import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.graphics.Point;
import android.util.Log;

/** 
 *
 * @author siemionides
 *
 */
public class GameModel implements ObjectPositionEventListener {
	
	// ----------------------- VARIABLES --------------------------------
	/** Game Variables */
	public Engine engine; 		//Scene is public because Player class uses it, should be changed later on
	
	
	public Scene scene; //it's little bit awkward, it must be here because current implementation of Model starts drawing before InitialActivity.onCreateScene() method is finished, so engine variable (field in GameModel class) doesnt know about this scene yet
			
	
	/** Variable representing current level that is maninated by GameModel */
	protected Level currentLevel;
	protected int levelCounter = 0;
	public Point screenDimensions;
	public Player player; //public for experiments with PLayer class
	protected TiledTextureRegion myTextureEnemy;
	protected Font myEnemyFont;
	
	// Textures

	/** Debug things */
	
	private Text wavesLeftText; 
	protected VertexBufferObjectManager objectManager;
	protected Font explosionFont;
	
	public static LinkedList<AnimatedSprite> myEnemies;
	
	
	
	// ----------------------- CONSTRUCTORS --------------------------------
	
	public GameModel(InitialActivity activity, Scene scene){
		this.engine = activity.getEngine();			
		this.wavesLeftText = activity.text;
		this.scene = scene;
	}
	


	// ----------------------- SETTERS & GETTERS  --------------------------------
	
	
	
	public LinkedList<Tower> getTowers(){
		return this.currentLevel.getTowers();
	}
	
	public LinkedList<AnimatedSprite> getCurrentWaveObjects(){
		return this.currentLevel.getCurrentWave().getObjects();
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public LinkedList<Wave> getWavesLeft(){
		return this.currentLevel.getWaves();
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	
	// --------------------- OVERRIDDEN METHODS ------------------------------
	
	 
	public void handleObjectPositionEvent(ObjectPositionEvent e) {
		
		switch(e.getEventCode()){
		
		case EventsConstants.EVENT_OBJECT_ENEMY_OUT_OF_SCENE:
			Enemy object = (Enemy) e.getSource(); 
			removeObjectFromScene(object);
			currentLevel.getCurrentWave().removeObject(object);	//it's ugly, maybe it's better to create some method in Level that could be called instead of walking down trough this hierarchy?
			object.removeObjectPositionEventListener();
			object = null;
			Log.v("eventMine", "RemovesObject" + currentLevel.getCurrentWave().getObjects().size() + " " + currentLevel.getWaves().size() );
			if(currentLevel.getCurrentWave().getObjects().size() == 0)
			{ //check whether something is still in current Wave
				Log.v("testingmarket", "Starting New Wave"); 
				currentLevel.startNewWave();				
			}
			break;
		case EventsConstants.EVENT_OBJECT_BULLET_OUT_OF_SCENE:
			TowerBullet objectBullet = (TowerBullet) e.getSource();
			removeObjectFromScene(objectBullet);						//removes from Scene
			objectBullet.getTower().getBullets().remove(objectBullet);	//removes from ListofBullets
			objectBullet.removeObjectPositionEventListener();			//removes this listener
			objectBullet.getTower().increaseBulletsAvailable(1);
			break;
		} 
	}
	
	//-------------------- PUBLIC METHODS ----------------------------
	
	/** 
	 * Check GameZenModel or GameSuperMarketModel for implementations of those funtions for each of mode.
	 * 
	 * Ultra important and bad-coding style method; Sets waves, enemies in there 
	 */
	public void setUpSimpleGame(Point screenDimensions,	VertexBufferObjectManager objectManager){
//		Log.v("testingmarket", "Super running, over.");
//		final float centerX = 100;
//		final float centerY = 100;
//		this.screenDimensions = screenDimensions;
//		this.myTextureEnemy= textureEnemy;
//		this.myEnemyFont= enemyFont;
//		this.player = new Player(centerX, centerY, TexMan.getIt().mPlayerTextureRegion, objectManager, TexMan.getIt().playerFont, this);
//		Log.v("testingmarket", "Player created: " + this.getPlayer());
//		scene.attachChild(this.player);
//		
//		
//		this.currentLevel = new Level(difficulty, nrWaves, nrTowers, screenDimensions, textureEnemy, objectManager, enemyFont, this);
//		this.objectManager = objectManager;
//		this.explosionFont = enemyFont;
		
		
		
		
	}			
	
		
	/**
	 * This method is for code clarity.
	 * @param entity
	 */
	public void addObjectToScene(IEntity entity){
		this.scene.attachChild(entity);
	}
	
	/**
	 * Temporary (?) method that performs global collision check.
	 * Currently it iterated through: enemies and checks collisions with:
	 * - player
	 * - bullets shoted from Tower
	 */
	public void performGlobalCollisionTest() {
		//this is the fix to the problem that no new waves appear when you get all three enemies
		// This is horrible code though and should all be moved to the enemy/wave class.
//		if(currentLevel.getCurrentWave().getObjects().size() == 0)
//		{ //check whether something is still in current Wave
//			if( currentLevel.getWaves().size() > 0) //if there are still waves to be shown
//			{
//				startNewWave();
//			}
//		}
		Iterator<AnimatedSprite> iter = this.getCurrentWaveObjects().iterator();
		LinkedList<Tower> towers = this.getTowers();
		AnimatedSprite enemy;
		while(iter.hasNext()){
			enemy = iter.next();
			if(player.collidesWith(enemy)){			//collsion player <-> enemy
				iter.remove();
				player.collisionDetected((Enemy)enemy);
				((Enemy)enemy).collisionDetected(null);	//null becuse it's collision with Player
				Explosion explosion = new Explosion(((Enemy)enemy).getX(), ((Enemy)enemy).getY(),
						objectManager, this);
				this.addObjectToScene(explosion);
			}else
			{	//otherwise check for collisions with bullets
				Iterator<Tower> iterTower = towers.iterator();
				Tower tower;
				towerLoop: while(iterTower.hasNext()){
					tower = iterTower.next();
					if(tower.getBullets().size() == 0)	//no bullets for this tower, check next one
						continue;
					Iterator<TowerBullet> iterBullet = tower.getBullets().iterator();
					TowerBullet bullet;
					while(iterBullet.hasNext()){
						bullet = iterBullet.next();
						if(enemy.collidesWith(bullet)){ //collision enemy <-> bullet
							iterBullet.remove(); //remove bullet
//							iter.remove(); //remove enemy
							bullet.collisionDetected();
							((Enemy)enemy).collisionDetected(bullet.getTower());
							
							break towerLoop;	//we're breaking the outer loop as for this enemy there won't be any collisions, because he is NO MORE.
						}
					}
				}
			}
		}
	}



	/**
	 * This method should be used to safely remove objects from scene care need be taken for concurrency issues.
	 * It is possible to do it manually, (within engine.runOnUpdateThread) but it's here for code clarity. 
	 * @param entity
	 */
	public void removeObjectFromScene(final IEntity entity){
		engine.runOnUpdateThread(new Runnable() {
			public void run() {
				entity.detachSelf();
				entity.dispose();
			}
		});
	}



	public void nextLevel() {
		// TODO Auto-generated method stub
		
	}
	
	
	//-------------------- PRIVATE METHODS ----------------------------
	
	



	
	
}
