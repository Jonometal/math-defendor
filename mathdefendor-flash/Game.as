package
{ 
	import flash.display.Sprite;
	import flash.utils.Timer;
	import flash.display.*
	import flash.text.*
	import flash.events.*
	import flash.system.System;
	
	public class Game extends MovieClip
	{
		static public var main;
		static public var newGame;
		static public var memcalTimer:Timer = new Timer(3000);
		
		public function Game()
		{
			memcalTimer.addEventListener(TimerEvent.TIMER, calcMem);
			memcalTimer.start();
				
			main = this;
			trace("Game Loaded!");
			var absorberBtn = new AbsorberBtn;
			Game.main.stage.addChild(absorberBtn);
			absorberBtn.x = 300;
			absorberBtn.y = 100;
			var mathAbsorberBtn = new MathAbsorberBtn;
			Game.main.stage.addChild(mathAbsorberBtn);
			mathAbsorberBtn.x = 300;
			mathAbsorberBtn.y = 200;
			var mathDefenderBtn = new MathDefenderBtn;
			Game.main.stage.addChild(mathDefenderBtn);
			mathDefenderBtn.x = 300;
			mathDefenderBtn.y = 300;
			absorberBtn.addEventListener(MouseEvent.CLICK, makeNewGame);
			mathAbsorberBtn.addEventListener(MouseEvent.CLICK, makeNewGame);
			mathDefenderBtn.addEventListener(MouseEvent.CLICK, makeNewGame);
			//newGame = new NewGame("Math", "Defender", 600, 400, 1, 10);
			//Game.main.stage.addChild(newGame);
		}
		
		static public function makeNewGame(event:MouseEvent)
		{
		
			if(event.target is AbsorberBtn)
			{	newGame = new NewGame("Normal", "Normal", 600, 400, 1, 10);	}
			if(event.target is MathAbsorberBtn)
			{	newGame = new NewGame("Math", "Normal", 600, 400, 1, 10);	}
			if(event.target is MathDefenderBtn)
			{	newGame = new NewGame("Math", "Defender", 600, 400, 1, 10);	}	
			trace(event.target);
		}
		
		static public function calcMem(e:TimerEvent)
		{
			var mb:Number = System.totalMemory / 1024 / 1024;
			var memory:Number = Math.round(mb * 100) / 100;
			trace ("Memory usage: " + memory);
		}
	}	
		
}