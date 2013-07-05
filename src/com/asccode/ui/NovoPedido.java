package com.asccode.ui;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
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
        String codigoIdPedido = getIntent().getStringExtra("codigoIdPedido");
        String url = getIntent().getStringExtra("url");

        Log.d("WEBSERVICE", "Nome: "+this.loja.getNome()+" - CodigoIdPedido: "+codigoIdPedido+" - url: "+url);

        new PedidoDataTask(this, this.loja.getId(), codigoIdPedido, pedido, url).execute();

	}

    public void plotaDadosTela( Pedido pedido ){

        this.pedido = pedido;
        this.setContentView(R.layout.novo_pedido);

        TextView novoPedidoNomeLoja = (TextView) findViewById(R.id.novoPedidoNomeLoja);
        novoPedidoNomeLoja.setText(loja.getNome());

        TextView nomeComprador = (TextView) findViewById(R.id.nomeComprador);
        nomeComprador.setText(pedido.getNomeComprador());

        TextView telefoneComprador = (TextView) findViewById(R.id.telefoneComprador);
        telefoneComprador.setText(pedido.getTelefoneComprador());

        TextView emailComprador = (TextView) findViewById(R.id.emailComprador);
        emailComprador.setText(pedido.getEmailComprador());

        TextView valorTotalPedido = (TextView) findViewById(R.id.valorTotalPedido);
        valorTotalPedido.setText(pedido.getValorTotalPedido());

        TextView formaDePagamento = (TextView) findViewById(R.id.formaDePagamento);
        formaDePagamento.setText(pedido.getFormaDePagamento());

        TextView numeroDeParcelas = (TextView) findViewById(R.id.numeroDeParcelas);
        numeroDeParcelas.setText(pedido.getNumeroDeParcelas());

        TextView tipoDeFrete = (TextView) findViewById(R.id.tipoDeFrete);
        tipoDeFrete.setText(pedido.getTipoDeFrete());

        TextView pedidoStatus = (TextView) findViewById(R.id.pedidoStatus);
        pedidoStatus.setText(pedido.getPedidoStatus());

        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        nm.cancel(getIntent().getIntExtra("notification_when", 0));

    }

}
