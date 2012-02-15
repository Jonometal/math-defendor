package
{
	import flash.events.*
	import flash.display.*
	import flash.ui.*
	import flash.filters.*
	import flash.utils.Timer;
    import flash.events.TimerEvent;


	public class Hawk extends MovieClip
	{
		public var speed:Number;
		public var dx:Number;
		public var dy:Number;
		public var angle:Number;
		public var spawnSide:Number;
		static public var list:Array = [];
		public var target:Object;
		public var inPos:Number;
		public var dist:Number;
		public var dist2:Number;
		public var collHelpers:Array = [];
		
		public function Hawk()
		{
			addEventListener(Event.ENTER_FRAME,enterFrame);
			x = -50;
			y = -50;
			target = Pigeon.list[0];
			var tempResult = sizeResult(target.width, target.height);
			width = tempResult[0];
			height = tempResult[1];
			//width = sizeResult(target.width, 0);
			//height = sizeResult(0, target.height);
			speed = Math.random() * 7 + 1;
			spawnSide = Math.floor(Math.random() * 4 +1);
			//trace ("My dir = " + spawnSide);
			//trace ("New Hawk at: " + x + " and " + y);
			list.push(this);
			inPos = 0;
			
			var temp:MovieClip = this as MovieClip;
			ColorChanger.changeColor(temp, Math.random ()* 167772150);
			ColorChanger.changeColor(target, Math.random ()* 167772150);
		}
		
		public function enterFrame(e:Event)
		{
			switch (spawnSide)
			{
				case 1:
						if (inPos == 0)
						{
							inPos = 1;
							x = Math.random() * 50 - 90;
							y = Math.random() * (NewGame.HEIGHT -50) +25;
							rotation += 180;
						}
						x += speed;
						break;
				case 2:
						if (inPos == 0)
						{
							inPos = 1;
							x = Math.random() * (NewGame.WIDTH -50) +25;
							y = Math.random() * 50 - 90;
							rotation -= 90;
						}
						y += speed;
						break;
				case 3:
						if (inPos == 0)
						{
							inPos = 1;
							x = (NewGame.WIDTH + (Math.random() * 50 +40));
							y = Math.random() * (NewGame.HEIGHT -50) +25;
						}
						x -= speed;
						break;
				case 4:
						if (inPos == 0)
						{
							inPos = 1;
							x = Math.random() * (NewGame.WIDTH -50) +25;
							y = NewGame.HEIGHT + (Math.random() * 50 + 40);
							rotation += 90;
						}
						y -= speed;
						break;
			}
			if (x < -100 || x > 800 || y < -100 || y > 500)
			{
				die();
			}
		}
		
		// This function calculates the width or height an enemy is allowed to be
		// It returns one number describing either width or height
		public function sizeResult(pWIDTH:Number, pHEIGHT:Number):Array
		{
			var result:Array = [];
			
			result[0] = Math.random() * ((4 * pHEIGHT) - pHEIGHT);
			if (result[0] <=0)
			{
				result[0] = 1;
			}
			//trace ("My resulting height = " + result[0]);
		
			result[1] = Math.random() * ((4 * pWIDTH) - pWIDTH);
			if (result[1] <=0)
			{
				result[1] = 1;
			}
			//trace ("My resulting width = " + result[1]);
			
			if (result[0] / result[1] > 2)
			{
				result[0] = (result[1] *2);
			}
			if (result[1] / result[0] > 2)
			{
				result[1] = (result[0] *2);
			}
			
			return result;
		}
		
		public function die()
		{
			//remove the enemy ship's ENTE_FRAME event
			removeEventListener(Event.ENTER_FRAME, enterFrame);
			//remove the grpahic from stage
			Game.main.stage.removeChild(this);
			for(var i:int = 0; i < NewGame.enemies.length; i++)
			{
				if(NewGame.enemies[i] == this)
				NewGame.enemies.splice(i,1);
			}
			NewGame.newObject("Hawk");
		}
	}
}