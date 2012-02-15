package
{
	import flash.events.*
	import flash.display.*
	import flash.ui.*
	import flash.filters.*
	import flash.utils.Timer;
    import flash.events.TimerEvent;


	public class Pigeon extends MovieClip
	{
		static public var list:Array = [];
		public var speed:Number;
		public var key_pressed:int = 0;
		
		public function Pigeon()
		{
			addEventListener(Event.ENTER_FRAME,enterFrame);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_DOWN, on_key_down);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_UP, on_key_up);
			x = NewGame.WIDTH/2;
			y = NewGame.HEIGHT/2;
			width = 4;
			height = 4;
			speed = 4;
			//trace ("New pigeon at: " + x + " and " + y);
			list.push(this);
		}
		
		public function on_key_down(e:KeyboardEvent):void
		{
			key_pressed=e.keyCode;
		}
		public function on_key_up(e:KeyboardEvent):void
		{
			if (e.keyCode==key_pressed)
			{
				key_pressed=0;
			}
		}
		
		public function enterFrame(e:Event)
		{
			if (height > 200 || height < 5 || width > 200 || width < 5)
			{
				height = 5;
				width = 5;
			}
			for(var i:int = 0; i < NewGame.enemies.length; i++)
			{
				for (var t in NewGame.enemies[i].collHelpers)
				{
					if (this.hitTestObject(NewGame.enemies[i].collHelpers[t]))
					{
					
						var victim = NewGame.enemies[i];
						if ((this.width * this.height) > (victim.width * victim.height)/2)
						{ 
							width += (victim.width / 4);
							height += (victim.height / 4);
							NewGame.enemies[i].die();
							//trace("Growing!");
							
						}
						if ((this.width * this.height) <= (victim.width * victim.height)/2)
						{
							width -= (victim.width / 4);
							height -= (victim.height / 4);
							NewGame.enemies[i].die();
							//trace("Shrinking!");
						}
					}
				}
			}
			
			switch (key_pressed)
			{
				case 37 :
						x -= speed;
						break;
				case 38 :
						y -= speed;
						break;
				case 39 :
						x+= speed;
						break;
				case 40 :
						y+= speed;
						break;
				case 0 :
						speed = speed;
						break;
			}

		}
	}
}