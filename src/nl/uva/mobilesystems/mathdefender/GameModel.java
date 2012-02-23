package nl.uva.mobilesystems.mathdefender;

import java.util.LinkedList;

import nl.uva.mobilesystems.mathdefender.andengine.AndEnemy;
import nl.uva.mobilesystems.mathdefender.andengine.AndEngineInitialActivity;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.game.AndWave;
import nl.uva.mobilesystems.mathdefender.gui.ResStrings;
import nl.uva.mobilesystems.mathdefender.physics.AndPhConstants;

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
	
	private AndWave currentWave;
	
	private LinkedList<AndWave> waves;
	
	
	/** Debug things */
	
	private Text wavesLeftText; 
	
	public GameModel(AndEngineInitialActivity activity){
		this.engine = activity.getEngine();
		this.wavesLeftText = activity.text;
	}
	
	/** Ultra important and bad-coding style method; Sets waves, enemies in there */
	public void generateWaves(int nrWaves, Point screenDimenstions, TiledTextureRegion textureRegion, VertexBufferObjectManager objectManager){
		this.waves = new LinkedList<AndWave>();
		for(int i=0; i<nrWaves; i++){
			LinkedList<AnimatedSprite>  tempEnemies = new LinkedList<AnimatedSprite>();
			for(int j=0; j< AndPhConstants.NR_ENEMIES_IN_WAVE; j++){ //generating enemies
				int random = (int)(Math.random() * 1000);	//should be an integer number from 0 - 1000 
				int x = screenDimenstions.x; //the edge of a screen
				int y = screenDimenstions.y / (AndPhConstants.NR_ENEMIES_IN_WAVE+1) * (j+1);	//so equal distribution on screen Width
				
				AndEnemy tempEnemy = new AndEnemy(x,y, textureRegion, objectManager);
				tempEnemy.addObjectPositionEventListener(this);
				tempEnemies.add(tempEnemy);
			}
			AndWave tempWave = new AndWave(tempEnemies);
			waves.offer(tempWave);
		}
		currentWave = waves.poll();
	}
	
	
	public LinkedList<AnimatedSprite> getCurrentWaveObjects(){
		return this.currentWave.getObjects();
	}
	
	public LinkedList<AndWave> getWavesLeft(){
		return this.waves;
	}

	@Override
	public void handleObjectPositionEvent(ObjectPositionEvent e) {
		
		switch(e.getEventCode()){
		case EventsConstants.EVENT_OBJECT_OUT_OF_SCENE:
			
			AnimatedSprite object = (AnimatedSprite) e.getSource(); 
			removeObjectFromScene(object);
			this.currentWave.removeObject(object);
			object = null;
			
			Log.v("eventMine", "RemovesObject");
			if(this.currentWave.getObjects().size() == 0){ //check whether something is still in current Wave
				if(this.waves.size() > 0) //if there are still waves to be shown
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
		this.currentWave = this.waves.poll();
		for(IEntity object : this.currentWave.getObjects()){
			addObjectToScene(object);
		}
//		this.wavesLeftText.setText(ResStrings.DEBUG_WAVES_LEFT + " " + this.waves.size() );
		Log.v("eventMine", "StartWave");

//		this.engine.getScene().get
	}
	
}
