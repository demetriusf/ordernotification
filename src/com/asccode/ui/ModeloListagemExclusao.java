package com.asccode.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.asccode.model.Loja;

public class ModeloListagemExclusao extends BaseAdapter {
	
	private Context context;
	private List<Loja> lojas;
	private Set<Integer> idLojasMarcadas = new HashSet<Integer>(); 
	private int posicaoAtual = 0;
	
	public ModeloListagemExclusao(Context context, List<Loja> lojas, Set<Integer> idLojasMarcadas){
		
		this.context = context;
		this.lojas = lojas;
		this.idLojasMarcadas = idLojasMarcadas;
		
	}

	public int getCount() {
		
		return this.lojas.size();
	}

	public Object getItem(int arg0) {
		
		return this.lojas.get(arg0);
	}

	public long getItemId(int arg0) {

		return arg0;
		
	}

	public View getView(int arg0, View convertView, ViewGroup arg2) {
		
		this.posicaoAtual = arg0;
		
		if(convertView == null){
		
			convertView = LayoutInflater.from(this.context).inflate(R.layout.excluir_lojas, null);
			
		}
		
		Loja loja = (Loja) this.getItem(arg0);
		
		CheckBox marcarExclusao = (CheckBox) convertView.findViewById(R.id.marcarExclusao);		
		TextView nomeLojaExclusao = (TextView) convertView.findViewById(R.id.nomeLojaExclusao);
		
		nomeLojaExclusao.setText( loja.getNome() );
		
		marcarExclusao.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			private int posicaoElemento = ModeloListagemExclusao.this.posicaoAtual;
						
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked) 
					ModeloListagemExclusao.this.idLojasMarcadas.add(this.posicaoElemento);
				else
					ModeloListagemExclusao.this.idLojasMarcadas.remove(this.posicaoElemento);
				
				//Toast.makeText(ModeloListagemExclusao.this.context, this.posicaoElemento+"mudou", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		return convertView;
		
	}

}
