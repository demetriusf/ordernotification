package com.asccode.ui;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;
import com.asccode.model.Pedido;

public class NovoPedido extends Activity implements Runnable {
	
	private Loja loja = null;
	private Pedido pedido = new Pedido();
	private boolean terminouThread = false;

	@Override
	public void onCreate(Bundle bundle){
		
		super.onCreate(bundle);
		
		Thread t1 = new Thread(this);
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.start();
				
		setContentView(R.layout.novo_pedido);
		
		while( !this.terminouThread ){
		
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
		
		// Daqui para baixo significa que a Thread terminou.
		((TextView) findViewById(R.id.loader)).setVisibility(0);
		
		this.loja = new LojaDao(this).pegaLojaPeloId(getIntent().getIntExtra("idLoja", 0));
		
		TextView novoPedidoNomeLoja = (TextView) findViewById(R.id.novoPedidoNomeLoja);
		novoPedidoNomeLoja.setText(loja.getNome());
		
	}

	
	@Override
	public void onStop(){
		this.onDestroy();		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.i("Teste", "destruiu - "+loja.getNome());		
		finish();
		
	}
	
	
	public void run(){
		
		HttpClient httpCliente = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("");
		
		try {
			
			HttpResponse response = httpCliente.execute(httpGet);
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			this.terminouThread = true;
			
		}
		
	}
}
