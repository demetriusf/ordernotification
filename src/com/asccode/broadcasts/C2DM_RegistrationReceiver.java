package com.asccode.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class C2DM_RegistrationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
 
		String action = intent.getAction();
		 
		if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            String registrationId = intent.getStringExtra("registration_id");
            
            SharedPreferences preferences = context.getSharedPreferences("dadosApp", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("registration_id", registrationId);
            editor.commit();
            
          }
		
	}
	

}
