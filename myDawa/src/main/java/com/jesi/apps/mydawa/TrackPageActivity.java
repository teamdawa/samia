package com.jesi.apps.mydawa;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class TrackPageActivity extends Activity{
	Button btnSet;
	ImageButton setTime;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_track_page);
		
		if(android.os.Build.VERSION.SDK_INT >= 11){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		btnSet = (Button) findViewById(R.id.btnSet);
		btnSet.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent i = new Intent(TrackPageActivity.this, FeedbackPageActivity.class);
				startActivity(i);
			}
		});
		
		setTime = (ImageButton) findViewById(R.id.btn_set_time);
		setTime.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				DialogFragment newFragment = new TimePickerFragment();
			    newFragment.show(getFragmentManager(), "Set Start Time");
			}
		});
	}
	
	@SuppressLint("NewApi")
	public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState){
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			
			TimePickerDialog dlg = new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
			dlg.setTitle("Set Start Time");
			return dlg;
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute){
			// Do something with the time chosen by the user
		}
	}
}
