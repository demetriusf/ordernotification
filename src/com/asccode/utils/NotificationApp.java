package com.asccode.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.asccode.model.Loja;
import com.asccode.ui.NovoPedido;

public class NotificationApp {
	
	private Context context;
	private final static int CODIGO_NOTIFICACAO_NOVO_PEDIDO = 1;
	
	public NotificationApp(Context context){
		
		this.context = context;
		
	}
	
	public void criaNotificacaoNovoPedido(Loja loja, String codigoIdPedido){
		
		String nomeLoja = loja.getNome();
		
		NotificationManager nm = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		CharSequence tickerText = "Novo pedido";
		long when = System.currentTimeMillis();

		Notification notification = new Notification();
		notification.when = when;
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notification.icon = com.asccode.ui.R.drawable.ic_notification;
		
		CharSequence contentTitle = "Novo pedido";
		CharSequence contentText = String.format("A loja %s acaba de obter um novo pedido", nomeLoja);
		Intent notificationIntent = new Intent(this.context,NovoPedido.class);
		notificationIntent.putExtra("idLoja", loja.getId());
		notificationIntent.putExtra("codigoIdPedido", codigoIdPedido);
		PendingIntent contentIntent = PendingIntent.getActivity(this.context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(this.context, contentTitle, contentText, contentIntent);
		
		nm.notify((int)when, notification);
	
		
	}

}
