package com.jesi.apps.mydawa.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.jesi.apps.mydawa.db.Drug;

public class ResultsParse {
	public ResultsParse(){
		
	}
	
	public Drug getParseJsonWCF(String sName) {
		Drug drug = new Drug();
		
		try {
			String temp = sName.replace(" ", "%20");
			URL js = new URL("http://www.mobidawa.com/search/results.php?brandname=" + temp);
			URLConnection jc = js.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
			String line = reader.readLine();
			JSONObject jsonResponse = new JSONObject(line);
			
			int success = jsonResponse.getInt("success");
			
			if(success == 1){
				drug.setId(Integer.valueOf(jsonResponse.getString("id")));
				drug.setBrand_name(jsonResponse.getString("brand_name"));
				drug.setScientific_name(jsonResponse.getString("scientific_name"));
				drug.setFull_dose(jsonResponse.getString("full_dose"));
				drug.setAvoid(jsonResponse.getString("avoid"));
				drug.setTake_time(jsonResponse.getString("take_time"));
				drug.setNot_for(jsonResponse.getString("not_for"));
				drug.setSide_effects(jsonResponse.getString("side_effects"));
				drug.setUses(jsonResponse.getString("uses"));
			}
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		return drug;
	}
}
