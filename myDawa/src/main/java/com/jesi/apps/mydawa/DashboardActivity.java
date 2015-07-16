package com.jesi.apps.mydawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DashboardActivity extends Activity{	
	ImageButton btnSearch;
	ImageButton btnHistory;
	ImageButton btnProfile;
	ImageButton btnCalendar;
	
	@Override
	protected void onCreate(Bundle instance){
		super.onCreate(instance);
		setContentView(R.layout.activity_dashboard);
		
		btnSearch = (ImageButton) findViewById(R.id.btn_n_search);
		btnHistory = (ImageButton) findViewById(R.id.btn_n_history);
		btnProfile = (ImageButton) findViewById(R.id.btn_n_profile);
		btnCalendar = (ImageButton) findViewById(R.id.btn_n_calendar);		
		
		btnSearch.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DashboardActivity.this, MainActivity.class);
				startActivity(i);
			}			
		});
		
		btnHistory.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}			
		});
		
		btnProfile.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DashboardActivity.this, ProfileActivity.class);
				startActivity(i);
			}			
		});
		
		btnCalendar.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DashboardActivity.this, CalendarActivity.class);
				startActivity(i);
			}			
		});
	}
}