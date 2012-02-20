package nl.uva.mobilesystems.mathdefender;

import java.util.concurrent.locks.ReentrantLock;

import nl.uva.mobilesystems.mathdefender.game.MathLevel;
import nl.uva.mobilesystems.mathdefender.game.Player;
import nl.uva.mobilesystems.mathdefender.physics.PhConstants;


import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

/** 
 * Model Part of MVC approach.
 * @author siemionides
 *
 */
public class GameModel extends Thread{
	
	/** Game Variables */
	public MathLevel level; //current Level

	
	/** Service Variables */
	final ReentrantLock lock = new ReentrantLock();
	
	private boolean mRun = false;

    /**
     * The actual model loop!
     */
    @Override
    public void run() {// TODO: solve synchronization problems, currently it's no synchro in here, but will be in Controller
        while (mRun) {
        	final ReentrantLock lock = this.lock;
        	lock.lock();
            try {
//            	Log.v("loop-model","oooo noo looop")
            	  level.movePlayer();
                  level.moveWave();
                  level.checkForCollisions();
                  Thread.sleep(PhConstants.MODEL_MILISEC_BREAK);
                } catch (InterruptedException e) {
                	e.printStackTrace();
				}
            	finally {
            		lock.unlock();
                }
        }
    }
    
    public void setRunning(boolean runnig){
    	this.mRun = runnig;
    }
	
	public GameModel(Point screenDimesions){
		
	      //Player initializtion
        Player player = new Player (100, 100);
        player.setSpeedX(0.0f);
        player.setSpeedY(0.0f);
        
        //level creation
        level = new MathLevel(player);
        level.generateWaves(5, new Point(screenDimesions.x, screenDimesions.y));
	}
	
}
