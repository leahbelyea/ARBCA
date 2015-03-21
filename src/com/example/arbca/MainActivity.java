package com.example.arbca;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
    	
		// Get guidelines - Attach intent to button using click listener
    	Button get_guidelines_btn = (Button)findViewById(R.id.get_guidelines_act);
    	Intent get_guidelines_int = new Intent(this, GetGuidelines.class);
    	get_guidelines_btn.setOnClickListener(new MainMenuListener(get_guidelines_int));
		
		// Compare groundwater - Attach intent to button using click listener
    	Button compare_gw_btn = (Button)findViewById(R.id.compare_gw_act);
    	Intent compare_gw_int = new Intent(this, Compare.class);
    	compare_gw_int.putExtra("media", "Groundwater");
    	compare_gw_btn.setOnClickListener(new MainMenuListener(compare_gw_int));
    	
		// Compare soil - Attach intent to button using click listener
    	Button compare_soil_btn = (Button)findViewById(R.id.compare_soil_act);
    	Intent compare_soil_int = new Intent(this, Compare.class);
    	compare_soil_int.putExtra("media", "Soil");
    	compare_soil_btn.setOnClickListener(new MainMenuListener(compare_soil_int));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

	public class MainMenuListener implements OnClickListener {
		
		Intent i;
		
		public MainMenuListener (final Intent i) {
			  this.i = i;
		}
		
		public void onClick(View v) {
			// Start specified event when button is clicked
			startActivity(i);
		}
	
	}

}