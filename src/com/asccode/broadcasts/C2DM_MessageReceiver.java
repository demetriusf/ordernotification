package com.asccode.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;
import com.asccode.utils.NotificationApp;

public class C2DM_MessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent){
		
        String action = intent.getAction();
             
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
        	
            String operacao = intent.getStringExtra("operacao"); 
             
            if("NOVO_PEDIDO".equals(operacao)){ //Cria notification novo pedido

            	String keyLoja = intent.getStringExtra("key");
            	String codigoIdPedido = intent.getStringExtra("codigoIdPedido");
            	String url = intent.getStringExtra("url");
            	String valorPedido = intent.getStringExtra("pedidoValor");

            	Loja loja = new LojaDao(context).pegaLojaPelaKey(keyLoja);

            	if( loja != null )
            		new NotificationApp(context).criaNotificacaoNovoPedido(loja, codigoIdPedido, url, valorPedido);
            	
            }
            
        }
		
	}
	
}
