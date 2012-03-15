package nl.uva.mobilesystems.mathdefender.gui;

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
	
	
	// KEYS used to addToHud(int)., removeFromHud(int)
	public static final int UPGRADE_TOWER_SIMPLIFIER = 0;
	public static final int UPGRADE_TOWER_SLOWER = 1;
	public static final int UPGRADE_TOWER_KILLER = 2;
	public static final int UPGRADE_BULLET_TIME = 3;
	public static final int HUD_ELEMENT_SWIPE_CHARGER= 4;
	
	
	
	private Scene scene;
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
					Log.d("hud", " SIMPLIFIER: Icon touched!!");
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
					Log.d("hud", " SLOWER DOWNER: Icon touched!!");
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
					Log.d("hud", " KIller: Icon touched!!");
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
					Log.d("hud", " BulletTime: Icon touched!!");
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
	

}
