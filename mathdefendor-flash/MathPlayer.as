package
{
	import flash.text.*;
	import flash.events.*
	import flash.display.*
	import flash.ui.*
	import flash.filters.*
	import flash.utils.Timer;
    import flash.events.TimerEvent;


	public class MathPlayer extends MovieClip
	{
		static var myValue:Number;
		private var key_pressed:int = 0;
		private var speed = 5;

		public function MathPlayer()
		{
			addEventListener(Event.ENTER_FRAME,enterFrame);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_DOWN, on_key_down);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_UP, on_key_up);
			myValue = 3;
			this.score.text = myValue.toString();
			trace (myValue.toString());
			this.score.autoSize = TextFieldAutoSize.LEFT;
		}
		
		public function enterFrame(e:Event)
		{
			for (var t in MathLevel.listOfSums)
			{
				if (this.hitTestObject(MathLevel.listOfSums[t].myText))
				{
					//trace ("MathPlayer hit: " + MathLevel.listOfSums[t]);
					addValue(MathLevel.listOfSums[t]);
					this.score.text = myValue.toString();
					this.score.autoSize = TextFieldAutoSize.LEFT
					MathLevel.listOfSums[t].die();
					if (MathLevel.gameMode == "Normal" && myValue >= 500 && myValue < 2500)
					{
						MathLevel.difficulty = 2;
					}
				}
			}
			switch (key_pressed)
			{
				case 37:
				case 65:
						x -= speed;
						break;
				case 38:
				case 87:
						y -= speed;
						break;
				case 39 :
				case 68:
						x+= speed;
						break;
				case 40:
				case 83:
						y+= speed;
						break;
				case 0 :
						speed = speed;
						break;
			}
		}
		
		public function addValue(sum:Sum)
		{
			if (sum.myResult < myValue)
			{	myValue += Math.sqrt(Math.pow(sum.myResult, 2))/2; 	}
			if (sum.myResult >= myValue)
			{	myValue -= (sum.myResult / 2);	}
		}
		
		public function on_key_down(e:KeyboardEvent):void
		{
			key_pressed = e.keyCode;
			//trace ("Kep pressed: " + key_pressed);
		}
		
		public function on_key_up(e:KeyboardEvent):void
		{
			if (e.keyCode==key_pressed)
			{
				key_pressed = 0;
			}
		}
		
		static public function getMyValue():Number
		{
			return myValue;
		}
	}
}