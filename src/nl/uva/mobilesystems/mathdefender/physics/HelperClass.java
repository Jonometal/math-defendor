package nl.uva.mobilesystems.mathdefender.physics;

import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;

/**
 * Helper class which contains various, static methods that helps with various, annoying
 * stuff.
 * @author siemionides
 *
 */
public class HelperClass {
	
	public static boolean isOutSideScene(float x, float y){
		
		if(x < 0 || x > GUIConstants.CAMERA_WIDTH || y < 0 || y > GUIConstants.CAMERA_HEIGHT)
			return true;
		else
			return false;
	}
	
	public static float calculateDistance(float x1,  float y1, float x2, float y2){
		return  (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	

}
