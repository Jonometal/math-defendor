package nl.uva.mobilesystems.mathdefender.game;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;

/**
 * Containes enemies (or bonuses later on?), difficulty, ability to create Sums (later on) 
 * @author siemionides
 *
 */
public class Wave {
	/** Interface Fragile, because not only enemies but bonuses etc may be found here */
	LinkedList<AnimatedSprite> objects;
	
	public int difficulty;
	
	public LinkedList<AnimatedSprite> getObjects(){
		return objects;
	}
	public Wave(LinkedList<AnimatedSprite> objects){
		this.objects = objects;
	}
	
	public void removeObject(AnimatedSprite objectToRemove){
		this.objects.remove(objectToRemove);
	}
	
	
	
	
	
}
