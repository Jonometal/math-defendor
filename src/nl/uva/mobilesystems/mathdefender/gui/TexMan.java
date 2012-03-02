package nl.uva.mobilesystems.mathdefender.gui;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;

/**
 * Texture Manager.
 * Class that is a container for textures/fonts/. Make them public and access from here.
 * IMPORTANT! this class is written in Singleton manner (so we can call it and access
 * our texstures from various places in the code) - So you're accessing your textures
 * through calling TexMan.getIt().(here comes your texture name). 
 * 
 * The name of class and access method is short so it's easy to use it, otherwise
 * you will have to use sth like TextureManager.getInstance().(your texture name)
 * @author siemionides
 *
 */
public class TexMan {
	
	// --------------------- TEXTURES/FONTS BELOW ------------------------------
	
	//game-sprites
	//background
	public BitmapTextureAtlas mBackgroundBitmap; //Background
	public ITextureRegion mBackgroundTextureRegion;
	public Sprite mBackgroundSprite;
	
	//player
	public BitmapTextureAtlas mPlayerBitmap; //Player
	public TiledTextureRegion mPlayerTextureRegion;
	
	//enemy
	public BitmapTextureAtlas mEnemyBitmap; //Enemy
	public TiledTextureRegion mEnemyTextureregion;
	
	//tower
	public BitmapTextureAtlas mTowerBitmap; //Tower
	public TiledTextureRegion mTowerTextureRegion;
	
	//tower bullet
	public BitmapTextureAtlas mTowerBulletBitmap;
	public TiledTextureRegion mTowerBulletTextureRegion;
	
	//analog-control
	public BitmapTextureAtlas mOnScreenControlTexture;
	public ITextureRegion mOnScreenControlBaseTextureRegion;
	public ITextureRegion mOnScreenControlKnobTextureRegion;
	
	//Text
	public Font font;
	public Font playerFont;
	
//	---------------------  INTERNAL ENGINE OF CLASS + PUBLIC METHODS ------------------------------
	
	private static TexMan instance;
	
	private static boolean wasInitialized = false;
	
	public static void initializeTextures(SimpleBaseGameActivity activity) throws IllegalStateException{
		
//		if(wasInitialized) return;
		
		//pbackground
		TexMan.getIt().mBackgroundBitmap = new BitmapTextureAtlas(activity.getTextureManager(), 531, 241, TextureOptions.BILINEAR);
		TexMan.getIt().mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(TexMan.getIt().mBackgroundBitmap, activity, "gfx/bg.png", 0, 0,1,1);
		TexMan.getIt().mBackgroundBitmap.load();
		TexMan.getIt().mBackgroundSprite = new Sprite(0f,0f, (float)GUIConstants.CAMERA_WIDTH, (float)GUIConstants.CAMERA_HEIGHT, TexMan.getIt().mBackgroundTextureRegion, activity.getVertexBufferObjectManager());
				
			//player
		TexMan.getIt().mPlayerBitmap = new BitmapTextureAtlas(activity.getTextureManager(), 96, 96, TextureOptions.BILINEAR);
		TexMan.getIt().mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(TexMan.getIt().mPlayerBitmap, activity, "gfx/next.png", 0, 0, 1, 1);
		TexMan.getIt().mPlayerBitmap.load();
				
		//enemy
		TexMan.getIt().mEnemyBitmap = new BitmapTextureAtlas(activity.getTextureManager(), 32, 32, TextureOptions.BILINEAR);
		TexMan.getIt().mEnemyTextureregion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(TexMan.getIt().mEnemyBitmap, activity, "gfx/face_box.png", 0, 0, 1, 1);
		TexMan.getIt().mEnemyBitmap.load();
				
			//tower
		TexMan.getIt().mTowerBitmap = new BitmapTextureAtlas(activity.getTextureManager(), 64, 64, TextureOptions.BILINEAR);
		TexMan.getIt().mTowerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(TexMan.getIt().mTowerBitmap, activity, "gfx/chromatic_circle_small_64x64.png", 0, 0, 1, 1);
		TexMan.getIt().mTowerBitmap.load();
				
		//towerbullet
		TexMan.getIt().mTowerBulletBitmap = new BitmapTextureAtlas(activity.getTextureManager(), 32, 32, TextureOptions.BILINEAR);
		TexMan.getIt().mTowerBulletTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(TexMan.getIt().mTowerBulletBitmap, activity, "gfx/bullet.png", 0, 0, 1, 1);
		TexMan.getIt().mTowerBulletBitmap.load();
				
		//analog control
		TexMan.getIt().mOnScreenControlTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		TexMan.getIt().mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(TexMan.getIt().mOnScreenControlTexture, activity, "gfx/onscreen_control_base.png", 0, 0);
		TexMan.getIt().mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(TexMan.getIt().mOnScreenControlTexture, activity, "gfx/onscreen_control_knob.png", 128, 0);
		TexMan.getIt().mOnScreenControlTexture.load();
				
		//text
		TexMan.getIt().font = FontFactory.create(activity.getFontManager(), activity.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32);
		TexMan.getIt().font.load();		
		TexMan.getIt().playerFont = FontFactory.create(activity.getFontManager(), activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 48);
		TexMan.getIt().playerFont.load();
	
//		if(wasInitialized) throw new IllegalStateException("It was already initialized!");
//		wasInitialized = true;
		
	}
	
	
	/**
	 * Access method
	 */
	public static TexMan getIt(){
		if(instance == null) 
			instance = new TexMan();
		return instance;
	}

}
