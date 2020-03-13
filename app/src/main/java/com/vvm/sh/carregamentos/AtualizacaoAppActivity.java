package com.vvm.sh.carregamentos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vvm.sh.R;
import com.vvm.sh.servicos.ServicoVersaoApp;
import com.vvm.sh.servicos.VersaoApp;
import com.vvm.sh.util.Notificacao;

import butterknife.BindView;
import butterknife.OnClick;
import static com.vvm.sh.util.Notificacao.Codigo.*;

public class AtualizacaoAppActivity extends CarregamentoActivity {


    @BindView(R.id.txt_atualizacoes)
    TextView txt_atualizacoes;

    @BindView(R.id.txt_versao_app)
    TextView txt_versao_app;

    @BindView(R.id.btn_iniciar)
    Button btn_iniciar;

    @BindView(R.id.btn_terminar)
    Button btn_terminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao_app);


        pgr_bar_progresso_notificacao.setProgress(30);


        new ServicoVersaoApp(handlerNotificacoesUI).execute();

    }


    //----------------------
    //Metodos locais
    //----------------------

    @Override
    protected void gerirNotificacoesWs(Notificacao.Comunicado comunicado) {

        switch (comunicado.obterCodigo()) {

            case CONCLUIR_PEDIDO_VERSAO_APP:

                relatorioVersao(comunicado);
                break;
        }
    }


    private void relatorioVersao(Notificacao.Comunicado comunicado){

        Gson gson = new Gson();
        VersaoApp versaoApp = gson.fromJson(comunicado.obterDados(), VersaoApp.class);

        versaoApp.fixarUtilizador("500005");

        txt_versao_app.setText(versaoApp.obterVersao());
        txt_atualizacoes.setText(versaoApp.obterTexto());

        if(versaoApp.atualizar() == true) {
            btn_iniciar.setVisibility(View.VISIBLE);
            btn_terminar.setVisibility(View.GONE);
        }
        else{
            btn_iniciar.setVisibility(View.GONE);
            btn_terminar.setVisibility(View.VISIBLE);
        }
    }


    //----------------------
    //Eventos
    //----------------------

    @OnClick(R.id.btn_terminar)
    public void btn_terminar_OnClickListener(View view) {



    }


}
