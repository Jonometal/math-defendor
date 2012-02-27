package nl.uva.mobilesystems.mathdefender;

import java.util.Iterator;

import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;
import nl.uva.mobilesystems.mathdefender.objects.Player;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Point;
import android.graphics.Typeface;
import android.opengl.GLES20;

public class InitialActivity extends SimpleBaseGameActivity {
	// ===========================================================
		// Constants
		// ===========================================================

	private static final float DEMO_VELOCITY = 100.0f;

		// ===========================================================
		// Fields
		// ===========================================================
	private Camera mCamera;
	
	private GameModel gModel;
	
	private Player player;

	//game-sprites
	//player
	private BitmapTextureAtlas mPlayerBitmap; //Player
	private TiledTextureRegion mPlayerTextureRegion;
	//enemy
	private BitmapTextureAtlas mEnemyBitmap; //Enemy
	private TiledTextureRegion mEnemyTextureregion;
	//tower
	private BitmapTextureAtlas mTowerBitmap; //Tower
	private TiledTextureRegion mTowerTextureRegion;
	
	//analog-control
	private BitmapTextureAtlas mOnScreenControlTexture;
	private ITextureRegion mOnScreenControlBaseTextureRegion;
	private ITextureRegion mOnScreenControlKnobTextureRegion;
	
	//Text
	private Font font;
	public Text text; //how many waves are left;
		
	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		
	 
	public EngineOptions onCreateEngineOptions() {
		//set Camera here
		this.mCamera = new Camera(0, 0, GUIConstants.CAMERA_WIDTH, GUIConstants.CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(GUIConstants.CAMERA_WIDTH, GUIConstants.CAMERA_HEIGHT), this.mCamera);
	}

	 
	protected void onCreateResources() {
		
		//player
		this.mPlayerBitmap = new BitmapTextureAtlas(this.getTextureManager(), 96, 96, TextureOptions.BILINEAR);
		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mPlayerBitmap, this, "gfx/next.png", 0, 0, 1, 1);
		this.mPlayerBitmap.load();
		
		//enemy
		this.mEnemyBitmap = new BitmapTextureAtlas(this.getTextureManager(), 32, 32, TextureOptions.BILINEAR);
		this.mEnemyTextureregion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mEnemyBitmap, this, "gfx/face_box.png", 0, 0, 1, 1);
		this.mEnemyBitmap.load();
		
		//tower
		this.mTowerBitmap = new BitmapTextureAtlas(this.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
		this.mTowerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mTowerBitmap, this, "gfx/chromatic_circle_small_64x64.png", 0, 0, 1, 1);
		this.mTowerBitmap.load();
		
		//analog control
		this.mOnScreenControlTexture = new BitmapTextureAtlas(this.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "gfx/onscreen_control_base.png", 0, 0);
		this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "gfx/onscreen_control_knob.png", 128, 0);
		this.mOnScreenControlTexture.load();
		
		//text
		this.font = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
		this.font.load();
		
	}

	 
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		//Set SCENE [must be done before Setting our MODEL obviously]
		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
		//set our MathLevel here (will be calculated in separated thread)
		gModel = new GameModel(this, scene);
			// (nrWaves, nrTowers, [Screen_X, Screen_Y], EnemyTexture, TowerTexture, Library-shit-buffer)
			gModel.setUpSimpleGame(50,1, new Point(GUIConstants.CAMERA_WIDTH, GUIConstants.CAMERA_HEIGHT), mEnemyTextureregion, mTowerTextureRegion, getVertexBufferObjectManager() );
		
		//Set Text
//		this.text = new Text(500, 40, this.font, ResStrings.DEBUG_WAVES_LEFT + " " + this.gModel.getWavesLeft().size(), 
//					new TextOptions(HorizontalAlign.CENTER), this.getVertexBufferObjectManager());
//		scene.attachChild(text);

		//Create a player here
		final float centerX = 100;
		final float centerY = 100;
		player = new Player(centerX, centerY, this.mPlayerTextureRegion, this.getVertexBufferObjectManager());

		scene.attachChild(player);
		
		
		//Set current wave objects here
		for(AnimatedSprite waveObject: gModel.getCurrentWaveObjects()){
			scene.attachChild(waveObject);
		}
		
		
		//Create analog-control here
		final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(0, GUIConstants.CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight(), this.mCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, 200, this.getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
			 
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				player.getPhysicsHanlder().setVelocity(pValueX * PhConstants.PLAYER_VELOCITY, pValueY * PhConstants.PLAYER_VELOCITY);
			}

			 
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				//what happens if you click on analog screen
				;
//				face.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f), new ScaleModifier(0.25f, 1.5f, 1)));
			}
		});
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();

		scene.setChildScene(analogOnScreenControl);
		
		scene.registerUpdateHandler(new IUpdateHandler(){

			 
//			GameModel.checkCollisions()
			public void onUpdate(float pSecondsElapsed) {
				Iterator<AnimatedSprite> iter = gModel.getCurrentWaveObjects().iterator();
				AnimatedSprite enemy;
				while(iter.hasNext()){
					enemy = iter.next();
					if(player.collidesWith(enemy)){
						gModel.removeObjectFromScene(enemy);
						//TODO should be re-written here in more OOP manner: so player.collisionDetected() and enemy.collisionDetected() should be used instead putting a logic here
						iter.remove();
					}
				}
//				for(AnimatedSprite enemy : gModel.getCurrentWaveObjects()){
//					if(player.collidesWith(enemy)){
//						gModel.removeObjectFromScene(enemy);
//					}
//				}
			}

			 
			public void reset() {
				; //nothing happens here so far?
			}
		});
		

		return scene;
	}
	
	
	
	/*
	 * PRIVATE METHODS GO DOWN ----------------------------------------------------
	 */
	
	
	
	
	
}
