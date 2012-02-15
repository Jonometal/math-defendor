package
{ 
	import flash.geom.*;
	
	public class ColorChanger
	{
		public static function changeColor(object:Object, color:Number)
		{
     		var colorchange:ColorTransform = new ColorTransform();
    		colorchange.color = color;
     		object.transform.colorTransform = colorchange;
		}
	}
			
}