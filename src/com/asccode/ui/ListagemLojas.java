package com.asccode.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;

public class ListagemLojas extends ListActivity {

	List<Loja> lojas;
	
	@Override
	public void onCreate(Bundle bundle){
		
		super.onCreate(bundle);
		
		
		lojas = new LojaDao(this).pegaTodasAsLojas();
		
		List<String> nomesLojas = new ArrayList<String>();
		
		for (Loja loja : lojas) {
		
			nomesLojas.add(loja.getNome());
			
		}
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomesLojas ));
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){

		Loja lojaSelecionada = this.lojas.get(position);
		
		if( lojaSelecionada != null ){
			
			Intent intent = new Intent(ListagemLojas.this, EditarLoja.class);
			intent.putExtra("idLoja", lojaSelecionada.getId());

			startActivity(intent);
			
		}
		
	}
	
}
