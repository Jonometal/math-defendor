package nl.uva.mobilesystems.mathdefender;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.game.Level;
import nl.uva.mobilesystems.mathdefender.game.Wave;
import nl.uva.mobilesystems.mathdefender.objects.Enemy;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.Engine;
import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
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
	
	/** Game Variables */
	Engine engine;
	
	/** Variable represeting current level that is maninated by GameModel */
	private Level currentLevel;
	
	/** Debug things */
	
	private Text wavesLeftText; 
	
	public GameModel(InitialActivity activity){
		this.engine = activity.getEngine();			//Laurens: We should prob switch this to an object reference to the engine itself in case an activity can have several engines?
		this.wavesLeftText = activity.text;
	}
	
	/** Ultra important and bad-coding style method; Sets waves, enemies in there */
	public void setUpSimpleGame(int nrWaves, Point screenDimenstions, TiledTextureRegion textureRegion, VertexBufferObjectManager objectManager){
		this.currentLevel = new Level(Level.DIFF_TUTORIAL);
		this.currentLevel.setWaves(new LinkedList<Wave>());
		
		for(int i=0; i<nrWaves; i++){
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			for(int j=0; j< PhConstants.NR_ENEMIES_IN_WAVE; j++){ //generating enemies
				int random = (int)(Math.random() * 1000);	//should be an integer number from 0 - 1000 
				int x = screenDimenstions.x; //the edge of a screen
				int y = screenDimenstions.y / (PhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1);	//so equal distribution on screen Width
				
				Enemy tempEnemy = new Enemy(x,y, textureRegion, objectManager);
				tempEnemy.addObjectPositionEventListener(this);
				tempEnemies.add(tempEnemy);
			}
			Wave tempWave = new Wave(tempEnemies);
			this.currentLevel.getWaves().offer(tempWave);
		}
		this.currentLevel.setCurrentWave(this.currentLevel.getWaves().poll());
	}
	
	
	public LinkedList<AnimatedSprite> getCurrentWaveObjects(){
		return this.currentLevel.getCurrentWave().getObjects();
	}
	
	public LinkedList<Wave> getWavesLeft(){
		return this.currentLevel.getWaves();
	}

	@Override
	public void handleObjectPositionEvent(ObjectPositionEvent e) {
		
		switch(e.getEventCode()){
		
		case EventsConstants.EVENT_OBJECT_OUT_OF_SCENE:
			AnimatedSprite object = (AnimatedSprite) e.getSource(); 
			removeObjectFromScene(object);
			currentLevel.getCurrentWave().removeObject(object);	//it's ugly, maybe it's better to create some method in Level that could be called instead of walking down trough this hierarchy?
			object = null;
			Log.v("eventMine", "RemovesObject");
			if(currentLevel.getCurrentWave().getObjects().size() == 0){ //check whether something is still in current Wave
				if( currentLevel.getWaves().size() > 0) //if there are still waves to be shown
					startNewWave();
				
			}
			break;
		}
		
	}
	

	
	/**
	 * This method is for code clarity.
	 * @param entity
	 */
	public void addObjectToScene(IEntity entity){
		engine.getScene().attachChild(entity);
	}
	
	
	/**
	 * This method should be used to safely remove objects from scene care need be taken for concurrency issues.
	 * It is possible to do it manually, (within engine.runOnUpdateThread) but it's here for code clarity. 
	 * @param entity
	 */
	public void removeObjectFromScene(final IEntity entity){
		engine.runOnUpdateThread(new Runnable() {
			
			@Override
			public void run() {
				entity.detachSelf();
				entity.dispose();
			}
		});
	}
	
	
	private void startNewWave(){
		currentLevel.setCurrentWave(currentLevel.getWaves().poll());
		for(IEntity object : currentLevel.getCurrentWave().getObjects()){
			addObjectToScene(object);
		}
//		this.wavesLeftText.setText(ResStrings.DEBUG_WAVES_LEFT + " " + this.waves.size() );
		Log.v("eventMine", "StartWave");

//		this.engine.getScene().get
	}
	
}
