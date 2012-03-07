package nl.uva.mobilesystems.mathdefender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class StartMenu extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Button button;
		
		button = (Button)findViewById(R.id.button_tutorialstart);
		button.setOnClickListener(this);
		
		button = (Button)findViewById(R.id.button_zenstart);
		button.setOnClickListener(this);
		
		button = (Button)findViewById(R.id.button_highscores);
		button.setOnClickListener(this);
	}

	public void onClick(View v) {
		Log.d("MathDefendor", "Clicked View is: " + v);
		Intent intent;
		if(v == (Button)findViewById(R.id.button_tutorialstart)) {
			// intent = new Intent(this, InitialActivity.class); // with parameter?
			//startActivity(intent);
		}

		else if(v == (Button)findViewById(R.id.button_zenstart)){
			intent = new Intent(this, InitialActivity.class);
			startActivity(intent);
		}
		
		else if(v == (Button)findViewById(R.id.button_supermarketstart)){
			intent = new Intent(this, InitialActivity.class);
			startActivity(intent);
		}
		
		else if(v == (Button)findViewById(R.id.button_highscores)) {
			intent = new Intent(this, Highscores.class);
			startActivity(intent);
		}
		
	}
}
