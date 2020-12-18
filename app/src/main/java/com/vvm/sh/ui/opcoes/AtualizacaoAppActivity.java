package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.VersaoApp;
import com.vvm.sh.databinding.ActivityAtualizacaoAppBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.NotificacaoUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class AtualizacaoAppActivity extends BaseDaggerActivity {

    private ActivityAtualizacaoAppBinding activityAtualizacaoAppBinding;

    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(OpcoesViewModel.class);

        activityAtualizacaoAppBinding = (ActivityAtualizacaoAppBinding) activityBinding;
        activityAtualizacaoAppBinding.setLifecycleOwner(this);
        activityAtualizacaoAppBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterAtualizacao(PreferenciasUtil.obterIdUtilizador(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_app;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarVersaoApp().observe(this, new Observer<VersaoApp>() {
            @Override
            public void onChanged(VersaoApp versaoApp) {

                if(versaoApp.atualizar == false){
                    dialogo.alerta(getString(R.string.atualizacao), getString(R.string.app_atualizada), listenerActivity);
                }
            }
        });


        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){


                    case ERRO:

                        dialogo.erro(recurso.messagem, (String) recurso.dados);
                        break;

                    default:
                        break;
                }

            }
        });

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

                    dialogo.erro(comunicado.obterDados());
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
        activityAtualizacaoAppBinding.progressBarProgresso.setProgress(comunicado.obterPosicao());
    }


    //----------------------
    //Eventos
    //----------------------

    @OnClick(R.id.btn_cancelar)
    public void btn_cancelar_OnClickListener(View view) {

        VersaoApp versaoApp = viewModel.versaoApp.getValue();

        if(versaoApp.atualizar == true) {
            NotificacaoUtil.notificarAtualizacaoApp(getApplication(), versaoApp.versao);
        }

        finish();
    }

    @OnClick(R.id.btn_atualizar)
    public void btn_atualizar_OnClickListener(View view) {

        activityAtualizacaoAppBinding.progressBarProgresso.setProgress(0);
        activityAtualizacaoAppBinding.txtTituloProgresso.setText(Sintaxe.SEM_TEXTO);
        activityAtualizacaoAppBinding.txtProgresso.setText(Sintaxe.SEM_TEXTO);


        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DOWNLOAD) == true){
                    viewModel.downloadApp(AtualizacaoAppActivity.this, handlerNotificacoesUI);
                }
            }
        };

        PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
    }



}
