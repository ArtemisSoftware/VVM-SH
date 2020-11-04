package com.vvm.sh.ui.autenticacao;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityCarregamentoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.transferencias.TransferenciasViewModel;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class CarregamentoActivity extends BaseDaggerActivity {


    private ActivityCarregamentoBinding activityCarregamentoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TransferenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TransferenciasViewModel.class);

        activityCarregamentoBinding = (ActivityCarregamentoBinding) activityBinding;
        activityCarregamentoBinding.setLifecycleOwner(this);
        activityCarregamentoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.atualizarTipos(this, handlerNotificacoesUI);
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_carregamento;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }


    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case ERRO:

                        terminarAtualizacao();
                        dialogo.erro(getString(R.string.erro), recurso.messagem);
                        break;

                }
            }
        });
    }



    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case PROCESSAMENTO_TIPOS:

                    imprimirProgresso(comunicado);
                    break;

                case PROCESSAMENTO_TIPOS_CONCLUIDO:

                    terminarAtualizacao();
                    break;



                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };





    //----------------------------------------
    //Metodos locais
    //----------------------------------------

    /**
     * Metod que permite terminar a atualizacao
     */
    private void terminarAtualizacao() {
        activityCarregamentoBinding.txtSubTitulo.setVisibility(View.VISIBLE);
        activityCarregamentoBinding.crlProsseguir.setVisibility(View.VISIBLE);
    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.crl_prosseguir})
    public void crl_prosseguir_ButtonClick(View view) {


        if(PreferenciasUtil.obterPrimeiraUtilizacao(this) == true){
            finish();
            Intent intent = new Intent(this, ApresentacaoActivity.class);
            startActivity(intent);
        }
        else{
            finish();
            Intent intent = new Intent(this, AutenticacaoActivity.class);
            startActivity(intent);
        }

    }





    /**
     * Metodo que permite apresentar o progresso da execucao de um servico
     * @param comunicado os dados da execucao
     */
    private void imprimirProgresso(AtualizacaoUI.Comunicado comunicado){

        activityCarregamentoBinding.lnrLytTipos.setVisibility(View.VISIBLE);

        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
            if(activityCarregamentoBinding.prgBarTipos.getMax() != comunicado.obterLimite()){
                activityCarregamentoBinding.prgBarTipos.setMax(comunicado.obterLimite());
            }
        }

        activityCarregamentoBinding.txtProgressoTipos.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        activityCarregamentoBinding.txtTituloTipos.setText(comunicado.obterMensagem());
        activityCarregamentoBinding.prgBarTipos.setProgress(comunicado.obterPosicao());
    }



    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------



}
