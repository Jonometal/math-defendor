package nl.uva.mobilesystems.mathdefender.objects;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.gui.TexMan;
import nl.uva.mobilesystems.mathdefender.utils.HelperClass;
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
	
	public Enemy(final float pX, final float pY,
			final VertexBufferObjectManager pVertexBufferObjectManager,
			final int difficulty, GameModel model, TiledTextureRegion texRegion)
	{
		
		super(pX, pY, texRegion, pVertexBufferObjectManager);
		this.model = model;
		this.myDiff = difficulty;
		//Set different diff. here for testing purposes
//		this.myDiff = (int)Math.floor(Math.random() * 4 +1);
//		Log.v("diffMine", "myDiff: " + this.myDiff);
		this.mPhysicsHandler = new PhysicsHandler(this);
		this.registerUpdateHandler(this.mPhysicsHandler);
		this.mPhysicsHandler.setVelocity(-PhConstants.ENEMY_VELOCITY, 0);
		this.mySum = genEquation();
		myResult = (int)Math.ceil(calculateResult(mySum));
		this.myText = new Text(0,0, TexMan.getIt().playerFont, "Equation", 50, pVertexBufferObjectManager);
		
		myText.setText(mySum);		
		this.attachChild(myText);
	}
	
	//This method should be placed in a StringCalc class and parse the string to return an answer in int
	private int calculateResult(String _mySum)
	{
		Interpreter interpreter = new Interpreter();
		int d = 0;
		try {
			if(this.myDiff <= 4)
			{
				interpreter.eval("result = " + _mySum);
				d = Integer.parseInt(interpreter.get("result").toString());
			}
			else if (this.myDiff == 5)
			{
				if(Character.getNumericValue(_mySum.charAt(2)) <= 0)
				{
					_mySum = "1";
				}
				else
				{
					for(int i=1; i<Character.getNumericValue(_mySum.charAt(2)); i++)
					{
						_mySum+= ("*" + _mySum.charAt(0));	
					}
					StringBuffer myString = new StringBuffer(_mySum);
					myString.delete(1,3);
					_mySum = myString.toString();
				}
				interpreter.eval("result = " + _mySum);
				d = Integer.parseInt(interpreter.get("result").toString());
				
			}
		}catch (java.lang.ArithmeticException ae){
			System.out.println("Catched!");
		} catch (EvalError e) {
			System.out.println(e.getLocalizedMessage());
		}
		Log.v("testingSP","myResult = " + d);
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
	
	public float getVelocityX(){
		return this.mPhysicsHandler.getVelocityX();
	}
	
	public float getVelocityY(){
		return this.mPhysicsHandler.getVelocityY();
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
	
	/** Should be called when collision between enemy and sth else happened.
	 *  If tower is null it assumes that player touched it
	 *  */
	public void collisionDetected(Tower tower){
		if(tower == null)		//collision with enemy
			fireEvent(EventsConstants.EVENT_OBJECT_ENEMY_OUT_OF_SCENE);
		
		if(tower instanceof TowerSimplificator){	//Simplify Equation
			this.setSum(HelperClass.simplifyExpression(this.getSum(), this.myDiff));
			if(this.myDiff > 1) this.myDiff--;
			this.model.engine.runOnUpdateThread(new Runnable() {
				public void run() {
					myText.setText((getSum()));
				}
			});
			
		}else if(tower instanceof TowerKiller)
			fireEvent(EventsConstants.EVENT_OBJECT_ENEMY_OUT_OF_SCENE);
		else if(tower instanceof TowerSlower ){
			TowerSlower towerS = (TowerSlower)tower;
			if(Math.abs(this.getVelocityX()) == Math.abs(PhConstants.ENEMY_VELOCITY)){ //so the one that was already shooted down won't be again..
				float vX = this.mPhysicsHandler.getVelocityX();
				float vY = this.mPhysicsHandler.getVelocityY();
				this.mPhysicsHandler.setVelocity(vX * towerS.getSlowDownRatio(), vY * towerS.getSlowDownRatio());
			}
		}
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
