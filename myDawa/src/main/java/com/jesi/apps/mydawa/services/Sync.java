package com.jesi.apps.mydawa.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class Sync {
	static final String UNIVERSAL_URL = "http://www.mobidawa.com/web-service/index.php";	
	JSONParser jsonParser;
	
	String tag_get_drugs = "get_drugs";
	
	public Sync(){
		jsonParser = new JSONParser();
	}
	
	public JSONObject getDrugs(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", tag_get_drugs));
		JSONObject json = jsonParser.getJSONFromUrl(UNIVERSAL_URL, params);
		return json;
	}
}