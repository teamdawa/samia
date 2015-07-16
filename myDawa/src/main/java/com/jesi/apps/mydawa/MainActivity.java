package com.jesi.apps.mydawa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.jesi.apps.mydawa.db.DbHandler;
import com.jesi.apps.mydawa.db.Drug;
import com.jesi.apps.mydawa.search.DrugAdapter;
import com.jesi.apps.mydawa.search.ResultsParse;
import com.jesi.apps.mydawa.tools.AlertDialogManager;
import com.jesi.apps.mydawa.tools.ConnectionDetector;

@SuppressLint("NewApi")
public class MainActivity extends Activity{
	
	AutoCompleteTextView searchBox;
	Button btnOk;
	AlertDialogManager alert = new AlertDialogManager();
	DbHandler db = new DbHandler(this);
	
	
	public boolean beforeSync(){
    	ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
		if (!cd.isConnectingToInternet()) {
			//alert.showAlertDialog(MainActivity.this, "Connection Error","Please connect to a working Internet connection", false);
			Toast.makeText(getApplicationContext(), "Please connect to a working internet connection", Toast.LENGTH_LONG).show();
			return false;
		}
		
		return true;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_new);
		
		//Get search box component
		searchBox = (AutoCompleteTextView) findViewById(R.id.txtSearchDawa);
		
		//Set Search adapter equals to web service API
		searchBox.setAdapter(new DrugAdapter(this, searchBox.getText().toString()));
		
		btnOk = (Button) findViewById(R.id.btnSearch);
		
		//Check for internet first
		if(!beforeSync()){
			searchBox.setEnabled(false);
			btnOk.setEnabled(false);
		}
		
		btnOk.setOnClickListener(new View.OnClickListener(){			
			@Override
			public void onClick(View v){
				Toast.makeText(getApplicationContext(), "Selected Drug is: " + searchBox.getText(), Toast.LENGTH_LONG).show();					
			}
		});
		
		searchBox.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				new MyNewTask().execute();
			}
			
		});
	}
	
	protected void onResume(){
		super.onResume();
		beforeSync();
	}
	
	/**
	 * 
	 * @author Obwogo Simeon
	 * Get drug info from API
	 *
	 */
	private class MyNewTask extends AsyncTask<String,String,String>{
		ProgressDialog dialog;
		String flag = null;
		Drug result = null;
		
		@Override
        protected void onPreExecute(){
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setIndeterminate(true);
			dialog.setTitle("Drug Info");
			dialog.setMessage("Fetching...");
			dialog.setCancelable(true);
			dialog.show();	
		}
		
		@Override
		protected String doInBackground(String... params) {
			Drug drug = new ResultsParse().getParseJsonWCF(searchBox.getText().toString());
			
			if(drug.getBrand_name() != null){
				flag = "success";
				result = drug;
				
				System.out.println("Drug " + drug.getBrand_name());
				System.out.println("Dose " + drug.getFull_dose());
				System.out.println("Sideffects " + drug.getSide_effects());
				System.out.println("Uses " + drug.getUses());
			}
			else{
				dialog.dismiss();
			}
			return flag;
		}
		
		@Override
        protected void onPostExecute(String flag){
			super.onPostExecute(flag);    		
			dialog.dismiss();
			
			try{
				if(flag == "success"){
					Intent i = new Intent(MainActivity.this, ResultsPageActivity.class);
					i.putExtra("drug", result.getBrand_name());
					i.putExtra("dose", result.getFull_dose());
					i.putExtra("avoid", result.getAvoid());
					i.putExtra("take", result.getTake_time());
					i.putExtra("not", result.getNot_for());
					i.putExtra("sideffects", result.getSide_effects());
					i.putExtra("uses", result.getUses());
					
					startActivity(i);
				}
			}
			catch(Exception e){	
				Toast.makeText(MainActivity.this, "Error During Search", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}
}
