package com.jesi.apps.mydawa.services;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;

import com.jesi.apps.mydawa.db.DbHandler;
import com.jesi.apps.mydawa.db.Drug;

public class DataService extends IntentService{
	
	private static final String TAG_DRUGS = "drugs";
	
	JSONArray drugs;
	JSONArray prescriptions;
	
	JSONParser jsonParser;
	DbHandler db;
	Sync sync;
	
	private static final String TAG_SUCCESS = "success";
	
	public DataService(){
		super("Empty Constructor");			
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		jsonParser = new JSONParser();
		sync = new Sync();
		db = new DbHandler(this);
		getDrugs();
	}
	
	private void getDrugs(){
		try{
			JSONObject json = sync.getDrugs();
			int success = json.getInt(TAG_SUCCESS);
			
			if(success == 1){
				drugs = json.getJSONArray(TAG_DRUGS);
				
				for (int i = 0; i < drugs.length(); i++) {
					JSONObject c = drugs.getJSONObject(i);
					Drug drug = new Drug(c.getInt("id"), c.getString("brand_name"), c.getString("scientific_name"), c.getString("full_dose"), c.getString("avoid"), c.getString("take_time"), c.getString("not_for"), c.getString("side_effects"), c.getString("uses"));
					db.insertDrug(drug);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}