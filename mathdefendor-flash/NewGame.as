package
{ 
	import flash.display.MovieClip;
	import flash.events.*
    import flash.events.TimerEvent;
	
	public class NewGame extends MovieClip
	{
		//Create vars
		private var box:BoundingBox;
		private var newLevel:Object;
		private var pigeon:Pigeon;
		private var hawk:Hawk;
		static public var enemies:Array = [];
		private var newGame:Object;
		//These are only here 
		static public var WIDTH:int;
		static public var HEIGHT:int;
		static public var gameMode:String;
		
		public var key_pressed:int = 0;
		
		public function NewGame(gMode:String, gType:String, gWIDTH:Number, gHEIGHT:Number, pigeons:Number, hawks:Number)
		{
			addEventListener(Event.ENTER_FRAME,enterFrame);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_DOWN, on_key_down);
			Game.main.stage.addEventListener(KeyboardEvent.KEY_UP, on_key_up);
			
			gameMode = gMode;
			//Set the WIDTH and HEIGTH for this game
			WIDTH = gWIDTH;
			HEIGHT= gHEIGHT;
			//Create a bounding box
			var boundingBox = new BoundingBox;
			Game.main.stage.addChild(boundingBox);
			boundingBox.width = gWIDTH;
			boundingBox.height = gHEIGHT;
			
			if (gameMode == "Normal")
			{
				//Create actors
				for (var p = 0; p < pigeons; p++)
				{
					pigeon = new Pigeon();
					Game.main.stage.addChild(pigeon);
				}
				for (var h = 0; h < hawks; h++)
				{
					hawk = new Hawk();
					Game.main.stage.addChild(hawk);
					enemies.push(hawk);
				}
			}
			if (gameMode == "Math")
			{
				trace(gType);
				newLevel = new MathLevel(gType);
			}
		}
		
		public function on_key_down(e:KeyboardEvent):void
		{
			//trace("Key pressed + " + e);
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
			switch (key_pressed)
			{
			}

		}
		
		public static function newObject (newObj:String)
		{
			if (newObj == "Hawk")
			{
				var hawk = new Hawk;
				Game.main.stage.addChild(hawk);
				enemies.push(hawk);
			}
			if (newObj == "Sum")
			{
				MathLevel.constructSums(1);
			}
		}
	}
}