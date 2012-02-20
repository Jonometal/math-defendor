package nl.uva.mobilesystems.mathdefender.physics;

/**
 * Class representing player's drone.
 * @author siemionides
 *
 */
public class Player {
	private float x;
	
	private float y;
	
	private float speedX;
	
	private float speedY;
	
	/* public methods here */
	
	public float getSpeedX(){
		return speedX;
	}
	
	public float getSpeedY(){
		return speedY;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void movePlayer(){
		x += speedX;
		y += speedY;
	}
	
	public void setSpeedX(float _speedX){
		this.speedX = _speedX;
	}
	
	public void setSpeedY(float _speedY){
		this.speedY = _speedY;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * default constructor
	 */
	public Player(){
		this.x = 0;
		this.y = 0;
		this.speedX = 0;
		this.speedY = 0;
	}
	

	public Player(float _x, float _y){
		this.x = _x;
		this.y = _y;
		this.speedX = 0;
		this.speedY = 0;
	}
	
	/* private methods here */
	
}
