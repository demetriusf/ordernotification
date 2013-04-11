package com.asccode.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.asccode.services.SincronizaLojas;

public class Main extends Activity {
    
	public static final int CODIGO_ACTIVITY_CADASTRAR_LOJA = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button cadastrarLoja = (Button) findViewById(R.id.cadastrarLoja);
        
        cadastrarLoja.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity( new Intent(Main.this, CadastrarLoja.class ) );
				
			}
			
		});
        
        Button visualizarLojas = (Button) findViewById(R.id.visualizarLojas);
        
        visualizarLojas.setOnClickListener( new View.OnClickListener() {
			
			public void onClick(View arg0) {

				startActivity( new Intent(Main.this, ListagemLojas.class ) );			
				
			}
		});
        
        Button excluirLojas = (Button) findViewById(R.id.excluirLojas);
        
        excluirLojas.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity(new Intent(Main.this, ExcluirLojas.class));
				
			}
		});
        
        Button sincLojas = (Button) findViewById(R.id.sincLojas);
        
        sincLojas.setOnClickListener( new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startService(new Intent(Main.this, SincronizaLojas.class));
				Toast.makeText(Main.this, "Lojas Sincronizadas", Toast.LENGTH_SHORT).show();
			}
			
		} );
                  
    }

}