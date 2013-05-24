package com.asccode.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asccode.controller.LojaController;
import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;



public class EditarLoja extends Activity {
	
	private Loja loja;
	private EditText edtNomeDaLoja;
	private EditText edtKeyDaLoja;

	@Override
	public void onCreate(Bundle bundle){
		
		super.onCreate(bundle);
		
		setContentView(R.layout.editar_loja);

		int idLoja = getIntent().getIntExtra("idLoja", 0);
		
		if(idLoja > 0){
			
			this.loja = new LojaDao(this).pegaLojaPeloId(idLoja);
			//Toast.makeText(this, this.loja.getKey(), Toast.LENGTH_LONG).show();
			
			this.edtNomeDaLoja = (EditText) findViewById(R.id.edtNomeDaLoja);
			this.edtKeyDaLoja = (EditText) findViewById(R.id.edtKeyDaLoja);

			edtNomeDaLoja.setText(this.loja.getNome());
			edtKeyDaLoja.setText(this.loja.getKey());

			Button edtSalvar = (Button) findViewById(R.id.edtSalvar);
			
			edtSalvar.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {

					loja.setNome(edtNomeDaLoja.getText().toString());
					loja.setKey(edtKeyDaLoja.getText().toString());

					Boolean feedback = new LojaController(EditarLoja.this).editar(loja);
					
					if( !feedback ){
					
						Toast.makeText(EditarLoja.this, "Para realizar a edi��o voc� precisa preencher todos os campos!", Toast.LENGTH_LONG).show();
						
					}else{// Pode editar diboa
						
						Toast.makeText(EditarLoja.this, "A altera��o da loja foi realizado com sucesso", Toast.LENGTH_LONG).show();
						finish();
						
					}
					
				}
				
			});
						
		}else{
			
			Toast.makeText(this, "Essa loja n�o existe!", Toast.LENGTH_LONG).show();
			finish();
			
		}
		
		
		
	}

}
