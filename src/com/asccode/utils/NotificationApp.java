package com.asccode.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import com.asccode.model.Loja;
import com.asccode.ui.Main;
import com.asccode.ui.NovoPedido;
import com.asccode.ui.R;

public class NotificationApp {
	
	private Context context;
	private final static int CODIGO_NOTIFICACAO_NOVO_PEDIDO = 1;
	
	public NotificationApp(Context context){
		
		this.context = context;
		
	}
	
	public void criaNotificacaoNovoPedido(Loja loja, String codigoIdPedido, String url, String valorPedido){
		
		String nomeLoja = loja.getNome();

        int when = (int) System.currentTimeMillis();

        Intent notificationIntent =  new Intent(this.context,NovoPedido.class);

        // add data
        notificationIntent.putExtra("idLoja", loja.getId());
        notificationIntent.putExtra("codigoIdPedido", codigoIdPedido);
        notificationIntent.putExtra("url", url);
        notificationIntent.putExtra("notification_when", when);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Novo pedido ")
                .setContentText(String.format("A loja %s acaba de obter um novo pedido", nomeLoja))
                .setSmallIcon(com.asccode.ui.R.drawable.ic_notification)
                .setAutoCancel(false);


        if( Double.parseDouble(valorPedido) >= 10000.00 ){

            mBuilder.setSound( Uri.parse(context.getContentResolver().SCHEME_ANDROID_RESOURCE+"://" + context.getPackageName() + "/raw/tamo_rico") );

        }else{

            mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.from(context);

        stackBuilder.addParentStack(Main.class);

        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( when, PendingIntent.FLAG_ONE_SHOT );

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify( when, mBuilder.getNotification() );
		
	}

}
