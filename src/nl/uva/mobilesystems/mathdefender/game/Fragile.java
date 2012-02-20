package nl.uva.mobilesystems.mathdefender.game;

import android.graphics.PointF;

/**
 * This interface represents objects which can appear on the screen and cause
 * an interaction between each other. They can move or not move
 * Dimensions, location, Size, touch-area, speed etc
 *
 * @author siemionides
 *
 */
public interface Fragile {
	
	public void collisionDetected();
	
	public float getSpeedX();
	
	public float getSpeedY();
	
	public PointF getLocation();
	
	public float getSize();

	/** Should be called within a game-loop */
	public void moveIt();
	
	public void setSpeedX(float speedX);
	
	public void setSpeedY(float speedY);
	
	public void setLocation(PointF location);
	
	public void setSize(float size);
}
