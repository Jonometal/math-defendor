package nl.uva.mobilesystems.mathdefender;

import java.util.Iterator;
import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.game.Level;
import nl.uva.mobilesystems.mathdefender.game.Wave;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.objects.Player;
import nl.uva.mobilesystems.mathdefender.objects.Tower;
import nl.uva.mobilesystems.mathdefender.objects.TowerBullet;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

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
	
	
	Scene scene; //it's little bit awkward, it must be here because current implementation of Model starts drawing before InitialActivity.onCreateScene() method is finished, so engine variable (field in GameModel class) doesnt know about this scene yet
			
	
	/** Variable represeting current level that is maninated by GameModel */
	private Level currentLevel;
	
	public Player player; //public for experiments with PLayer class
	
	
	// Textures

	/** Debug things */
	
	private Text wavesLeftText; 
	
	
	public static LinkedList<AnimatedSprite> myEnemies;
	
	
	
	// ----------------------- CONSTRUCTORS --------------------------------
	
	public GameModel(InitialActivity activity, Scene scene){
		this.engine = activity.getEngine();			//Laurens: We should prob switch this to an object reference to the engine itself in case an activity can have several engines?
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
			Log.v("eventMine", "RemovesObject");
			if(currentLevel.getCurrentWave().getObjects().size() == 0){ //check whether something is still in current Wave
				if( currentLevel.getWaves().size() > 0) //if there are still waves to be shown
					startNewWave();
				
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
	 * This method is here more for debug/development purposes. It sets:
	 * - 1 New Level
	 * - nrWaves Waves in it
	 * - nrTowers Towers in it (will be places in the center of screen
	 * 
	 * Ultra important and bad-coding style method; Sets waves, enemies in there 
	 */
	public void setUpSimpleGame(int nrWaves, int nrTowers, Point screenDimensions, TiledTextureRegion textureEnemy,
								TiledTextureRegion textureTower, TiledTextureRegion textureTowerBullet,
								VertexBufferObjectManager objectManager, Font enemyFont){
		this.currentLevel = new Level(Level.DIFF_TUTORIAL);
		this.currentLevel.setWaves(new LinkedList<Wave>());
		int levelDiff = 1;
			
			for(int i=0; i<nrWaves; i++){
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			for(int j=0; j< PhConstants.NR_ENEMIES_IN_WAVE; j++){ //generating enemies
				int x = screenDimensions.x; //the edge of a screen
				int y = screenDimensions.y / (PhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1);	//so equal distribution on screen Width
				
				Enemy tempEnemy = new Enemy(x,y, textureEnemy, objectManager, levelDiff, enemyFont, this);
				tempEnemy.addObjectPositionEventListener(this);
				tempEnemies.add(tempEnemy);
			}
			Wave tempWave = new Wave(tempEnemies);
			this.myEnemies = tempEnemies;
			this.currentLevel.getWaves().offer(tempWave);
		}
		this.currentLevel.setCurrentWave(this.currentLevel.getWaves().poll());
		
		//Add additional TOWER to the game
		for(int j=0; j<nrTowers; j++){

			setNewTowerAt(350, 400, textureTower,textureTowerBullet, objectManager);
		}
	}
	
		
	/**
	 * Sets new Tower to current Level and adds it to the scene.
	 * @param X
	 * @param Y
	 * @param pTiledTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public void setNewTowerAt(final float X, final float Y, TiledTextureRegion pTowerTiledTextureRegion,TiledTextureRegion pTowerBulletTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ){
		Tower newTower = new Tower(this, X,Y, pTowerTiledTextureRegion,pTowerBulletTiledTextureRegion, pVertexBufferObjectManager);
		this.currentLevel.getTowers().add(newTower);
		addObjectToScene(newTower);
		this.scene.registerTouchArea(newTower);
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
		Iterator<AnimatedSprite> iter = this.getCurrentWaveObjects().iterator();
		LinkedList<Tower> towers = this.getTowers();
		AnimatedSprite enemy;
		while(iter.hasNext()){
			enemy = iter.next();
			if(player.collidesWith(enemy)){			//collsion player <-> enemy
				this.removeObjectFromScene(enemy);
				
				player.collisionDetected((Enemy)enemy);
				//TODO should be re-written here in more OOP manner: so player.collisionDetected() and enemy.collisionDetected() should be used instead putting a logic here
				iter.remove();
			}else{	//otherwise check for collisions with bullets
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
							this.removeObjectFromScene(bullet);
							this.removeObjectFromScene(enemy);
							tower.increaseBulletsAvailable(1);	//increase tower's bullet by 1
							iterBullet.remove(); //remove bullet
							iter.remove(); //remove enemy
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
	
	
	//-------------------- PRIVATE METHODS ----------------------------
	
	private void startNewWave(){
		currentLevel.setCurrentWave(currentLevel.getWaves().poll());
		for(IEntity object : currentLevel.getCurrentWave().getObjects()){
			addObjectToScene(object);
		}
	}
	
}
