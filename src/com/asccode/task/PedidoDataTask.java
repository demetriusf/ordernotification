package com.asccode.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import com.asccode.model.Loja;
import com.asccode.model.Pedido;
import com.asccode.ui.NovoPedido;
import com.asccode.ui.R;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class PedidoDataTask extends AsyncTask<Object, Object, Boolean> {

    private String codigoIdPedido;
    private Context context;
    private int idLoja;
    private Pedido pedido;
    private String url;
    private ProgressDialog progressDialog;

    public PedidoDataTask(Context context, int idLoja, String codigoIdPedido, Pedido pedido, String url) {

        this.context = context;
        this.idLoja = idLoja;
        this.codigoIdPedido = codigoIdPedido;
        this.pedido = pedido;
        this.url = url;

    }

    @Override
    public void onPreExecute(){

        this.progressDialog = ProgressDialog.show(this.context, "Aguarde...", "Informações sendo coletadas.");

    }

    @Override
    protected Boolean doInBackground(Object... objects) {

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = null;

        try {

            httpGet = new HttpGet( java.net.URLDecoder.decode(url, "UTF-8")+"/pega_pedido.php?id_pedido="+codigoIdPedido );

        } catch (UnsupportedEncodingException e) {

            Log.d("WEBSERVICE",e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        }


        try {

            HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

            Gson gson = new Gson();

            this.pedido = gson.fromJson(EntityUtils.toString(httpResponse.getEntity()), Pedido.class);

            return true;

        } catch (Exception e) {

            Log.d("WEBSERVICE",e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        }

        return false;

    }

    @Override
    public void onPostExecute( Boolean result ){

        this.progressDialog.dismiss();

        if(result){

            ((NovoPedido)this.context).plotaDadosTela(pedido);

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
