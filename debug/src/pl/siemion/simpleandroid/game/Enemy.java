package pl.siemion.simpleandroid.game;

import pl.siemion.simpleandroid.physics.PhConstants;
import android.graphics.PointF;

/** Class representing single "enemy" in wave **/ 
public class Enemy implements Fragile {

	private PointF location;
	private float speedX;
	private float speedY = -PhConstants.ENEMY_SPEED;	//TODO hard-coded, to be changed later on
	private float size;
	
	private int sum;
	
	public Enemy(int x, int y,float size, int sum){
		location = new PointF(x,y);
		this.size = size;
		this.setSum(sum);
	}
	
	@Override
	public float getSpeedX() {
		return this.speedX;
	}

	@Override
	public float getSpeedY() {
		return this.speedY;
	}

	@Override
	public PointF getLocation() {
		return location;
	}

	@Override
	public float getSize() {
		return this.size;
	}

	@Override
	public void moveIt() {
		location.x += speedX;
		location.y += speedY;
		
	}

	@Override
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
		
	}

	@Override
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	@Override
	public void setLocation(PointF location) {
		this.location = location;
	}

	@Override
	public void setSize(float size) {
		this.size = size;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

}
