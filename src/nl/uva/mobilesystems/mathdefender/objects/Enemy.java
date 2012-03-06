package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.physics.HelperClass;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import bsh.EvalError;
import bsh.Interpreter;

import android.util.Log;


/** Class representing single "enemy" in wave **/ 
public class Enemy extends AnimatedSprite{
	
	
	
	private GameModel model;

	private ObjectPositionEventListener listener; 
	
	private final PhysicsHandler mPhysicsHandler;
	
	private String mySum = "";
	private int myResult;
	private int myDiff;
	private Text myText;
	private Font myFont;
	
	public Enemy(final float pX, final float pY, final TiledTextureRegion pTextureRegion,
			final VertexBufferObjectManager pVertexBufferObjectManager,
			final int difficulty, Font myFont, GameModel model)
	{
		
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.model = model;
		this.myDiff = difficulty;
		//Set different diff. here for testing purposes
		this.myDiff = 4;
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(-PhConstants.ENEMY_VELOCITY, 0);
		this.mySum = genEquation();
		myResult = (int)Math.ceil(calculateResult(mySum));
		Entity a = new Entity();
		this.myFont = myFont;
		myText = new Text(0,0, this.myFont, "Score", "FPS: XXXXX".length(), pVertexBufferObjectManager);
		//myText.setText(Integer.toString(myResult));
		myText.setText(mySum);		
		this.attachChild(myText);
	}
	
	//This method should be placed in a StringCalc class and parse the string to return an answer in int
	private int calculateResult(String _mySum)
	{
		Interpreter interpreter = new Interpreter();
		int d = 0;
		try {
			interpreter.eval("result = " + _mySum);
			d = Integer.parseInt(interpreter.get("result").toString());
		}catch (java.lang.ArithmeticException ae){
			System.out.println("Catched!");
		} catch (EvalError e) {
			System.out.println(e.getLocalizedMessage());
		}
		return d;
	}

	/**
	 * It uses Laurens' rewritten flash code.
	 * It performs parsing tests - if it's not successful it generates the once more itd.
	 */
	
	public String genEquation()
	{
		String equation = "";
		equation = HelperClass.generateSum(this.myDiff,this.model.getPlayer().getScore());
		try{
			HelperClass.parseSum(equation);
		}catch(EvalError e){	//most probably it's divide by 0 exception
			Log.e("appError", e.toString());
			return genEquation();
		}
		return equation;
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
