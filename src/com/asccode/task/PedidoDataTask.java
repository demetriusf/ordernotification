package com.asccode.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import com.asccode.model.Pedido;
import com.asccode.ui.NovoPedido;
import com.asccode.ui.R;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;

public class PedidoDataTask extends AsyncTask<Object, Object, Boolean> {

    private int idLoja;
    private int codigoIdPedido;
    private Context context;
    private Pedido pedido;
    private ProgressDialog progressDialog;

    public PedidoDataTask(Context context, int codigoIdPedido, int idLoja, Pedido pedido) {
        this.context = context;
        this.codigoIdPedido = codigoIdPedido;
        this.idLoja = idLoja;
        this.pedido = pedido;
    }

    @Override
    public void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, "Aguarde...", "Informações sendo coletadas.");

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.plugadosti.com.br/EasyC2DM/recovery_order_data.php?loja="+this.idLoja+"&codigoIdPedido="+this.codigoIdPedido);


        try {

            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            Gson gson = new Gson();

            Pedido pedido = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Pedido.class);

            return true;

        } catch (Exception e) {

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        }

        return false;

    }

    @Override
    public void onPostExecute( Boolean result ){

        this.progressDialog.dismiss();

        if(result){

            ((NovoPedido)this.context).plotaDadosTela();

        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setTitle("Erro");
            builder.setMessage("Não foi possível recupar os dados. Verifique sua conexão com a internet!");
            builder.setCancelable(false);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((Activity) PedidoDataTask.this.context).finish();
                }
            });

            builder.show();

        }

    }

}
