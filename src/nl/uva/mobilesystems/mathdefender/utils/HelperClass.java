package nl.uva.mobilesystems.mathdefender.utils;

import bsh.EvalError;
import bsh.Interpreter;
import android.graphics.YuvImage;
import android.util.Log;
import nl.uva.mobilesystems.mathdefender.gui.GUIConstants;

/**
 * Helper class which contains various, static methods that helps with various, annoying
 * stuff.
 * @author siemionides
 *
 */
public class HelperClass {
	
	public static float calculateDistance(float x1,  float y1, float x2, float y2){
		return  (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}

	public static String generateSum(int myDiff, int playerScore){
		String mySum ="";
		switch (myDiff)
		{
					case 1:
						int element = (int) (Math.floor(Math.random() * (playerScore + 10)) - 5);
						mySum = mySum + Integer.toString(element);
						break;
						
					case 2:
	 					int element2_0 = (int)(Math.floor(Math.random() * (playerScore + 100)) - 50);
						String element2_1 = randomConstructor("+-...");
						int element2_2 = (int) Math.floor(Math.random() * (playerScore + 100)) - 50;
						mySum = mySum + (Integer.toString(element2_0));
						mySum = mySum + (element2_1);
						mySum = mySum + (Integer.toString(element2_2));
						
						break;
						
					case 3:
						int element3_0 = (int)Math.floor(Math.random() * (playerScore + 200)) - 100;
						String element3_1 = randomConstructor("+-");
						int element3_2 = (int)Math.floor(Math.random() * (playerScore + 200)) - 100;
						String element3_3 = randomConstructor("-+");
						int element3_4 = (int)Math.floor(Math.random() * (playerScore + 100)) - 50;
						mySum = mySum + (Integer.toString(element3_0));
						mySum = mySum + (element3_1);
						mySum = mySum + (Integer.toString(element3_2));
						mySum = mySum + (element3_3);
						mySum = mySum + (Integer.toString(element3_4));
						break;
					case 4:
						int element4_0 = (int)Math.floor(Math.random() * (playerScore));
						String element4_1 = randomConstructor("*/...");
						int element4_2 = (int)Math.floor(Math.random() * (playerScore));
						mySum = mySum + (Integer.toString(element4_0));
						mySum = mySum + (element4_1);
						mySum = mySum + (Integer.toString(element4_2));
						break;						
					case 5:
						//The 5th level presents the user with sums like 5^6.Since our parser can't handle this
						// I wrote a simple forloop thatw ill generate a parseable sum. I just have to fix it so that this
						// parseable sum wont get printed on the Enemy.
						int element5_0 = (int)Math.floor(Math.random() * (10));
						mySum = mySum + (Integer.toString(element5_0));
						int element5_2 = (int)Math.floor(Math.random() * (10));
						for(int i=0; i< element5_2; i++)
						{
							mySum+= '*' + Integer.toString(element5_2);							
						}
						Log.v("testingSP","mySum = " + mySum);
						break;
						
					default:
						//trace ("Error! Difficulty == 0");
						break;
		}
		
		//now ensure that there's no double "-" or "+"
		for(int i=0; i<mySum.length()-2; i++){
			if(	mySum.charAt(i) == '+' && 	mySum.charAt(i+1) == '+' ||
				mySum.charAt(i) == '-' && 	mySum.charAt(i+1) == '-'){
				mySum = removeElementAt(mySum, i);
			}
		}
		
		
		return mySum;
	}

	public static boolean isOutSideScene(float x, float y){
		
		if(x < 0 || x > GUIConstants.CAMERA_WIDTH || y < 0 || y > GUIConstants.CAMERA_HEIGHT)
			return true;
		else
			return false;
	}
	
	public static int parseSum(String sum) throws EvalError{
		Interpreter interpreter = new Interpreter();
		int d = 0;
		interpreter.eval("result = " + sum);
		d = Integer.parseInt(interpreter.get("result").toString());
		return d;
	}
	
	public static String removeElementAt(String baseString,int index){
		StringBuffer buf = new StringBuffer(baseString);
		buf.deleteCharAt(index);
		return buf.toString();
	}
	
	public static String simplifyExpression(String expression, int diffLevel){
		ExpressionSimplifier simp = new ExpressionSimplifier(expression, diffLevel);
		return simp.getSimplified();
	}
	
	
	/* ------------------------- PRIVATE METHODS ----------------------------*/
	private static String randomConstructor(String operatorString)
	{
		int amnt = 0;
		int choice = 0;
		for(int o = 0; o < operatorString.length(); o++)
		{
			 
			if (operatorString.charAt(o) != '.'){ amnt+=1; }
//			Log.v("forloop","charAt(o) = " + operatorString.charAt(o));
//			Log.v("forloop","amount is " + amnt);
		}
		
		choice = (int)Math.floor(Math.random() *amnt);
		//trace("char chose: " + choice + " from "  + amnt);
//		Log.v("forloop","current char: " + operatw orString.charAt(choice));
		return Character.toString(operatorString.charAt(choice));
	}

}
