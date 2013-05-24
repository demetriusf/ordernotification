package com.asccode.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asccode.controller.LojaController;
import com.asccode.model.Loja;

public class CadastrarLoja extends Activity {

	public void onCreate( Bundle bundle ){
		
		super.onCreate(bundle);
		
		setContentView(R.layout.cadastrar_loja);
		
		Button salvar = (Button) findViewById(R.id.cadSalvar);
		
		salvar.setOnClickListener( new View.OnClickListener(){

			public void onClick(View arg0) {
				
				EditText cadNomeDaLoja = (EditText) findViewById(R.id.cadNomeDaLoja);
				EditText cadKeyDaLoja = (EditText) findViewById(R.id.cadKeyDaLoja);

				Loja loja = new Loja();
				loja.setNome(cadNomeDaLoja.getText().toString());
				loja.setKey(cadKeyDaLoja.getText().toString());

				Boolean feedback = new LojaController(CadastrarLoja.this).cadastrar(loja);
				
				if( !feedback ){
				
					Toast.makeText(CadastrarLoja.this, "Para realizar o cadastro voc� precisa preencher todos os campos!", Toast.LENGTH_LONG).show();
					
				}else{// Pode cadastrar diboa
					
					Toast.makeText(CadastrarLoja.this, "Seu cadastro foi realizado com sucesso", Toast.LENGTH_LONG).show();
					finish();
					
				}
				
				
			}
			
		} );
		
	}
	
}
