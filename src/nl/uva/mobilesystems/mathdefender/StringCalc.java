/*
 * package nl.uva.mobilesystems.mathdefender;
 


public class StringCalc
	{ 
       
      public static int calculate(String s)
		{ 
           
          // Convert nonenglish comma desimal delimiters to 
          // english dot 
          while (s.contains(",") != false)
			{ 
              s = s.replace(",", "."); 
          } 
           
          // These two loops beneath appends the proper 
          // multiply sign when a formula is written 
          // like 2(2+2), and converts it to 2*(2+2) 
           
         for (int g = 0; g < s.length(); g++)
			{ 
              if (s.substring(g,0) == "(" && 
                  s.substring(g-1) != "(" && 
                  s.substring(g-1) != "+" && // if operator is already assigned, skip 
                  s.substring(g-1) != "-" && 
                  s.substring(g-1) != "*" && 
                  s.substring(g-1) != "/" && 
                  g != 0 // if it is not the first char 
              )
				{ 
                  s = s.substring(0, g)+"*"+s.substring(g, s.length()); 
              } 
          } 
           
          for (int h = 0; h < s.length(); h++)
			{ 
              if (s.substring(h) == ")" && 
                  s.substring(h+1) != ")" &&  
                  s.substring(h+1) != "+" && // if operator is already assigned, skip 
                  s.substring(h+1) != "-" && 
                  s.substring(h+1) != "*" && 
                  s.substring(h+1) != "/" && 
                  h != s.length()-1 // if it is the last char, dont append 
              )
				{ 
                  s = s.substring(0, h+1)+"*"+s.substring(h+1, s.length()); 
              } 
          } 
          // Code beneath sorts out the ordered operations 
          // into a xml structure. I used XML cause it was  
          // easy to debug and navigate in. 
           
          XML equation = <equation/>; 
          var currentOrder:XML = equation; 
          var startIndex:int = 0; 
           
          for (var i:int = 0; i < s.length(); i++)
			{ 
              var o:XML; 
              var e:String; 
              if (s.substring(i) == "(")
				{ 
                  e = s.substring(startIndex, i); 
                  if (e != "")
					{ 
                      currentOrder.appendChild(e); 
                  } 
                  startIndex = i+1; 
                  o = <order/>; 
                  currentOrder.appendChild(o); 
                  currentOrder = o; 
              } 
              if (s.substring(i) == ")")
				{ 
                  e = s.substring(startIndex, i); 
                  if (e != "")
					{ 
                      currentOrder.appendChild(e); 
                  } 
                  startIndex = i+1; 
                  currentOrder = currentOrder.parent(); 
              } 
              if (i == s.length()-1)
				{ 
                  e = s.substring(startIndex, i+1) 
                  if (e != "")
					{ 
                      currentOrder.appendChild(e); 
                  } 
              } 
          } 
           
          // This traverses the operations in the correct 
          // order and calculuates them accordingly 
           
          var t:Function = function(order:XMLList):* 
			{ 
              var eq:String = ""; 
              for (var j:int = 0; j < order.length(); j++)
				{ 
                  if (order[j].nodeKind() == "text")
					{ 
                      eq += order[j]; 
                  } else if (order[j].localName() == "order")
					{ 
                      eq += arguments.callee(order[j].children()); 
                  } 
              } 
              return calc(eq); 
          } 
           
          t(equation.children()); 
           
          return t(equation.children()); 
      } 
      // The calculating function. Splits the operation 
      // by operators into objects in an array, which 
      // makes it easy to calculate the actual equation 
       
      private static function calc(eq:String):* 
		{ 
           
          var startIndex:int = 0; 
           
          var a:Array = new Array(); 
           
          for (var i:int = 0; i < eq.length(); i++)
			{ 
              var o:Object; 
              if (eq.substring(i) == "+")
				{ 
                  o = new Object(); 
                  o.operation = "add"; 
                  o.value = eq.substring(startIndex, i); 
                  a.push(o); 
                  startIndex = i+1; 
              } else if (eq.substring(i) == "-")
				{ 
                  o = new Object(); 
                  o.operation = "subtract"; 
                  o.value = eq.substring(startIndex, i); 
                  a.push(o); 
                  startIndex = i+1; 
              } else if (eq.substring(i) == "*")
				{ 
                  o = new Object(); 
                  o.operation = "multiply"; 
                  o.value = eq.substring(startIndex, i); 
                  a.push(o); 
                  startIndex = i+1; 
              } else if (eq.substring(i) == "/")
				{ 
                  o = new Object(); 
                  o.operation = "divide"; 
                  o.value = eq.substring(startIndex, i); 
                  a.push(o); 
                  startIndex = i+1; 
              } 
               
              if (i == eq.length()-1)
				{ 
                  o = new Object(); 
                  o.operation = "none"; 
                  o.value = eq.substring(startIndex, i+1); 
                  a.push(o); 
              } 
          } 
           
          var result:Number; 
           
          // If there is no value, insert zero. 
          // I.e. if a user has written -2+2, this equals to 
          // the equation 0-2+2 etc. 
          if (a[0].value == "")
			{ 
              result = 0; 
          } else
			{ 
              result = parseFloat(a[0].value); 
          } 
           
          for (var j:int = 0; j < a.length(); j++)
			{ 
               
              // If there is no value, insert zero. Prevents 
              // script from breaking, when users insert stuff 
              // like 2+++2, which translates here to 2+0+0+2 
              // Probably should return error instead? 
              if (j != a.length()-1)
				{ 
                  if (a[j+1].value == "")
					{ 
                      a[j+1].value = "0"; 
                  } 
              } 
               
              if (a[j].operation == "add")
				{ 
                  result += parseFloat(a[j+1].value); 
              } else if (a[j].operation == "subtract")
				{ 
                  result -= parseFloat(a[j+1].value); 
              } else if (a[j].operation == "multiply")
				{ 
                  result *= parseFloat(a[j+1].value); 
              } else if (a[j].operation == "divide")
				{ 
                  result /= parseFloat(a[j+1].value); 
              } 
               
          } 
          return result; 
      } 
       
  }     
} 
*/