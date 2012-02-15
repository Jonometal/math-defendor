package
{
    import flash.events.TimerEvent;
	import flash.utils.Timer;
	import flash.events.*

	public class MathLevel
	{
		public static var difficulty:Number;
		static var gameMode = "Normal";				// (Defender:Walls of sums	/	Highscore: you are your score)
		private	var listOfManSums:Array = [];			// List of special manual sums
		static public var listOfSums:Array = [];
		private	var sumsAmnt = 10;
		private var mathPlayer:MathPlayer;
		static public var waveTimer:Timer = new Timer(10000);
		static public var waveCounter:Number = 0;
		

		public function MathLevel(mathType:String) 
		{
			difficulty = 1;						// (from numbers -> calculations -> equations)
			mathPlayer = new MathPlayer();
			Game.main.stage.addChild(mathPlayer);
			mathPlayer.x = NewGame.WIDTH / 2;
			mathPlayer.y = NewGame.HEIGHT / 2;
			gameMode = mathType;
			constructSums(sumsAmnt);			// constructor code
		}
		
		public static function constructSums(amount:Number)
		{
			if (gameMode == "Normal")
			{
				for(var a:int = 0; a <  amount; a++)
				{
					var newSum = new Sum(listOfSums.length, difficulty, randommise());
					listOfSums.push(newSum);
					Game.main.stage.addChild(newSum);
				}
			}
			if (gameMode == "Defender")
			{
				waveTimer.addEventListener(TimerEvent.TIMER, newWave);
				waveTimer.start();
			}
		}
		
		public static function newWave(e:TimerEvent):void
		{
			waveCounter ++;
			if (waveCounter < 13)
			{
				difficulty = Math.ceil(waveCounter / 3)
				trace("WaveCounter" + waveCounter + " Difficulty: " + difficulty);
				for(var b:int = 0; b <  10; b++)
				{
					var newSumb = new Sum(listOfSums.length, difficulty, randommise());
					//trace("Length: " + listOfSums.length);
					listOfSums.push(newSumb);
					Game.main.stage.addChild(newSumb);
				}
			}
		}
		
		//public function randommise(){ counter++, if counter%10 == 0{type = special || double)} else{ type = "normal"} return type}
		public static function randommise():String
		{
			return "Normal";
		}
	}
}