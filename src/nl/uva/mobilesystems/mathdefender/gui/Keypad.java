package nl.uva.mobilesystems.mathdefender.gui;

import android.graphics.PointF;

public class Keypad {
	
	public static final int KEY_UP = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_LEFT = 3;
	public static final int KEY_RIGHT= 4;
	public static final int KEY_NONE = 0;
	

	/** top-left rectangle corner */
	public PointF left = new PointF();
	public PointF right = new PointF();
	
	public PointF up = new PointF();
	public PointF down = new PointF();
	
	public float keySize;

	
	
	public Keypad setLeft(float leftX, float leftY){
		left.x = leftX;
		left.y = leftY;
		return this;
	}
	
	public Keypad setRight(float rX, float rY){
		right.x = rX;
		right.y = rY;
		return this;
	}
	
	public Keypad setUp(float uX, float uY){
		up.x = uX;
		up.y = uY;
		return this;
	}
	
	public Keypad setDown(float dX, float dY){
		down.x = dX;
		down.y = dY;
		return this;
	}
	
	public Keypad setSize(float s){
		this.keySize = s;
		return this;
	}
	
	public Keypad(){
		;
	}
	
	public int getWhichKeyWasTouched(float tX, float tY){
		if(tX > left.x && tX < left.x+keySize && tY > left.y && tY < left.y+keySize)
			return KEY_LEFT;
		else if(tX > right.x && tX < right.x+keySize && tY > right.y && tY < right.y+keySize)
			return KEY_RIGHT;
		else if (tX > up.x && tX < up.x+keySize && tY > up.y && tY < up.y+keySize)
			return KEY_UP;
		else if(tX > down.x && tX < down.x+keySize && tY > down.y && tY < down.y+keySize)
			return KEY_DOWN;
		else 
			return KEY_NONE;
		
			
		
	}
	
}
