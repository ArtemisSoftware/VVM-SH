package com.vvm.sh.carregamentos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vvm.sh.R;
import com.vvm.sh.servicos.Servico;
import com.vvm.sh.servicos.ServicoDownloadApk;
import com.vvm.sh.servicos.ServicoInstalacaoApk;
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


    private Servico servico;
    private VersaoApp versaoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao_app);

        pgr_bar_progresso_notificacao.setProgress(0);

        servico = new ServicoVersaoApp(handlerNotificacoesUI);
        servico.execute();

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

            case CONCLUIR_DOWNLOAD_APK:
                iniciarInstalacao();
                break;

            case ERRO_DOWNLOAD_APK:

                //relatorioVersao(comunicado);
                break;

            case ERRO_INSTALACAO_APK:

                break;
        }
    }


    /**
     * Metodo que permite apresentar o relatorio do pedido de versão
     * @param comunicado o resultado da execucao
     */
    private void relatorioVersao(Notificacao.Comunicado comunicado){

        Gson gson = new Gson();
        versaoApp = gson.fromJson(comunicado.obterDados(), VersaoApp.class);

        versaoApp.fixarUtilizador("500005");

        txt_versao_app.setText(versaoApp.obterVersao());
        txt_atualizacoes.setText(versaoApp.obterTexto());

        if(versaoApp.atualizar() == true) {

            servico = new ServicoDownloadApk(this, handlerNotificacoesUI, versaoApp);
            btn_iniciar.setVisibility(View.VISIBLE);
            btn_terminar.setVisibility(View.GONE);
        }
        else{
            btn_iniciar.setVisibility(View.GONE);
            btn_terminar.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Metodo que permite iniciar a instalacao da nova versao
     */
    private void iniciarInstalacao(){

        limparProgresso();
        servico = new ServicoInstalacaoApk(this, handlerNotificacoesUI, versaoApp);
        servico.execute();
    }

    //----------------------
    //Eventos
    //----------------------

    @OnClick(R.id.btn_iniciar)
    public void btn_iniciar_OnClickListener(View view) {

        btn_iniciar.setEnabled(false);
        servico.execute();

    }

    @OnClick(R.id.btn_terminar)
    public void btn_terminar_OnClickListener(View view) {



    }



}
