package com.asccode.ui;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.asccode.dao.LojaDao;
import com.asccode.model.Loja;
import com.asccode.model.Pedido;
import com.asccode.task.PedidoDataTask;

public class NovoPedido extends Activity{

	private Loja loja = null;
    private Pedido pedido;

	@Override
	public void onCreate(Bundle bundle){
		
		super.onCreate(bundle);

		this.loja = new LojaDao(this).pegaLojaPeloId(getIntent().getIntExtra("idLoja", 0));
        int codigoIdPedido = getIntent().getIntExtra("codigoIdPedido", 0);

        new PedidoDataTask(this, this.loja.getId(), codigoIdPedido, pedido).execute();

	}

    public void plotaDadosTela(){

        this.setContentView(R.layout.novo_pedido);

        TextView novoPedidoNomeLoja = (TextView) findViewById(R.id.novoPedidoNomeLoja);
        novoPedidoNomeLoja.setText(loja.getNome());

        TextView tv = (TextView) findViewById(R.id.nomeComprador);
        tv.setText(pedido.getNomeComprador());

        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        nm.cancelAll();

    }

}
