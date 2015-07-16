package com.jesi.apps.mydawa.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jesi.apps.mydawa.db.DbHandler;
import com.jesi.apps.mydawa.tools.AlertDialogManager;
import com.jesi.apps.mydawa.tools.ConnectionDetector;

public class InternetReceiver extends BroadcastReceiver{
	ConnectionDetector cd;
	AlertDialogManager alert = new AlertDialogManager();
	DbHandler db;
	Context context;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		cd = new ConnectionDetector(context);
		db = new DbHandler(context);
		
		if (!cd.isConnectingToInternet()) {
			return;
		}
		
		Intent is = new Intent(context, DataService.class);
		context.startService(is);
	}	
}
