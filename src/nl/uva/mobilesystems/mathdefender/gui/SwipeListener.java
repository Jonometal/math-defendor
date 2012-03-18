package nl.uva.mobilesystems.mathdefender.gui;

import nl.uva.mobilesystems.mathdefender.GameModel;
import nl.uva.mobilesystems.mathdefender.andengine.events.EventsConstants;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEvent;
import nl.uva.mobilesystems.mathdefender.andengine.events.ObjectPositionEventListener;
import nl.uva.mobilesystems.mathdefender.utils.HelperClass;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class SwipeListener implements IOnSceneTouchListener {

	private GameModel model; //model is here to have an access to player
	final int swipeMinDistance;
	
	private float xMoveDown = -10, yMoveDown = -10;
	public float xActionUp = -10, yActionUp = -10;
	
	private ObjectPositionEventListener listener; 
	
	
	public SwipeListener(Context ctx, GameModel _model){
		final ViewConfiguration vs = ViewConfiguration.get(ctx);
		this.swipeMinDistance = vs.getScaledTouchSlop();
		this.model = _model;
				
		
	}
	
	public synchronized void addObjectPositionEventListener(ObjectPositionEventListener listener){
		this.listener = listener;
	}
	
	public boolean isClickedOnPlayer(float x, float y){
		return this.model.player.contains(x, y);
	}
	
	
	
	public synchronized void removeObjectPositionEventListener(){
		this.listener = null;
	}
	

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
//		Log.d("swipe", pSceneTouchEvent.getX() + ", " + pSceneTouchEvent.getY());
		float xPress = pSceneTouchEvent.getX();
		float yPress = pSceneTouchEvent.getY();
		
		switch(pSceneTouchEvent.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			if(!HelperClass.isOutSideScene(xPress, yPress) && isClickedOnPlayer(xPress, yPress)){
				xMoveDown = pSceneTouchEvent.getX();
				yMoveDown = pSceneTouchEvent.getY();
			}
			break;
		
		case MotionEvent.ACTION_OUTSIDE:
			xMoveDown = -10;
			yMoveDown = -10;
			
			break;
			
		case MotionEvent.ACTION_MOVE:
			break;
		
		case MotionEvent.ACTION_UP:
			if(xMoveDown != -10 && yMoveDown != -10){
				
				if(Math.abs(pSceneTouchEvent.getX() - xMoveDown) > swipeMinDistance &&  
						Math.abs(pSceneTouchEvent.getY() - yMoveDown) > swipeMinDistance){
//					Log.d("swipe detected!:",  xMoveDown + "," + yMoveDown + "   to   " + pSceneTouchEvent.getX() + "," + pSceneTouchEvent.getY());
					xActionUp = xPress;
					yActionUp = yPress;
					fireEvent(EventsConstants.EVENT_SWIPE_DETECTED);
					xMoveDown = -10;
					yMoveDown = -10;	
				}
				
			}
			break;
		}
		
		return false;
	}
	
	private synchronized void fireEvent(int eventCode){
		ObjectPositionEvent event = new ObjectPositionEvent(this, eventCode);
		this.listener.handleObjectPositionEvent(event);
	}

}
