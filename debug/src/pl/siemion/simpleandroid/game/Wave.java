package pl.siemion.simpleandroid.game;

import java.util.LinkedList;

/**
 * Containes enemies (or bonuses later on?), difficulty, ability to create Sums (later on) 
 * @author siemionides
 *
 */
public class Wave {
	/** Interface Fragile, because not only enemies but bonuses etc may be found here */
	LinkedList<Fragile> objects;
	
	public int difficulty;
	
	public LinkedList<Fragile> getObjects(){
		return objects;
	}
	public Wave(LinkedList<Fragile> objects){
		this.objects = objects;
	}
	
	
	
}
