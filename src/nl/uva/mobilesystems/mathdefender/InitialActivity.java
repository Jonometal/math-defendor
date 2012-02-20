package nl.uva.mobilesystems.mathdefender;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

public class InitialActivity extends Activity implements OnClickListener {
	Button bI;
	ProgressBar progress;
    CustomDrawableView mCustomDrawableView;
    GameView aView;
    GameModel gModel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Getting WindowManager in order to obtain screen dimentions
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display d = wm.getDefaultDisplay();
        
        //creating MODEL
        gModel = new GameModel(new Point(d.getWidth(), d.getHeight()));
        	gModel.setRunning(true);	
        	gModel.start();
        aView = new GameView(this, null,gModel); 

    	setContentView(aView);

//        setContentView(R.layout.main);
    	
//    	bI = (Button) findViewById(R.id.button1);
//        bI.setOnClickListener(this);
//        
//        progress = (ProgressBar) findViewById(R.id.progressBar1);
//        progress.setMax(10);
    	
    	
        
        
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