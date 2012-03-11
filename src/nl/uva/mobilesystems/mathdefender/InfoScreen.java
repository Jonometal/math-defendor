package nl.uva.mobilesystems.mathdefender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;


public class InfoScreen extends Activity implements OnClickListener {

	private String mode;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infoscreen);
		
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			mode = extras.getString("mode");
		}
		
		TextView infoScreenTitle = (TextView)findViewById(R.id.infoScreenTitle); 
		TextView infoTextView = (TextView)findViewById(R.id.infoTextView); 		
		TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);

		if(mode.equals("supermarket")){
			infoScreenTitle.setText(R.string.supermarket);
			infoTextView.setText(R.string.loremipsum1);
			tableLayout.setBackgroundResource(R.drawable.background_supermarket);
		}
		else if(mode.equals("zen")){
			infoScreenTitle.setText(R.string.zen);
			infoTextView.setText(R.string.loremipsum2);			
			tableLayout.setBackgroundResource(R.drawable.background_hsc);
		}
		
		Button button;
		button = (Button)findViewById(R.id.button_continue);
		button.setOnClickListener(this);

	}

	public void onClick(View v) {
		Log.d("MathDefendor", "Clicked button is: " + v);
		Intent intent;
		if(v == (Button)findViewById(R.id.button_continue)) {

			intent = new Intent(this, InitialActivity.class); // param?
			intent.putExtra("mode",mode);
			
			startActivity(intent);
		}
		
	}
}