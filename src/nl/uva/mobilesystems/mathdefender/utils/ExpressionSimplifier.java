package nl.uva.mobilesystems.mathdefender.utils;

import bsh.EvalError;
import android.util.Log;

/**
 * Simple class that simplifies an expression
 * First try is that it will simplify expressions according to difficulty level
 * set in HelperClass.generateSum(DIFF, score)
 * @author siemionides
 *
 */
public class ExpressionSimplifier {
	
	private String expression;
	private int diff;
	
	public ExpressionSimplifier(String expresssion, int diff){
		this.expression = expresssion;
		this.diff = diff;
	}
	
	/**
	 * 
	 * @return Simplified expression or the same if it wasn's possible
	 */
	public String getSimplified(){
		String returnString = this.expression;
		switch(this.diff){
		case	1: //Apparently "1" is just one number, so nothing to be simplified here.
			
			break;
		case 2:		//This one is just a + b so return a result here.
			try {
				returnString = Integer.toString(HelperClass.parseSum(expression));
			} catch (EvalError e) {
				Log.e("Exception thrown", "exception 1 in ExpressionSimplified.getSimplified() class");
				e.printStackTrace();
			}
			break;
		case 3:	//currently those are 3 numbers lined with "+"/"-" operators, so just calculate the first part of it 
			//TODO - to be done;
			break;
		case 4:
			break;
		case 5:
			Log.e("FIXME", " simplifying for diff Level 5 not implemented YET" );
		}
		
		return returnString;
	}

}
