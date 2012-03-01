package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.StringCalc;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import android.graphics.PointF;
import android.util.Log;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


/** Class representing single "enemy" in wave **/ 
public class Enemy extends AnimatedSprite{
	
	

	private ObjectPositionEventListener listener; 
	
	private final PhysicsHandler mPhysicsHandler;
	
	private String mySum = "";
	private int myResult;
	private int myDiff;
	private Text myText;
	private Font myFont;
	
	public Enemy(final float pX, final float pY, final TiledTextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager,
			final int difficulty, Font myFont
			)
	{
		
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.myDiff = difficulty;
		//Set different diff. here for testing purposes
		this.myDiff = 4;
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(-PhConstants.ENEMY_VELOCITY, 0);
		genEquation();
		myResult = (int)Math.ceil(calculateResult(mySum));
		Entity a = new Entity();
		this.myFont = myFont;
		final Text myText = new Text(0,0, this.myFont, "Score", "FPS: XXXXX".length(), this.getVertexBufferObjectManager());
		//myText.setText(Integer.toString(myResult));
		myText.setText(mySum);		
		this.attachChild(myText);
	}
	
	//This method should be placed in a StringCalc class and parse the string to return an answer in int
	private int calculateResult(String mySum)
	{
		return 10;
		//return StringCalc.calculate(mySum);
	}

	public void genEquation()
	{
		switch (myDiff)
		{
					case 1:
						int element = (int) (Math.floor(Math.random() * (Player.getScore() + 10)) - 5);
						mySum = mySum + Integer.toString(element);
						//if (gameMode == "Defender"){var element = Math.floor(Math.random() * (Player.getScore() + 10)) - 5;}
						//else {var element = Math.floor(Math.random() * Player.getScore() + 10) - 5;}
						//listOfElements.push(element);
						break;
						
					case 2:
						int element2_0 = (int)(Math.floor(Math.random() * (Player.getScore() + 100)) - 50);
						String element2_1 = randomConstructor("+-...");
						int element2_2 = (int) Math.floor(Math.random() * (Player.getScore() + 100)) - 50;
						mySum = mySum + (Integer.toString(element2_0));
						mySum = mySum + (element2_1);
						mySum = mySum + (Integer.toString(element2_2));
						
						break;
						
					case 3:
						int element3_0 = (int)Math.floor(Math.random() * (Player.getScore() + 200)) - 100;
						String element3_1 = randomConstructor("+-");
						int element3_2 = (int)Math.floor(Math.random() * (Player.getScore() + 200)) - 100;
						String element3_3 = randomConstructor("-+");
						int element3_4 = (int)Math.floor(Math.random() * (Player.getScore() + 100)) - 50;
						mySum = mySum + (Integer.toString(element3_0));
						mySum = mySum + (element3_1);
						mySum = mySum + (Integer.toString(element3_2));
						mySum = mySum + (element3_3);
						mySum = mySum + (Integer.toString(element3_4));
						break;
					case 4:
						int element4_0 = (int)Math.floor(Math.random() * (Player.getScore()));
						String element4_1 = randomConstructor("*/...");
						int element4_2 = (int)Math.floor(Math.random() * (Player.getScore()));
						mySum = mySum + (Integer.toString(element4_0));
						mySum = mySum + (element4_1);
						mySum = mySum + (Integer.toString(element4_2));
						break;						
					case 5:
						break;
						
					default:
						//trace ("Error! Difficulty == 0");
						break;
		}
	}
	
	public String randomConstructor(String operatorString)
	{
		int amnt = 0;
		int choice = 0;
		for(int o = 0; o < operatorString.length(); o++)
		{
			 
			if (operatorString.charAt(o) != '.'){ amnt+=1; }
			Log.v("forloop","charAt(o) = " + operatorString.charAt(o));
			Log.v("forloop","amount is " + amnt);
		}
		
		choice = (int)Math.floor(Math.random() *amnt);
		//trace("char chose: " + choice + " from "  + amnt);
		Log.v("forloop","current char: " + operatorString.charAt(choice));
		return Character.toString(operatorString.charAt(choice));
	}
	
	public String getSum() {
		return mySum;
	}
	
	public int getResult() {
		return myResult;
	}
	
	public void setSum(String sum) {
		this.mySum = sum;
	}
	
	public synchronized void addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
	}
	
	
	public synchronized void removeObjectPositionEventListener(){
		this.listener = null;
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {

		if (this.getX() < 0){
			fireEvent(EventsConstants.EVENT_OBJECT_ENEMY_OUT_OF_SCENE);
			
		}
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	private synchronized void fireEvent(int eventCode){
		ObjectPositionEvent event = new ObjectPositionEvent(this, eventCode);
		this.listener.handleObjectPositionEvent(event);
	}
	
	
	

}
