package pl.siemion.simpleandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class InitialActivity extends Activity implements OnClickListener {
	Button bI;
	ProgressBar progress;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	
    	bI = (Button) findViewById(R.id.button1);
        bI.setOnClickListener(this);
        
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        progress.setMax(10);
    	
    	
        
        
    }
	@Override
	public void onClick(View arg0) {
		if (arg0==bI){
			progress.incrementProgressBy(1);
			if(progress.getProgress() == 10)
				progress.setProgress(0);
		}
	}
}