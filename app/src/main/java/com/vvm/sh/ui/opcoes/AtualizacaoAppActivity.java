package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.databinding.ActivityAtualizacaoAppBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.Notificacao;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AtualizacaoAppActivity extends BaseDaggerActivity {

    private ActivityAtualizacaoAppBinding activityAtualizacaoAppBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OpcoesViewModel.class);

        activityAtualizacaoAppBinding = (ActivityAtualizacaoAppBinding) activityBinding;
        activityAtualizacaoAppBinding.setLifecycleOwner(this);
        activityAtualizacaoAppBinding.setViewmodel(viewModel);
        activityAtualizacaoAppBinding.setActivity(this);

        subscreverObservadores();

        viewModel.obterAtualizacao();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_app;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }


    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }



    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------


    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case PROCESSAMENTO_DADOS:

                    imprimirProgresso(comunicado);
                    break;


                case CONCLUIR_DOWNLOAD_APK:

                    viewModel.instalarApp(AtualizacaoAppActivity.this, handlerNotificacoesUI);
                    break;


                case ERRO_DOWNLOAD_APK:
                case ERRO_INSTALACAO_APK:

                    relatorioErro(comunicado);
                    break;

                default:
                    //TODO: alerta de erro

                    //--Alerta de erro
                    //if(comunicado.obterMensagem() != null)
                    //--AlertaUI.erro(dialogo, comunicado.obterMensagem())
                    break;
            }

            super.handleMessage(msg);
        }
    };


    //----------------------
    //Metodos locais
    //----------------------


    /**
     * Metodo que permite apresentar o progresso da execucao de um servico
     * @param comunicado os dados da execucao
     */
    private void imprimirProgresso(AtualizacaoUI.Comunicado comunicado){


        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
            if(activityAtualizacaoAppBinding.progressBarProgresso.getMax() != comunicado.obterLimite()){
                activityAtualizacaoAppBinding.progressBarProgresso.setMax(comunicado.obterLimite());
            }
        }

        activityAtualizacaoAppBinding.txtProgresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        activityAtualizacaoAppBinding.txtTituloProgresso.setText(comunicado.obterMensagem());

    }


    /**
     * Metodo que permite apresentar um relatorio de erro
     * @param comunicado
     */
    private void relatorioErro(AtualizacaoUI.Comunicado comunicado){

        //TODO: alerta de erro

        //--Alerta de erro
        //if(comunicado.obterMensagem() != null)
        //--AlertaUI.erro(dialogo, comunicado.obterMensagem())
    }

    //----------------------
    //Eventos
    //----------------------


    public void onCancelarClick(VersaoApp versaoApp) {

        if(versaoApp.atualizar() == true) {
            Notificacao.notificarAtualizacaoApp(getApplication(), versaoApp.obterVersao());
        }

        finish();
    }


    public void onAtualizarClick() {

        activityAtualizacaoAppBinding.progressBarProgresso.setProgress(0);
        activityAtualizacaoAppBinding.txtTituloProgresso.setText(Sintaxe.SEM_TEXTO);
        activityAtualizacaoAppBinding.txtProgresso.setText(Sintaxe.SEM_TEXTO);

        viewModel.downloadApp(this, handlerNotificacoesUI);
    }

}
