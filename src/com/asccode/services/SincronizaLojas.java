package com.asccode.services;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;

public class SincronizaLojas extends Service implements Runnable {

	private volatile List<Loja> lojas ;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId){
	
    	this.lojas = new LojaDao(this).pegaTodasAsLojas();
    	this.register();

		Thread t1 = new Thread(this);
		t1.start();
		
	}
	
    @Override  
    public void onDestroy() {  
        super.onDestroy();  
        stopSelf();  
    }  

    public void run(){
 	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	SharedPreferences preferences = getSharedPreferences("dadosApp", 0);
    	String registrationId = preferences.getString("registration_id", "");
    	
    	for (Loja loja : this.lojas) {
    		
    		if(loja != null && !registrationId.isEmpty()){
    			
    			HttpClient client = new DefaultHttpClient();
    			HttpGet request = new HttpGet("http://www.plugadosti.com.br/EasyC2DM/register_device.php?key="+loja.getKey()+"&idRegistration="+registrationId);
    			
    			try {
    				
    				HttpResponse response = client.execute(request);
    			} catch (ClientProtocolException e) {
    				
    				e.printStackTrace();
    			} catch (IOException e) {
    				
    				e.printStackTrace();
    			}	    			
    			
    		}
    		
		}
    	
        stopSelf(); 
    }
    
    
    private void register() {
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
     
        Intent intentBroad = new Intent();
        
        intent.putExtra("app", PendingIntent.getBroadcast(this, 0, intentBroad , 0));
        intent.putExtra("sender", "operalastorders@gmail.com");

        startService(intent); 
        
        
    }
    
}
