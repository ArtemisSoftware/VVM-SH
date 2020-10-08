package com.vvm.sh.ui.transferencias;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.databinding.ActivityDownloadTrabalhoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class DownloadTrabalhoActivity extends BaseDaggerActivity {


    private ActivityDownloadTrabalhoBinding activityDownloadTrabalhoBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private TransferenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TransferenciasViewModel.class);

        activityDownloadTrabalhoBinding = (ActivityDownloadTrabalhoBinding) activityBinding;
        activityDownloadTrabalhoBinding.setLifecycleOwner(this);
        activityDownloadTrabalhoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        switch (bundle.getInt(getString(R.string.argumento_download))){

            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:

                downloadTrabalhoDia();
                break;


            default:
                break;
        }

        /*
        if(bundle != null){

            if(bundle.getBoolean(getString(R.string.argumento_recarregar_tarefa)) == true){

                activityDownloadTrabalhoBinding.txtTitulo.setText(getString(R.string.recarregar_tarefa));
                viewModel.recarregarTarefa((Tarefa) bundle.get(getString(R.string.argumento_tarefa)), handlerNotificacoesUI);
            }
            else {
                activityDownloadTrabalhoBinding.txtTitulo.setText(getString(R.string.recarregar_trabalho_dia));
                activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
                viewModel.obterTrabalho(PreferenciasUtil.obterIdUtilizador(this), DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_YYYY_MM_DD), handlerNotificacoesUI);
                //viewModel.obterPendencias(PreferenciasUtil.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)));
            }
        }
        else {
            activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY));
            viewModel.obterPendencias(PreferenciasUtil.obterIdUtilizador(this));
        }
*/
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_download_trabalho;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarPendencias().observe(this, new Observer<List<Pendencia>>() {
            @Override
            public void onChanged(List<Pendencia> pendencias) {
                formatarPendencias(pendencias);
            }
        });

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:


                        break;

                    case ERRO:

                        dialogo.erro(recurso, listenerActivity);
                        break;

                    default:
                        break;
                }
            }
        });

    }


    //----------------------
    //Metodos locais
    //----------------------

    /**
     * Metodo que permite iniciar o download do trabalho do dia
     */
    private void downloadTrabalhoDia(){
        activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY));
        viewModel.obterPendencias(PreferenciasUtil.obterIdUtilizador(this));
    }



    /**
     * Metodo que permite formatar as pendencias
     * @param registos os registos pendentes
     */
    private void formatarPendencias(List<Pendencia> registos) {

        if(registos.size() == 0) {
            activityDownloadTrabalhoBinding.cardTipos.setVisibility(View.VISIBLE);
            viewModel.atualizarTipos(handlerNotificacoesUI);
        }
        else{

            activityDownloadTrabalhoBinding.txtSubTitulo.setText(getString(R.string.tarefas_pendentes));
            activityDownloadTrabalhoBinding.txtSubTitulo.setVisibility(View.VISIBLE);
            activityDownloadTrabalhoBinding.rclRegistosPendencias.setVisibility(View.VISIBLE);
            dialogo.alerta(getString(R.string.pendencias), getString(R.string.pendencias_download));

        }
    }



    /**
     * Metodo que permite obter o trabalho
     */
    private void obterTrabalho(){

        activityDownloadTrabalhoBinding.cardTrabalho.setVisibility(View.VISIBLE);
        Bundle bundle = getIntent().getExtras();

        switch (bundle.getInt(getString(R.string.argumento_download))){

            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:

                viewModel.obterTrabalho(PreferenciasUtil.obterIdUtilizador(this), handlerNotificacoesUI);
                break;


            default:
                break;
        }


//
//        if(bundle != null){
//
//            OnDialogoListener listener = new OnDialogoListener() {
//                @Override
//                public void onExecutar() {
//                    viewModel.obterUpload(PreferenciasUtil.obterIdUtilizador(getApplication()), bundle.getLong(getString(R.string.argumento_data)), handlerNotificacoesUI);
//                }
//            };
//
//            dialogo.alerta_OpcaoCancelar(getString(R.string.recarregar_trabalho_dia), getString(R.string.recarregar_trabalho_perder_dados), listener);
//        }
//        else{
//            viewModel.obterTrabalho(PreferenciasUtil.obterIdUtilizador(this), handlerNotificacoesUI);
//        }
    }


    /**
     * Metodo que permite terminar o download
     */
    private void terminarDownload() {

        OnDialogoListener listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                finish();
            }
        };

        dialogo.sucesso(getString(R.string.dados_descarregados_sucesso), listener);

    }


    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------


    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case PROCESSAMENTO_TIPOS:

                    imprimirProgresso(activityDownloadTrabalhoBinding.lnrLytProgressoTipos,
                            activityDownloadTrabalhoBinding.progressBarProgressoTipos,
                            activityDownloadTrabalhoBinding.txtProgressoTipos,
                            activityDownloadTrabalhoBinding.txtTituloProgressoTipos,  comunicado);
                    break;


                case PROCESSAMENTO_TIPOS_CONCLUIDO:

                    obterTrabalho();
                    break;


                case PROCESSAMENTO_TRABALHO:

                    imprimirProgresso(activityDownloadTrabalhoBinding.lnrLytProgressoTrabalho,
                            activityDownloadTrabalhoBinding.progressBarProgressoTrabalho,
                            activityDownloadTrabalhoBinding.txtProgressoTrabalho,
                            activityDownloadTrabalhoBinding.txtTituloProgressoTrabalho,  comunicado);
                    break;

                case PROCESSAMENTO_DOWNLOAD_CONCLUIDO:

                    terminarDownload();
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




    /**
     * Metodo que permite apresentar o progresso da execucao de um servico
     * @param comunicado os dados da execucao
     */
    private void imprimirProgresso(ConstraintLayout lnrLytProgresso, ProgressBar progressBarProgresso, TextView txtProgresso, TextView txtTituloProgresso, AtualizacaoUI.Comunicado comunicado){

        lnrLytProgresso.setVisibility(View.VISIBLE);

        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
            if(progressBarProgresso.getMax() != comunicado.obterLimite()){
                progressBarProgresso.setMax(comunicado.obterLimite());
            }
        }

        txtProgresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        txtTituloProgresso.setText(comunicado.obterMensagem());
        progressBarProgresso.setProgress(comunicado.obterPosicao());
    }
}
