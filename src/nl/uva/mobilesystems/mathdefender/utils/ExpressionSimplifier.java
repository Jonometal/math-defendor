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
			/*
			 * 44+1-3
			 * -32+92-45
			 * -69+59+24
			 * 3-4-62
			 * 8-63+-29
			 */
			
			int pointer = 0;
			StringBuffer tempString;
			if(returnString.charAt(pointer) == '-')	//get rid of fist '-'
				pointer++;
			
			
			while(Character.isDigit(returnString.charAt(pointer))) //go through first number, now pointer is at "+" or "-"
				pointer++;
			
			
			if(returnString.charAt(pointer+1) == '-'){ //so we have ?a+-b?c let's put pointer on the "b"
				pointer += 2;
			}else 
				pointer++;
			
			
			while(Character.isDigit(returnString.charAt(pointer)))	//so now in ?a+-b?c the pointer is on the second ?, so we can calculate the first part
				pointer++;
			
			tempString = new StringBuffer(returnString.substring(0, pointer));	
			try {
				returnString = Integer.toString(HelperClass.parseSum(tempString.toString())) + 
							    returnString.subSequence(pointer, returnString.length());
			} catch (EvalError e) {
				Log.e("ExpressionSimplifier", "EvalError during parsing the sum:" + tempString.toString() );
				e.printStackTrace();
			}
			break;
		case 4:
			break;
		case 5:
			Log.e("FIXME", " simplifying for diff Level 5 not implemented YET" );
		}
		
		return returnString;
	}

}
