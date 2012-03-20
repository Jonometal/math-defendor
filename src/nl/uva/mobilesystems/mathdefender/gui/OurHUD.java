package nl.uva.mobilesystems.mathdefender.gui;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

/**
 * This is our own HUD class that should contains:
 * - Icon for Upgrade: TowerSimplifier
 * - Icon for Upgrade: TowerSlowDOwner
 * - Icon for Upragde: Tower Killer
 * - Icon for Uprade: BulletTime
 * - 
 * @author siemionides
 *
 */
public class OurHUD extends HUD{
	
	ObjectPositionEventListener listener;
	
	// KEYS used to addToHud(int)., removeFromHud(int)
	public static final int UPGRADE_TOWER_SIMPLIFIER = 0;
	public static final int UPGRADE_TOWER_SLOWER = 1;
	public static final int UPGRADE_TOWER_KILLER = 2;
	public static final int UPGRADE_BULLET_TIME = 3;
	public static final int HUD_ELEMENT_SWIPE_CHARGER= 4;
	
	
	
	private Scene scene;
	private GameModel model = null;
	private VertexBufferObjectManager objectManager;
	private TiledSprite upTowerSimplifier = null;
	private TiledSprite upTowerSlower = null;
	private TiledSprite upTowerKiller = null;
	private TiledSprite upBulletTime = null;
	private AnimatedSprite swipeCharger = null;
	
	public OurHUD(Scene _scene, VertexBufferObjectManager _objectManager){
		super();
		this.scene = _scene;
		this.objectManager = _objectManager;
	}
	
	
	public synchronized void addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
	}
	
	public synchronized void removeObjectPositionEventListener(){
		this.listener = null;
	}
	
	private synchronized void fireEvent(int eventCode, int addCode){
		ObjectPositionEvent event = new ObjectPositionEvent(this, eventCode,addCode);
		this.listener.handleObjectPositionEvent(event);
	}
	
	
	/**
	 * So keys are int OurHud method. OurHud.UPGRADE_TOWER_SIMPLIFIER for example.
	 * @param UpgradeKey
	 */
	public void addToToHud(int upgradeKey){
		float x = 0;
		float y = 0;
		switch(upgradeKey){
		
		
		case UPGRADE_TOWER_SIMPLIFIER:
			x = GUIConstants.HUD_ICON_SPACE;
			y = GUIConstants.HUD_TOP_MARGIN;
			
			this.upTowerSimplifier = new TiledSprite(x, y,GUIConstants.HUD_ICON_WIDTH, GUIConstants.HUD_ICON_HEIGHT,TexMan.getIt().mTowerSimpTextureRegion, this.objectManager){
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					fireEvent(EventsConstants.EVENT_UPGRADE_HUD_PRESSED, EventsConstants.ADD_EVENT_HUD_PRESSED_TOWER_SIMPLIFICATOR);
					return true;
				}
			};
			this.scene.registerTouchArea(this.upTowerSimplifier);
			this.attachChild(this.upTowerSimplifier);
			
			break;
			
		case UPGRADE_TOWER_SLOWER:
			x = GUIConstants.HUD_ICON_SPACE*2;
			y = GUIConstants.HUD_TOP_MARGIN;
			
			this.upTowerSlower = new TiledSprite(x, y,GUIConstants.HUD_ICON_WIDTH, GUIConstants.HUD_ICON_HEIGHT,TexMan.getIt().mTowerSlowDownerTextureRegion, this.objectManager){
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					fireEvent(EventsConstants.EVENT_UPGRADE_HUD_PRESSED, EventsConstants.ADD_EVENT_HUD_PRESSED_TOWER_SLOWER);
					return true;
				}
			};
			this.scene.registerTouchArea(this.upTowerSlower);
			this.attachChild(this.upTowerSlower);
			break;
		
		case UPGRADE_TOWER_KILLER:
			x = GUIConstants.HUD_ICON_SPACE*3;
			y = GUIConstants.HUD_TOP_MARGIN;
			
			this.upTowerKiller = new TiledSprite(x, y,GUIConstants.HUD_ICON_WIDTH, GUIConstants.HUD_ICON_HEIGHT,TexMan.getIt().mTowerKillerTextureRegion, this.objectManager){
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					fireEvent(EventsConstants.EVENT_UPGRADE_HUD_PRESSED, EventsConstants.ADD_EVENT_HUD_PRESSED_TOWER_KILLER);
					return true;
				}
			};
			this.scene.registerTouchArea(this.upTowerKiller);
			this.attachChild(this.upTowerKiller);
			break;
			
		case UPGRADE_BULLET_TIME:
			x = GUIConstants.HUD_ICON_SPACE*4;
			y = GUIConstants.HUD_TOP_MARGIN;
			
			this.upBulletTime = new TiledSprite(x, y,GUIConstants.HUD_ICON_WIDTH, GUIConstants.HUD_ICON_HEIGHT,TexMan.getIt().mUpgradeBuletTimeTextureRegion, this.objectManager){
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
					fireEvent(EventsConstants.EVENT_UPGRADE_HUD_PRESSED, EventsConstants.ADD_EVENT_HUD_PRESSED_BULLET_TIME);
					return true;
				}
			};
			this.scene.registerTouchArea(this.upBulletTime);
			this.attachChild(this.upBulletTime);
			break;
		
		case HUD_ELEMENT_SWIPE_CHARGER:
			break;
			
		}
	}
	
	public void removeFromHud(int elementCode){
		switch(elementCode){
		
		
		case UPGRADE_TOWER_SIMPLIFIER:
			this.scene.unregisterTouchArea(this.upTowerSimplifier);
			this.model.removeObjectFromScene(this.upTowerSimplifier);
			break;
			
		case UPGRADE_TOWER_SLOWER:
			this.scene.unregisterTouchArea(this.upTowerSlower);
			this.model.removeObjectFromScene(this.upTowerSlower);
			break;
		
		case UPGRADE_TOWER_KILLER:
			this.scene.unregisterTouchArea(this.upTowerKiller);
			this.model.removeObjectFromScene(this.upTowerKiller);
			break;
			
		case UPGRADE_BULLET_TIME:
			this.scene.unregisterTouchArea(this.upBulletTime);
			this.model.removeObjectFromScene(this.upBulletTime);
			break;
		
		case HUD_ELEMENT_SWIPE_CHARGER:
			break;
			
		}
	}


	public GameModel getModel() {
		return model;
	}


	public void setModel(GameModel model) {
		this.model = model;
	}
	

}
