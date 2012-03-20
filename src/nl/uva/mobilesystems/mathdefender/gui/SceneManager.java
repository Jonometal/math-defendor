package nl.uva.mobilesystems.mathdefender.gui;

import java.util.LinkedList;
import java.util.StringTokenizer;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.CameraScene;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;
import android.util.Log;


/**
 * Class-container for other scenes (pause-scene, level-finished scene, game-over-scene)
 * @author siemionids
 *
 */
public class SceneManager {
	//internal variables
	
	private static final float BACK_SPRITE_X = 499f  * 0.8f;
	private static final float BACK_SPRITE_Y = 457f * 0.8f;
	
	private static SceneManager instance = null;
	
	private Camera camera;
	
	private Scene mainScene;
	
	private Engine engine;
	
	private GameModel model;
	
	private VertexBufferObjectManager objectManager;
	
	
	// Scene Manager external fields
	
	
	public AnalogOnScreenControl analogOnScreenControl; //analog Control - it's also child scene
	
	public CameraScene levelZenFinished;	//level ZEN finished Scene
	
	public CameraScene gameOverScene;
	
	
	
	public void createAnalogOnScreenControlScene(){
		//Create analog-control here
				analogOnScreenControl = new AnalogOnScreenControl(0, GUIConstants.CAMERA_HEIGHT - TexMan.getIt().mOnScreenControlBaseTextureRegion.getHeight(), camera, TexMan.getIt().mOnScreenControlBaseTextureRegion, TexMan.getIt().mOnScreenControlKnobTextureRegion, 0.1f, 200, objectManager, new IAnalogOnScreenControlListener() {
					 
					
					public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
						
						model.getPlayer().getPhysicsHanlder().setVelocity(pValueX * PhConstants.PLAYER_VELOCITY, pValueY * PhConstants.PLAYER_VELOCITY);
					}
					public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
						//what happens if you click on analog screen
						;
//						face.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f), new ScaleModifier(0.25f, 1.5f, 1)));
					}
				});
				analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
				analogOnScreenControl.getControlBase().setAlpha(0.5f);
				analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
				analogOnScreenControl.getControlBase().setScale(1.25f);
				analogOnScreenControl.getControlKnob().setScale(1.25f);
				analogOnScreenControl.refreshControlKnobPosition();
	}
	
	
	public void createGameOverScene(int playerScore){
		getIt().gameOverScene = new CameraScene(getIt().camera);
		getIt().gameOverScene.setBackgroundEnabled(false);
		
		float spriteX = GUIConstants.CAMERA_WIDTH/2-BACK_SPRITE_X/2;
		float spriteY = GUIConstants.CAMERA_HEIGHT/2-BACK_SPRITE_Y/2;
		
		Sprite backSprite = new Sprite(spriteX ,spriteY, 
				BACK_SPRITE_X, BACK_SPRITE_Y, TexMan.getIt().mLevelFinishedBackgroundTextureRegion, getIt().objectManager){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				Log.d("sceneManager", "GameOverTouch");
				getIt().model.activity.finish();
//				getIt().mainScene.clearChildScene();
//				
//				
////				getIt().levelZenFinished.detachSelf();
//				getIt().mainScene.onUpdate(1);
//				getIt().mainScene.setChildScene(analogOnScreenControl);
				
				return true;
			}
		};
		getIt().gameOverScene.registerTouchArea(backSprite);

		
		//Text: Game Over - Title
		Text titleText = new Text(0,0, TexMan.getIt().levelFinishedTitleFont, "Game Over!", 50, getIt().objectManager);
		titleText.setColor(1.0f, 1.0f, 1.0f);
		titleText.setPosition(GUIConstants.CAMERA_WIDTH/2-titleText.getWidth()/2, spriteY+20f);
