package com.jesi.apps.mydawa.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jesi.apps.mydawa.db.Drug;

public class JsonParse {
	double current_latitude, current_longitude;

	public JsonParse() {
		
	}

	public JsonParse(double current_latitude, double current_longitude) {
		this.current_latitude = current_latitude;
		this.current_longitude = current_longitude;
	}

	public List<Drug> getParseJsonWCF(String sName) {
		List<Drug> ListData = new ArrayList<Drug>();
		try {
			String temp = sName.replace(" ", "%20");
			URL js = new URL("http://www.mobidawa.com/search/search.php?name=" + temp);
			URLConnection jc = js.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
			String line = reader.readLine();
			JSONObject jsonResponse = new JSONObject(line);
			JSONArray jsonArray = jsonResponse.getJSONArray("drugs");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject r = jsonArray.getJSONObject(i);
				ListData.add(new Drug(r.getInt("id"), r.getString("brand_name"), r.getString("scientific_name"), 
						r.getString("full_dose"), r.getString("avoid"), r.getString("take_time"), r.getString("not_for"), r.getString("side_effects"), r.getString("uses")));
			}
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		return ListData;
	}
}