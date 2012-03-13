package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.GameSuperMarketModel;
import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Class representing player's drone.
 * @author siemionides
 *
 */
public class Player extends AnimatedSprite{
	
	private GameModel model;
	
	private final PhysicsHandler mPhysicsHandler;
	private int myScore = 5;
	private Font myFont;
    private BitmapTextureAtlas mFontTexture;
    private Text myText;
    
	/*
	 * SETTERS & GETTERS ----------------------------------------------------
	 */
	public PhysicsHandler getPhysicsHanlder(){
		return this.mPhysicsHandler;
	}

	public int getScore() {
		return myScore;
	}

	// Setscore is meant to be used by SMLevel to set a budget for the player
		public void setScore(int budget) {
			myScore = budget;
		}
	
//	public void updateScore(int updateScore) {
//		myScore += updateScore;
//		//myText.setText(int.toString(myScore));
//	}
    
    /*
     * CONSTRUCTORS ------------------------------------------------------
     */

	public Player(final float pX, final float pY, final TiledTextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, Font myFont, GameModel model){
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.myFont = myFont;
		myText = new Text(20,20, this.myFont, "Score", "FPS: XXXXX".length(), pVertexBufferObjectManager);
		myText.setText(Integer.toString(myScore));
		this.attachChild(myText);
		this.model = model;
	

//        this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.BLACK);
//		final ChangeableText elapsedText = new ChangeableText(100, 160, this.mFont, "Seconds elapsed:", "Seconds elapsed: XXXXX".length());
//		this.at
		
//		this.mPhysicsHandler.setVelocity(AndPhConstants.DEMO_VELOCITY, AndPhConstants.DEMO_VELOCITY);
		
	}
	 /*
     * OVERRIDEN METHOD ------------------------------------------------------
     */
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {

		//don't let the player go outside Camera's bounds!
		if((this.getX() <= 0 && this.mPhysicsHandler.getVelocityX() < 0) || 
				(this.getX() >= GUIConstants.CAMERA_WIDTH - this.getWidth() && this.mPhysicsHandler.getVelocityX() > 0))
			this.mPhysicsHandler.setVelocityX(0);
		else if((this.getY() <= 0 && this.mPhysicsHandler.getVelocityY() < 0) || 
				(this.getY() >= GUIConstants.CAMERA_HEIGHT - this.getHeight() && this.mPhysicsHandler.getVelocityY() > 0))
			this.mPhysicsHandler.setVelocityY(0);
		
		super.onManagedUpdate(pSecondsElapsed);
		
	}
	
	 /*
     * PUBLIC METHODS ------------------------------------------------------
     */
	
	/**
	 * Should be called when collision is detected.
	 * Currently it serves only collisions with enemies.
	 * @param enemy
	 */
	public void collisionDetected(Enemy enemy)
	{
		
		this.myScore += model instanceof GameSuperMarketModel ? - enemy.getResult(): enemy.getResult();
		this.model.engine.runOnUpdateThread(new Runnable() {
			
			public void run() {
				myText.setText(Integer.toString(myScore));
			}
		});
		
		
//		this.model.engine.getScene().registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
//			@Override
//			public void onTimePassed(final TimerHandler pTimerHandler) {
//				myText.setText("hahaha");
//			}
//		}));
		
//		this.myText.setText("haha");
	}
	

}
