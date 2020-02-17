package com.vvm.sh.ui.contaCorrente;

import android.os.Bundle;

import com.thefinestartist.finestwebview.FinestWebView;
import com.thefinestartist.finestwebview.listeners.WebViewListener;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.constantes.Api;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrenteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Intent intent = new Intent(this, ApresentacaoActivity.class);
        //startActivity(intent);


        subscreverObservadores();
        obterRegistos();
    }


    //---------------------------
    //Metodos locais
    //---------------------------


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        iniciarWebView("515616893");

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }



    /**
     * Metodo que permite iniciar a web view da conta corrente
     * @param nif o nif do
     */
    private void iniciarWebView(String nif){

        String url = Api.URL_CONTA_CORRENTE + nif;
        new FinestWebView.Builder(this).titleDefault(getString(R.string.conta_corrente_nif_) + nif)
                .showMenuShareVia(false)
                .showMenuCopyLink(false)
                .showMenuOpenWith(false)
                .removeWebViewListener(new WebViewListener() {
                    @Override
                    public void onPageFinished(String url) {
                        super.onPageFinished(url);

                        if (url.contains("url you want to match")){

                        }
                    }
                })
                .show(url);
    }
}