//		myText.setPosition(50f, 50f);
		
		//Your Score: Text
		Text contentText1 = new Text(0,0, TexMan.getIt().levelFinishedContentFont, "Your score: " + playerScore, 50, getIt().objectManager);
		contentText1.setColor(1.0f, 1.0f, 1.0f);
		contentText1.setPosition(GUIConstants.CAMERA_WIDTH/2-contentText1.getWidth()/2, spriteY+120f);
		
		//Click to return to Main Menu: Text
		Text contentText2 = new Text(0,0, TexMan.getIt().returnToMenuFont, "Touch for Main Menu.", 50, getIt().objectManager);
		contentText2.setColor(1.0f, 1.0f, 1.0f);
		contentText2.setPosition(GUIConstants.CAMERA_WIDTH/2-contentText2.getWidth()/2, spriteY+300f);
		
		getIt().gameOverScene.attachChild(backSprite);
		getIt().gameOverScene.attachChild(titleText);
		getIt().gameOverScene.attachChild(contentText1);
		getIt().gameOverScene.attachChild(contentText2);
	}
	
	
	
	public void createZenLevelFinishedScene(int playerScore, int wavesPassed){
		getIt().levelZenFinished = new CameraScene(getIt().camera);
		getIt().levelZenFinished.setBackgroundEnabled(false);
		
		float spriteX = GUIConstants.CAMERA_WIDTH/2-BACK_SPRITE_X/2;
		float spriteY = GUIConstants.CAMERA_HEIGHT/2-BACK_SPRITE_Y/2;
		
		//background sprite
		Sprite backSprite = new Sprite(spriteX ,spriteY, 
				BACK_SPRITE_X, BACK_SPRITE_Y, TexMan.getIt().mLevelFinishedBackgroundTextureRegion, objectManager){
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				getIt().mainScene.clearChildScene();
				getIt().mainScene.onUpdate(1);
				getIt().mainScene.setChildScene(analogOnScreenControl);
				
				return true;
			}
		};
		getIt().levelZenFinished.registerTouchArea(backSprite);
		
		
		//Level Finished - Title
		Text titleText = new Text(0,0, TexMan.getIt().levelFinishedTitleFont, "Level Finished!", 50, getIt().objectManager);
		titleText.setColor(1.0f, 1.0f, 1.0f);
		titleText.setPosition(GUIConstants.CAMERA_WIDTH/2-titleText.getWidth()/2, spriteY+20f);
//		myText.setPosition(50f, 50f);
		
		//Your Score: Text
		Text contentText1 = new Text(0,0, TexMan.getIt().levelFinishedContentFont, "Your score: " + playerScore, 50, getIt().objectManager);
		contentText1.setColor(1.0f, 1.0f, 1.0f);
		contentText1.setPosition(GUIConstants.CAMERA_WIDTH/2-contentText1.getWidth()/2, spriteY+120f);

		//Waves Passed: your score
		Text contentText2 = new Text(0,0, TexMan.getIt().levelFinishedContentFont, "Waves passed: " +wavesPassed, 50, getIt().objectManager);
		contentText2.setColor(1.0f, 1.0f, 1.0f);
		contentText2.setPosition(GUIConstants.CAMERA_WIDTH/2-contentText2.getWidth()/2, spriteY+220f);
		
		//Touch for Next Level your score
		Text contentText3 = new Text(0,0, TexMan.getIt().levelFinishedContentFont, "Touch for Next Level", 50, getIt().objectManager);
		contentText3.setColor(1.0f, 1.0f, 1.0f);
		contentText3.setPosition(GUIConstants.CAMERA_WIDTH/2-contentText3.getWidth()/2, spriteY+300f);
		
		
		
//		getIt().levelZenFinished.setBackground(new SpriteBackground(0.05f, 0.8f, 0.8f, TexMan.getIt().mLevelFinishedBackgroundSprite));

		getIt().levelZenFinished.attachChild(backSprite);
		getIt().levelZenFinished.attachChild(titleText);
		getIt().levelZenFinished.attachChild(contentText1);
		getIt().levelZenFinished.attachChild(contentText2);
		getIt().levelZenFinished.attachChild(contentText3);
	}
	
	
	
	public void createSuperMarketLevelFinishedScene(){
		;
	}
	
	public void createSceneManager(Camera cam, Engine engine, Scene scene, VertexBufferObjectManager objectManager, GameModel model){
		getIt().camera = cam;
		getIt().objectManager = objectManager;
		getIt().mainScene = scene;
		getIt().engine = engine;
		getIt().model = model;
		
		
		
		
	}
//	String "dsddas,dsdsasd";
	
	
	
	
	public static SceneManager getIt(){
		if(instance == null)
			instance = new SceneManager();
		return instance;
	}
	
	
	public static void showGameOverScene(int playerScore){
		getIt().gameOverScene = null;
		getIt().createGameOverScene(playerScore);
		getIt().mainScene.setChildScene(getIt().gameOverScene, true, true, true);
//		getIt().mainScene.onUpdate(0);
	}
	
	
	public static void showZenLevelFinishedScene(int currentScore, int wavesPassed){
		getIt().levelZenFinished = null;
		getIt().createZenLevelFinishedScene(currentScore, wavesPassed);
		getIt().mainScene.setChildScene(getIt().levelZenFinished, false, true, true);
		getIt().mainScene.onUpdate(0);
		//		
//		getIt().engine.stop();
	}
	
	private SceneManager(){
		;
	}
	
}
