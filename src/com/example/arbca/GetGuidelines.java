package com.example.arbca;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GetGuidelines extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.get_guidelines);
		setContentView(R.layout.activity_get_guidelines);
		
		// Configure spinners
		Spinner media_spinner = (Spinner)findViewById(R.id.media);
		ArrayAdapter<CharSequence> media_adapter = ArrayAdapter.createFromResource(this, R.array.media_choices, android.R.layout.simple_spinner_item);
		media_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		media_spinner.setAdapter(media_adapter);
		
		Spinner receptor_spinner = (Spinner)findViewById(R.id.receptor);
		ArrayAdapter<CharSequence> receptor_adapter = ArrayAdapter.createFromResource(this, R.array.receptor_choices, android.R.layout.simple_spinner_item);
		receptor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		receptor_spinner.setAdapter(receptor_adapter);
		
		Spinner gw_use_spinner = (Spinner)findViewById(R.id.gw_use);
		ArrayAdapter<CharSequence> gw_use_adapter = ArrayAdapter.createFromResource(this, R.array.gw_use_choices, android.R.layout.simple_spinner_item);
		gw_use_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gw_use_spinner.setAdapter(gw_use_adapter);
		
		Spinner soil_type_spinner = (Spinner)findViewById(R.id.soil_type);
		ArrayAdapter<CharSequence> soil_type_adapter = ArrayAdapter.createFromResource(this, R.array.soil_type_choices, android.R.layout.simple_spinner_item);
		soil_type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		soil_type_spinner.setAdapter(soil_type_adapter);
		
		// Attach listener to button
    	Button show_guidelines_btn = (Button)findViewById(R.id.show_guidelines);
    	show_guidelines_btn.setOnClickListener(new ShowGuidelinesListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_get_guidelines, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public class ShowGuidelinesListener implements OnClickListener {
    	
    	
    	public ShowGuidelinesListener () {
    		
    	}
    	
    	public void onClick(View v) {
    		
    		// Get selected media
    		Spinner media_spinner = (Spinner)findViewById(R.id.media);
    		String media = media_spinner.getSelectedItem().toString();
    		
    		// Get selected receptor
    		Spinner receptor_spinner = (Spinner)findViewById(R.id.receptor);
    		String receptor = receptor_spinner.getSelectedItem().toString();
    		
    		// Get selected groundwater use
    		Spinner gw_use_spinner = (Spinner)findViewById(R.id.gw_use);
    		String gw_use = gw_use_spinner.getSelectedItem().toString();
    		
    		// Get selected soil type
    		Spinner soil_type_spinner = (Spinner)findViewById(R.id.soil_type);
    		String soil_type = soil_type_spinner.getSelectedItem().toString();
    		
    		// Open database for reading
    		AppDatabase database = new AppDatabase(GetGuidelines.this);
    		SQLiteDatabase rbca_db = database.getReadableDatabase();
    		
    		// Get specified guidelines from database
    		Cursor results = rbca_db.rawQuery("SELECT * FROM rbsls WHERE media='" + media + "' AND receptor='" + receptor + "' AND gw_use='" + gw_use + "' AND soil_type='" + soil_type + "'", null);
    		
    		// Move to first result
    		results.moveToFirst();
    		
    		// Assign guidelines to variables
    		String b_guideline = results.getString(5);
    		String t_guideline = results.getString(6);
    		String e_guideline = results.getString(7);
    		String x_guideline = results.getString(8);
    		String tph_guideline = results.getString(9) + " / " + results.getString(10) + " / " + results.getString(11);

    		//Close cursor and database
    		results.close();
    		rbca_db.close();
    		
    		// Build alert dialog to display results
    	    AlertDialog.Builder builder = new AlertDialog.Builder(GetGuidelines.this);

    		// Build alert dialog title
    		String title = media + " Guidelines";
    	    // Set alert dialog title
    	    builder.setTitle(title);
    	    
    	    // Inflate custom layout for displaying results
    	    LayoutInflater inflater = GetGuidelines.this.getLayoutInflater();
    	    View view = inflater.inflate(R.layout.guidelines_dialog, null);

    		// Build description of guidelines
    		String guideline_desc = "For a " + receptor.toLowerCase() + " receptor,  " + gw_use.toLowerCase() + " groundwater use, and " + soil_type.toLowerCase() + " soil.";
    		
    	    // Set description of guidelines to TextView
     	    TextView guideline_desc_text = (TextView)view.findViewById(R.id.guideline_desc);
            guideline_desc_text.setText(guideline_desc);

    	    // Set guidelines to corresponding TextViews
     	    TextView b_guideline_text = (TextView)view.findViewById(R.id.b_guideline);
            b_guideline_text.setText(b_guideline);
            
     	    TextView t_guideline_text = (TextView)view.findViewById(R.id.t_guideline);
            t_guideline_text.setText(t_guideline);
            
     	    TextView e_guideline_text = (TextView)view.findViewById(R.id.e_guideline);
            e_guideline_text.setText(e_guideline);
            
     	    TextView x_guideline_text = (TextView)view.findViewById(R.id.x_guideline);
            x_guideline_text.setText(x_guideline);
            
     	    TextView tph_guideline_text = (TextView)view.findViewById(R.id.tph_guideline);
            tph_guideline_text.setText(tph_guideline);
            
            // Set inflated custom view to alert dialog build
    	    builder.setView(view);
    	    
    	    // Set exit button on alert dialog
    	    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int id) {
    	        dialog.cancel();
    	        }
    	        
    	    });
    	    
    	    //Create and show alert dialog
    	    AlertDialog alert = builder.create();
    	    alert.show();
    	    
    	}

    }

}
