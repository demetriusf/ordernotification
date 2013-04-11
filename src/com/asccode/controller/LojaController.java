package com.asccode.controller;

import android.content.Context;
import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;

public class LojaController {
	
	private Context context;
	
	public LojaController( Context context ){
		
		this.context = context;
		
	}
	
	
	public boolean cadastrar(Loja loja){
		
		if( loja != null ){
			
			if( !loja.getNome().isEmpty() && !loja.getKey().isEmpty() ){
			
				new LojaDao(this.context).inserir(loja);
				
				return true;
				
			}else{
				
				return false;
				
			}
			
		}else{
			
			return false;
			
		}
		
	}
	
	
	public boolean editar(Loja loja){
		
		if( loja != null ){
			
			if( !loja.getNome().isEmpty() && !loja.getKey().isEmpty() ){
			
				new LojaDao(this.context).atualizar(loja);
				
				return true;
				
			}else{
				
				return false;
				
			}
			
		}else{
			
			return false;
			
		}
		
	}
	
	public void excluirLoja(Loja loja){
		
		new LojaDao(this.context).excluir(loja);	
		
	}

}
