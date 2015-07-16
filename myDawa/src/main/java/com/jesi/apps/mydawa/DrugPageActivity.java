package com.jesi.apps.mydawa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jesi.apps.mydawa.tools.AlertDialogManager;

@SuppressLint("NewApi")
public class DrugPageActivity extends Activity{
	
	Button btnOk;
	EditText txtAge;
	EditText txtWeight;
	RadioGroup grpGender;
	RadioGroup grpPregnant;
	
	RadioButton rdnMale;
	RadioButton rdnFemale;
	RadioButton pregYes;
	RadioButton pregNo;
	
	String gender = "";
	String pregnant = "";
	
	AlertDialogManager alert = new AlertDialogManager();
	
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_drugs_page);
		
		if(android.os.Build.VERSION.SDK_INT >= 11){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		txtAge = (EditText) findViewById(R.id.txtAge);
		txtWeight = (EditText) findViewById(R.id.txtWeight);
		grpGender = (RadioGroup) findViewById(R.id.grpGender);
		grpPregnant = (RadioGroup) findViewById(R.id.grpPreg);
		
		rdnMale = (RadioButton) findViewById(R.id.rdnMale);
		rdnFemale = (RadioButton) findViewById(R.id.rdnFemale);
		pregYes = (RadioButton) findViewById(R.id.rdnPregYes);
		pregNo = (RadioButton) findViewById(R.id.rdnPregNo);
		
		btnOk = (Button) findViewById(R.id.btnOk);
		btnOk.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				if(rdnMale.isChecked()){
					gender = "male";
				}
				else if(rdnFemale.isChecked()){
					gender = "female";
				}
				
				if(pregYes.isChecked()){
					pregnant = "yes";
				}
				else if(pregNo.isChecked()){
					pregnant = "no";
				}
				
				if(gender.isEmpty() || pregnant.isEmpty() || txtAge.getText().toString().trim().isEmpty() || txtWeight.getText().toString().trim().isEmpty()){
					alert.showAlertDialog(DrugPageActivity.this,"Missing Values","Fill All Values", false);
				}
				else{
					Intent i = new Intent(DrugPageActivity.this, ResultsPageActivity.class);
					startActivity(i);
				}
			}
		});
	}
}