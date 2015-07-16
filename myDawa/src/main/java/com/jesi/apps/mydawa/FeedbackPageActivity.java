package com.jesi.apps.mydawa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

@SuppressLint("NewApi")
public class FeedbackPageActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_feedback_page);		

		if(android.os.Build.VERSION.SDK_INT >= 11){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
}
