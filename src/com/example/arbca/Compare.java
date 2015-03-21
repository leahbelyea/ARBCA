package com.example.arbca;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Compare extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
		// Set title depending on media
		if (getIntent().getStringExtra("media").equals("Groundwater")){
			setTitle(R.string.compare_gw);
		}
		if (getIntent().getStringExtra("media").equals("Soil")){
			setTitle(R.string.compare_soil);
		}
		
		setContentView(R.layout.activity_compare);
		
		// Set default input to number pad
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		// Set instructions based on media
		TextView conc_instruct = (TextView)findViewById(R.id.conc_instruct);
		if (getIntent().getStringExtra("media").equals("Groundwater")) {
			conc_instruct.setText("Enter groundwater concentrations in mg/L:");
		}
		if (getIntent().getStringExtra("media").equals("Soil")) {
			conc_instruct.setText("Enter soil concentrations in mg/kg:");
		}
		
		//Configure spinners
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
		
		Spinner resemblance_spinner = (Spinner)findViewById(R.id.resemblance);
		ArrayAdapter<CharSequence> resemblance_adapter = ArrayAdapter.createFromResource(this, R.array.resemblance_choices, android.R.layout.simple_spinner_item);
		resemblance_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resemblance_spinner.setAdapter(resemblance_adapter);
		
		// Attach listener to button
    	Button compare_gw_btn = (Button)findViewById(R.id.compare_gw);
    	compare_gw_btn.setOnClickListener(new CompareGWListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_compare_gw, menu);
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
	
	  public class CompareGWListener implements OnClickListener {
	    	
	    	
	    	public CompareGWListener () {
	    		
	    	}
	    	
	    	public void onClick(View v) {
	    		
	    		// Set media type
	    		String media = getIntent().getStringExtra("media");
	    		
	    		// Initialize concentrations to default of 0
	    		float b_conc = (float) 0;
	    		float t_conc = (float) 0;
	    		float e_conc = (float) 0;
	    		float x_conc = (float) 0;
	    		float tph_conc = (float) 0;
	    		
	    		// Get seelcted guideline parameters
	    		Spinner receptor_spinner = (Spinner)findViewById(R.id.receptor);
	    		String receptor = receptor_spinner.getSelectedItem().toString();
	    		Spinner gw_use_spinner = (Spinner)findViewById(R.id.gw_use);
	    		String gw_use = gw_use_spinner.getSelectedItem().toString();
	    		Spinner soil_type_spinner = (Spinner)findViewById(R.id.soil_type);
	    		String soil_type = soil_type_spinner.getSelectedItem().toString();
	    		
	    		// If concentrations were provided, override default values
	    		EditText b_conc_edit = (EditText)findViewById(R.id.b_conc);
	    		try { b_conc = Float.parseFloat(b_conc_edit.getText().toString()); } catch (Exception e) {}
	    		EditText t_conc_edit = (EditText)findViewById(R.id.t_conc);
	    		try { t_conc = Float.parseFloat(t_conc_edit.getText().toString()); } catch (Exception e) {}
	    		EditText e_conc_edit = (EditText)findViewById(R.id.e_conc);
	    		try { e_conc = Float.parseFloat(e_conc_edit.getText().toString()); } catch (Exception e) {}
	    		EditText x_conc_edit = (EditText)findViewById(R.id.x_conc);
	    		try { x_conc = Float.parseFloat(x_conc_edit.getText().toString()); } catch (Exception e) {}
	    		EditText tph_conc_edit = (EditText)findViewById(R.id.tph_conc);
	    		try { tph_conc = Float.parseFloat(tph_conc_edit.getText().toString()); } catch (Exception e) {}  		
	    		Spinner resemblance_spinner = (Spinner)findViewById(R.id.resemblance);
	    		String resemblance = resemblance_spinner.getSelectedItem().toString();
	    		
	    		// Open database for reading
	    		AppDatabase database = new AppDatabase(Compare.this);
	    		SQLiteDatabase rbca_db = database.getReadableDatabase();
	    		
	    		// Get specified guidelines from database
	    		Cursor results = rbca_db.rawQuery("SELECT * FROM rbsls WHERE media='" + media + "' AND receptor='" + receptor + "' AND gw_use='" + gw_use + "' AND soil_type='" + soil_type + "'", null);
	    		
	    		// Move to first result
	    		results.moveToFirst();
	    		
	    		// Convert guidelines to floats and assign to variables
	    		float b_guideline = Float.parseFloat(results.getString(5));
	    		float t_guideline = Float.parseFloat(results.getString(6));
	    		float e_guideline = Float.parseFloat(results.getString(7));
	    		float x_guideline = Float.parseFloat(results.getString(8));
    			float tph_guideline = Float.parseFloat(results.getString(9));
	    		
    			// Select appropriate TPH guideline based on resemblance
	    		if (resemblance.equals("Gasoline")) {
	    			tph_guideline = Float.parseFloat(results.getString(9));
	    		}
	    		else if (resemblance.equals("Fuel Oil")) {
	    			tph_guideline = Float.parseFloat(results.getString(10));
	    		}
	    		else if (resemblance.equals("Lube Oil")) {
	    			tph_guideline = Float.parseFloat(results.getString(11));
	    		}

	    		// Close cursor and database
	    		results.close();
	    		rbca_db.close();
	    		
	    		// Initialize exceedances as empty string
	    		String exceedances = "";
	    		
	    		// If parameter exceeds, add to exceedances string
	    		if (b_conc > b_guideline) {
	    			exceedances = exceedances + "Benzene\n";
	    		}
	    		if (t_conc > t_guideline) {
	    			exceedances = exceedances + "Toluene\n";
	    		}
	    		if (e_conc > e_guideline) {
	    			exceedances = exceedances + "Ethyl Benzene\n";
	    		}
	    		if (x_conc > x_guideline) {
	    			exceedances = exceedances + "Xylenes\n";
	    		}
	    		if (tph_conc > tph_guideline) {
	    			exceedances = exceedances + "Modified TPH\n";
	    		}
	    		
	    		// Build alert dialog to display results
	    	    AlertDialog.Builder builder = new AlertDialog.Builder(Compare.this);

	    	    // If no parameter exceed, set appropriate title but no message; 
	    	    // If there are exceedances, set appropriate title and list exceedances in message
	    	    if (exceedances.equals("")) {	    	    
	    	    	builder.setTitle("Parameters did not exceed guidelines.");
	    	    }
	    	    else {
	    	    	builder.setTitle("Parameters exceeding guidelines:");
	    	    	builder.setMessage(exceedances);
	    	    }
	    	    
	    	    // Set exit button on alert dialog
	    	    builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
	    	        public void onClick(DialogInterface dialog, int id) {
	    	        dialog.cancel();
	    	        }
	    	        
	    	    });
	    	    
	    	    // Create and show alert dialog
	    	    AlertDialog alert = builder.create();
	    	    alert.show();
	    	    
	    	}

	    }


}
