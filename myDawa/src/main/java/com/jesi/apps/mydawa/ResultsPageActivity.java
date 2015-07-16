package com.jesi.apps.mydawa;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class ResultsPageActivity extends Activity{
	Button btnTrack;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstance){
		super.onCreate(savedInstance);
		setContentView(R.layout.activity_results_page);
		Intent data = getIntent();
		
		if(android.os.Build.VERSION.SDK_INT >= 11){
			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setTitle("Results For " + data.getStringExtra("drug"));
		}
		
		/*TextView lblUses = (TextView) findViewById(R.id.lblMedUses);
		TextView lblSideEffects = (TextView) findViewById(R.id.lblMedSideEffects);
		TextView lblFullDose = (TextView) findViewById(R.id.lblFullDose);
		TextView lblAvoid = (TextView) findViewById(R.id.lblAvoid);
		TextView lblTake = (TextView) findViewById(R.id.lblTake);
		TextView lblPregnant = (TextView) findViewById(R.id.lblPregnant);
		
		lblSideEffects.setText("Side Effects: " + data.getStringExtra("sideffects"));
		lblFullDose.setText("Dosage: " + data.getStringExtra("dose"));
		lblAvoid.setText("Precautions: " + data.getStringExtra("avoid"));
		lblTake.setText("Take Time: " + data.getStringExtra("take"));
		lblPregnant.setText("Contraindication: " + data.getStringExtra("not"));
		lblUses.setText("Uses: " + data.getStringExtra("uses"));
		
		btnTrack = (Button) findViewById(R.id.btnTrack);
		btnTrack.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent i = new Intent(ResultsPageActivity.this, TrackPageActivity.class);
				startActivity(i);
			}
		});*/
		
		RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
		recList.setHasFixedSize(true);
		LinearLayoutManager llm = new LinearLayoutManager(this);
		llm.setOrientation(LinearLayoutManager.VERTICAL);
		recList.setLayoutManager(llm);
		
		ResultsInfo uses = new ResultsInfo();
		uses.name = "Drug Uses";
		uses.desc = data.getStringExtra("uses");
		
		ResultsInfo dosage = new ResultsInfo();
		dosage.name = "Dosage";
		dosage.desc = data.getStringExtra("dose");
		
		ResultsInfo sideeffects = new ResultsInfo();
		sideeffects.name = "Side Effects";
		sideeffects.desc = data.getStringExtra("sideffects");
		
		ResultsInfo precuation = new ResultsInfo();
		precuation.name = "Precautions";
		precuation.desc = data.getStringExtra("avoid");
		
		ResultsInfo contradication = new ResultsInfo();
		contradication.name = "Do Not Take " + data.getStringExtra("drug") + " If";
		contradication.desc = data.getStringExtra("not");
		
		List<ResultsInfo> result = new ArrayList<ResultsInfo>();
		result.add(uses);
		result.add(dosage);
		result.add(sideeffects);
		result.add(precuation);
		result.add(contradication);
		
		ResultsAdapter ca = new ResultsAdapter(result);
        recList.setAdapter(ca);
	}
}
