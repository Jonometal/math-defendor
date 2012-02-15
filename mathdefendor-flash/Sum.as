package 
{
	import flash.text.*;
	import flash.events.*
	import flash.display.*
	import flash.ui.*
	import flash.filters.*
	import flash.utils.Timer;
    import flash.events.TimerEvent;

	public class Sum extends MovieClip
	{
		public var mySum:String = "";	//:String;	// (contains sum)
		public var myType;	// (Normal, double difficulty, score dependent(3245/score), special(1337^1337/Pi)), type is determined at random
		public var myResult:Number;	// (Result of sum:Number)
		public var score;		// (how much a succesful solving of this sum is worth when absorbed)
		public var listOfElements:Array = [];
		public var stringOfElements:String;
		public var myText;
		public var spawnSide;
		public var inPos;
		public var speed;
		public var myIncrementer;
		public var myDifficulty;
		public var gameMode;
		
		public function Sum(incrementer:Number, difficulty:Number, modifier:String)
		{
			addEventListener(Event.ENTER_FRAME,enterFrame);
			x = -99;
			y = -99;
			myType = modifier;	// (Normal, double difficulty, score dependent(3245/score), special(1337^1337/Pi)), type is determined at random
			score = 0;		// (how much a succesful solving of this sum is worth when absorbed)
			myText = new TextField();
			myText.x = -99;
			myText.y = -99;
			gameMode = MathLevel.gameMode;
			
			if (gameMode == "Normal"){spawnSide = Math.floor(Math.random() * 4 +1);}
			else if (gameMode == "Defender"){spawnSide = 0;}
			inPos = 0;
			speed = 2;
			myIncrementer = (incrementer + 1);
			//if (myIncrementer <= 0){ myIncrementer = 1;}
			myDifficulty = difficulty;
			
			constructSum();
			myResult = Math.ceil(StringCalc.calculate(mySum));
			//Plan fpr creating functions that are printable:
			// Make substrings of all elements: element1 = 56, element 2=randomOperator(), element3 = 13
			// listOFElements.Push(toString(element 1-3))
			// for (x:int = 0; x < listOfElements.length; x++){ elementsString.add(listOfElements[x]) }
			// printTest.text = elementsString;
			// myResult = [elementsString];
			//http://www.dreamincode.net/forums/topic/212045-as3-evaluating-equation-from-string-using-own-methods-in-my-equationsolveras/
			//of
			// var result = ExternalInterface.call(myEvalFunctionInJS,formula)
			// http://www.actionscript.org/resources/articles/745/2/JavaScript-and-VBScript-Injection-in-ActionScript-3/Page2.html
			// 	
			// full codelib in as3: http://www.actionscript.org/forums/showthread.php3?t=160367
			//	var val:Number = Eval("9+6+2*3-12/12+3*7-4/2");
			//	trace(val);
			//	function Eval(str:String):Number{
			//	return ExternalInterface.call("function(){return eval(\""+str+"\");}");} 

			//trace ("My result = " + myResult);
		}
		
		public function enterFrame(e:Event)
		{
			switch (spawnSide)
			{
				case 1:
						if (inPos == 0)
						{
							inPos = 1;
							myText.x = Math.random() * 50 - 90;
							myText.y = Math.random() * (NewGame.HEIGHT -50) +25;
						}
						myText.x += speed;
						break;
				case 2:
						if (inPos == 0)
						{
							inPos = 1;
							myText.x = Math.random() * (NewGame.WIDTH -50) +25;
							myText.y = Math.random() * 50 - 90;
						}
						myText.y += speed;
						break;
				case 3:
						if (inPos == 0)
						{
							inPos = 1;
							myText.x = (NewGame.WIDTH + (Math.random() * 50 +40));
							myText.y = Math.random() * (NewGame.HEIGHT -50) +25;
						}
						myText.x -= speed;
						break;
				case 4:
						if (inPos == 0)
						{
							inPos = 1;
							myText.x = Math.random() * (NewGame.WIDTH -50) +25;
							myText.y = NewGame.HEIGHT + (Math.random() * 50 + 40);
						}
						myText.y -= speed;
						break;
				case 0:
						if (inPos == 0)
						{
							inPos = 1;
							myText.x = NewGame.WIDTH + 99;
							//trace ("at y: " + myIncrementer % 10);
							if (myIncrementer % 10 == 0) { myText.y = 10 * 40 - 30;	}
							else {	myText.y = (myIncrementer % 10 ) * 40 - 30;	}
						}
						myText.x -= speed;
						break;
			}
			if (myText.x < -100 || myText.x > 800 || myText.y < -100 || myText.y > 500)
			{
				die();
			}
		}
		
		public function constructSum()
		{
			{	
				//trace("Difficulty: " + myDifficulty);
				switch (myDifficulty)
				{
							case 1:
								if (gameMode == "Defender"){var element = Math.floor(Math.random() * (MathPlayer.getMyValue() + 10)) - 5;}
								else {var element = Math.floor(Math.random() * MathPlayer.getMyValue() + 10) - 5;}
								//trace ("Element generated: " + element);
								listOfElements.push(element);
								mySum = mySum + (element.toString());
								//trace ("Mysum: " + mySum);
								break;
							case 2:
								if (gameMode == "Defender"){var element0 = Math.floor(Math.random() * (MathPlayer.getMyValue() + 100)) - 50;}
								else {var element0 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element0);
								var element1 = randomConstructor("+-...");
								listOfElements.push(element1);
								if (gameMode == "Defender"){var element2 = Math.floor(Math.random() * (MathPlayer.getMyValue() + 100)) - 50;}
								else {var element2 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element2);
								//trace("ListOfElements" + listOfElements);
								mySum = mySum + (element0.toString());
								mySum = mySum + (element1.toString());
								mySum = mySum + (element2.toString());
								break;
							case 3:
								if (gameMode == "Defender"){var element0 = Math.floor(Math.random() * (MathPlayer.getMyValue() + 200)) - 100;}
								else {var element0 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element0);
								var element1 = randomConstructor("+-...");
								listOfElements.push(element1);
								if (gameMode == "Defender"){var element2 = Math.floor(Math.random() * (MathPlayer.getMyValue() + 200)) - 100;}
								else {var element2 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element2);
								var element3 = randomConstructor("-+...");
								listOfElements.push(element3);
								if (gameMode == "Defender"){var element4 = Math.floor(Math.random() * (MathPlayer.getMyValue() + 100)) - 50;}
								else {var element4 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element4);
								//trace("ListOfElements" + listOfElements);
								mySum = mySum + (element0.toString());
								mySum = mySum + (element1.toString());
								mySum = mySum + (element2.toString());
								mySum = mySum + (element3.toString());
								mySum = mySum + (element4.toString());
								break;
							case 4:
								if (gameMode == "Defender"){var element0 = Math.floor(Math.random() * (MathPlayer.getMyValue()))}
								else {var element0 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element0);
								var element1 = randomConstructor("*/...");
								listOfElements.push(element1);
								if (gameMode == "Defender"){var element2 = Math.floor(Math.random() * (MathPlayer.getMyValue()))}
								else {var element2 = Math.floor((Math.random() * myIncrementer) * 100);}
								listOfElements.push(element2);
								//trace("ListOfElements" + listOfElements);
								mySum = mySum + (element0.toString());
								mySum = mySum + (element1.toString());
								mySum = mySum + (element2.toString());
								break;
							case 5:
								break;
							default:
								trace ("Error! Difficulty == 0");
								break;
				}
				var textX = 0;
				var textY = 0;
				for (var e:int = 0; e < listOfElements.length; e++)
				{ 
					myText.appendText(listOfElements[e].toString());
				}
				Game.main.stage.addChild(myText);
				myText.autoSize = TextFieldAutoSize.LEFT;
				myText.background = true;
			}
		}
		
		public function randomConstructor(operatorString:String):String
		{
			var amnt = 0;
			var choice = 0;
			for(var o:int=0; o < 5; o++)
			{
				if (operatorString.charAt(o) != "."){ amnt+=1; }
			}
			choice = Math.floor(Math.random() *amnt);
			//trace("char chose: " + choice + " from "  + amnt);
			return operatorString.charAt(choice);
		}
		
		public function die()
		{
			//remove the enemy ship's ENTE_FRAME event
			removeEventListener(Event.ENTER_FRAME, enterFrame);
			//remove the grpahic from stage
			Game.main.stage.removeChild(this);
			Game.main.stage.removeChild(this.myText);
			for(var i:int = 0; i < MathLevel.listOfSums.length; i++)
			{
				if(MathLevel.listOfSums[i] == this)
				{
					MathLevel.listOfSums.splice(i,1);
				}
			}
			if(gameMode == "Normal"){NewGame.newObject("Sum");}
		}
	}	
}
