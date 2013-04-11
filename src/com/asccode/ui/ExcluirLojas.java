package com.asccode.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.asccode.controller.LojaController;
import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;

public class ExcluirLojas extends ListActivity {
	
	private List<Loja> lojas ;
	private Set<Integer> idLojasMarcadas = new HashSet<Integer>(); 

	public void onCreate(Bundle bundle){
		
	super.onCreate(bundle);
		
		this.lojas = new LojaDao(this).pegaTodasAsLojas();		
		
		setListAdapter(new ModeloListagemExclusao(this, lojas, this.idLojasMarcadas));
				
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		
		boolean result = super.onCreateOptionsMenu(menu);
		
		menu.add(0, 1, 0, "Excluir Marcados");
		menu.add(0, 2, 0, "Excluir Todos");

		return result;
		
	}
	
	public boolean onOptionsItemSelected(MenuItem menuItem){
	
		boolean result = super.onOptionsItemSelected(menuItem);
		
		switch(menuItem.getItemId()){
		
			case 1: 
					// Só deleta os marcados na lista				
					this.deletaLojasMarcadas();
					finish();
					break;
			
			case 2: 
					// Deleta todos
					this.deletaTodasAsLojas();
					finish();
					break;
		
		}
		
		return result;	
		
	}
	
	public void deletaLojasMarcadas(){
		
		if( this.idLojasMarcadas.size() > 0 ){
			
			for (Integer elementoLista : this.idLojasMarcadas) {
				
				Loja loja = this.lojas.get(elementoLista);
				
				if(loja != null){
					
					new LojaController(this).excluirLoja(loja);
					
				}
				
			}
			
			Toast.makeText(this, "As lojas marcadas foram excluidas com sucesso", Toast.LENGTH_LONG).show();
			
		}else{
			Toast.makeText(this, "Nenhuma Loja Foi Marcada para exclusão", Toast.LENGTH_LONG).show();
		}	
		
	}
	
	public void deletaTodasAsLojas(){
		
		for (Loja loja : this.lojas) {
					
			if(loja != null){
				
				new LojaController(this).excluirLoja(loja);
				
			}
			
		}
		
		Toast.makeText(this, "Todas as lojas foram excluidas com sucesso", Toast.LENGTH_LONG).show();

		
	}
	
}
