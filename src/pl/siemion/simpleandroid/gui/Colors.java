package pl.siemion.simpleandroid.gui;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * It's more container class that contains colors, ... used in the game
 * @author siemionides
 *
 */
public class Colors {
	
	public static Paint PAINT_PLAYER = new Paint();
	public static Paint PAINT_PLAYER_TEXT = new Paint();
	
	public static Paint PAINT_ENEMY = new Paint();
	public static Paint PAINT_ENEMY_TEXT = new Paint();
	
	public static Paint PAINT_FPS = new Paint();	
	public static int COLOR_BACKGROUD = Color.BLACK;
	
	
	public static Paint PAINT_KEYPAD = new Paint();

	/** this is kind of tricky, so code inside the blocks initializes the variables ABOVE */
	static {
		PAINT_FPS.setColor(Color.RED);
		PAINT_FPS.setTextSize(32);
		
		PAINT_PLAYER.setColor(Color.BLUE);
		PAINT_PLAYER_TEXT.setColor(Color.GREEN);
		PAINT_PLAYER_TEXT.setTextSize(20);
		
		PAINT_ENEMY.setColor(Color.GREEN);
		PAINT_ENEMY_TEXT.setColor(Color.RED);
		PAINT_ENEMY_TEXT.setTextSize(15);
		
		PAINT_KEYPAD.setColor(Color.WHITE);
		PAINT_KEYPAD.setStyle(Paint.Style.STROKE);
		
	}
}
