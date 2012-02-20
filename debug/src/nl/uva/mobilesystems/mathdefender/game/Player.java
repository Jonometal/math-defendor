package nl.uva.mobilesystems.mathdefender.game;

import nl.uva.mobilesystems.mathdefender.physics.PhConstants;
import android.graphics.PointF;

/**
 * Class representing player's drone.
 * @author siemionides
 *
 */
public class Player implements Fragile {
	private PointF location;
		
	private float size = PhConstants.PLAYER_SIZE;
	
	private float speedX;
	
	private float speedY;
	
	
	
	/* public methods here */
	
	public float getSpeedX(){
		return speedX;
	}
	
	public float getSpeedY(){
		return speedY;
	}
	
	
	public void setSpeedX(float _speedX){
		this.speedX = _speedX;
	}
	
	public void setSpeedY(float _speedY){
		this.speedY = _speedY;
	}
	
	
	/**
	 * default constructor
	 */
	public Player(){
		this.location = new PointF(0,0);
		this.speedX = 0;
		this.speedY = 0;
	}
	

	public Player(float _x, float _y){
		this.location = new PointF(_x, _y);
		this.speedX = 0;
		this.speedY = 0;
	}

	@Override
	public PointF getLocation() {
		return this.location;
	}

	@Override
	public float getSize() {
		return this.size;
	}

	@Override
	public void moveIt() {
		this.location.x += speedX;
		this.location.y += speedY;
	}



	@Override
	public void setLocation(PointF location) {
		this.location = location;
	}

	@Override
	public void setSize(float size) {
		this.size = size;
	}

	@Override
	public void collisionDetected() {
		// TODO Auto-generated method stub
		
	}

	
	/* private methods here */
	
}
