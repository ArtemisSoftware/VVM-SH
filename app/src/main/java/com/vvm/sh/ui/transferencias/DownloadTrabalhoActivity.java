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

import com.google.android.material.card.MaterialCardView;
import com.vvm.sh.R;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.databinding.ActivityDownloadTrabalhoBinding;
import com.vvm.sh.databinding.ActivityDownloadTrabalhoV2Binding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class DownloadTrabalhoActivity extends BaseDaggerActivity  implements OnTransferenciaListener {


    private ActivityDownloadTrabalhoV2Binding activityDownloadTrabalhoBinding;

    private TransferenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(TransferenciasViewModel.class);

        activityDownloadTrabalhoBinding = (ActivityDownloadTrabalhoV2Binding) activityBinding;
        activityDownloadTrabalhoBinding.setLifecycleOwner(this);
        activityDownloadTrabalhoBinding.setViewmodel(viewModel);

        activityDownloadTrabalhoBinding.setAtualizacaoTipos(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));
        activityDownloadTrabalhoBinding.setAtualizacaoActivPlaneaveis(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));
        activityDownloadTrabalhoBinding.setAtualizacaoTemplates(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));
        activityDownloadTrabalhoBinding.setAtualizacaoTrabalho(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));
        activityDownloadTrabalhoBinding.setAtualizacaoChecklist(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));

        subscreverObservadores();

        selecionarTipoDownload();

//        Bundle bundle = getIntent().getExtras();
//        activityDownloadTrabalhoBinding.setTipo(bundle.getInt(getString(R.string.argumento_download)));
//
//        switch (bundle.getInt(getString(R.string.argumento_download))){
//
//            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:
//
//                downloadTrabalhoDia();
//                break;
//
//            case Identificadores.Download.RECARREGAR_TRABALHO_DIA:
//
//                recarregarTrabalhoDia();
//                break;
//
//            case Identificadores.Download.RECARREGAR_TAREFA:
//
//                recarregarTarefa();
//                break;
//
//
//            case Identificadores.Download.ATUALIZAR_TRABALHO_DIA:
//
//                atualizarTarefa();
//                break;
//
//            default:
//                break;
//        }

    }




    @Override
    protected int obterLayout() {
        return R.layout.activity_download_trabalho_v2;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

//        viewModel.observarPendencias().observe(this, new Observer<List<Pendencia>>() {
//            @Override
//            public void onChanged(List<Pendencia> pendencias) {
//                if(pendencias.size() > 0) {
//                    dialogo.alerta(getString(R.string.pendencias), getString(R.string.pendencias_download));
//                }
//            }
//        });
//
//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem, (Codigo) recurso.dados, listenerActivity);
//                        break;
//
//                    default:
//                        break;
//                }
//            }
//        });

    }


    //----------------------
    //Metodos locais
    //----------------------

    private void selecionarTipoDownload(){

        Bundle bundle = getIntent().getExtras();
        //--activityDownloadTrabalhoBinding.setTipo(bundle.getInt(getString(R.string.argumento_download)));

        switch (bundle.getInt(getString(R.string.argumento_download))){

            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:

                downloadTrabalhoDia();
                break;

            case Identificadores.Download.RECARREGAR_TRABALHO_DIA:

                recarregarTrabalhoDia();
                break;

            case Identificadores.Download.RECARREGAR_TAREFA:

                recarregarTarefa();
                break;


            case Identificadores.Download.ATUALIZAR_TRABALHO_DIA:

                atualizarTarefa();
                break;

            default:
                break;
        }

    }

    private void downloadTrabalhoDia() {

        activityDownloadTrabalhoBinding.txtTituloTrabalho.setText(getString(R.string.download_trabalho_dia_) + DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY));

        viewModel.obterPendencias(this, PreferenciasUtil.obterIdUtilizador(this), false);
    }

    private void recarregarTrabalhoDia() {

        Bundle bundle = getIntent().getExtras();
        activityDownloadTrabalhoBinding.txtTituloTrabalho.setText(getString(R.string.recarregar_trabalho_dia_) + DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
    }

    private void recarregarTarefa() {
        viewModel.recarregarTrabalho();
    }

    private void atualizarTarefa() {
    }


    //----------------------
    //Eventos
    //----------------------

    @Override
    public void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI) {

        switch (atualizacaoUI.estado){

            case PROCESSAMENTO_TIPOS:

                activityDownloadTrabalhoBinding.setAtualizacaoTipos(atualizacaoUI);
                break;

            case PROCESSAMENTO_ATIVIDADES_PLANEAVEIS:

                activityDownloadTrabalhoBinding.setAtualizacaoActivPlaneaveis(atualizacaoUI);
                break;

            case PROCESSAMENTO_TEMPLATE_AVALIACAO_RISCOS:

                activityDownloadTrabalhoBinding.setAtualizacaoTemplates(atualizacaoUI);
                break;


            case PROCESSAMENTO_CHECKLIST:

                activityDownloadTrabalhoBinding.setAtualizacaoChecklist(atualizacaoUI);
                break;


            case PROCESSAMENTO_TRABALHO:

                activityDownloadTrabalhoBinding.setAtualizacaoTrabalho(atualizacaoUI);
                break;

            default:
                break;

        }
    }

    @Override
    public void terminarTransferencia() {


        Bundle bundle = getIntent().getExtras();
        //--activityDownloadTrabalhoBinding.setTipo(bundle.getInt(getString(R.string.argumento_download)));

        switch (bundle.getInt(getString(R.string.argumento_download))){

            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:

                viewModel.obterTrabalho(this, this, PreferenciasUtil.obterIdUtilizador(this));
                break;

            case Identificadores.Download.RECARREGAR_TRABALHO_DIA:

                //recarregarTrabalhoDia();
                break;

            case Identificadores.Download.RECARREGAR_TAREFA:

                //recarregarTarefa();
                break;


            case Identificadores.Download.ATUALIZAR_TRABALHO_DIA:

                //atualizarTarefa();
                break;

            default:
                break;
        }
    }

    @Override
    public void erroTransferencia() {

    }


//
//    //----------------------
//    //Metodos locais
//    //----------------------
//
//    /**
//     * Metodo que permite iniciar o download do trabalho do dia
//     */
//    private void downloadTrabalhoDia(){
//        activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY));
//        viewModel.obterPendencias(this, handlerNotificacoesUI, PreferenciasUtil.obterIdUtilizador(this), false);
//        //--Para teste --viewModel.atualizarTipos(this,  handlerNotificacoesUI);
//    }
//
//
//    /**
//     * Metodo que permite recarregar o trabalho de um dia especifico
//     */
//    private void recarregarTrabalhoDia() {
//
//        Bundle bundle = getIntent().getExtras();
//        activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
//        activityDownloadTrabalhoBinding.cardTrabalho.setVisibility(View.VISIBLE);
//
//        viewModel.recarregarTrabalho(this, PreferenciasUtil.obterIdUtilizador(this), DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_YYYY_MM_DD), handlerNotificacoesUI);
//    }
//
//    private void atualizarTarefa() {
//
//        Bundle bundle = getIntent().getExtras();
//        activityDownloadTrabalhoBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
//        activityDownloadTrabalhoBinding.cardTrabalho.setVisibility(View.VISIBLE);
//
//        viewModel.recarregarTrabalho(this, PreferenciasUtil.obterIdUtilizador(this), DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_YYYY_MM_DD), handlerNotificacoesUI);
//
//    }
//
//
//    /**
//     * Metodo que permite recarregar uma tarefa especifica
//     */
//    private void recarregarTarefa(){
//
//        Bundle bundle = getIntent().getExtras();
//        viewModel.recarregarTarefa(this, (Tarefa) bundle.get(getString(R.string.argumento_tarefa)), handlerNotificacoesUI);
//    }
//
//
//
//
//
//
//
//    /**
//     * Metodo que permite obter o trabalho
//     */
//    private void obterTrabalho(){
//
//        activityDownloadTrabalhoBinding.cardTrabalho.setVisibility(View.VISIBLE);
//        Bundle bundle = getIntent().getExtras();
//
//        switch (bundle.getInt(getString(R.string.argumento_download))){
//
//            case Identificadores.Download.DOWNLOAD_TRABALHO_DIA:
//
//                viewModel.obterTrabalho(this, PreferenciasUtil.obterIdUtilizador(this), handlerNotificacoesUI);
//                break;
//
//            case Identificadores.Download.RECARREGAR_TRABALHO_DIA:
//
//                OnDialogoListener listener = new OnDialogoListener() {
//                    @Override
//                    public void onExecutar() {
//                        viewModel.recarregarTrabalho(DownloadTrabalhoActivity.this, PreferenciasUtil.obterIdUtilizador(getApplication()), DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_YYYY_MM_DD), handlerNotificacoesUI);
//                    }
//                };
//
//                dialogo.alerta(getString(R.string.recarregar_trabalho_dia), getString(R.string.recarregar_trabalho_perder_dados), listener, true);
//                break;
//
//            case Identificadores.Download.RECARREGAR_TAREFA:
//                viewModel.recarregarTarefa(this, bundle.getParcelable(getString(R.string.argumento_tarefa)), handlerNotificacoesUI);
//                break;
//
//
//            default:
//                break;
//        }
//
//    }
//
//
//    /**
//     * Metodo que permite terminar o download
//     */
//    private void terminarDownload() {
//
//        OnDialogoListener listener = new OnDialogoListener() {
//            @Override
//            public void onExecutar() {
//                finish();
//            }
//        };
//
//        dialogo.sucesso(getString(R.string.dados_descarregados_sucesso), listener);
//
//    }
//
//
//    //----------------------------------------
//    //HANDLER (notificacoes para o ui)
//    //----------------------------------------
//
//
//    final Handler handlerNotificacoesUI = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//
//            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;
//
//            switch (comunicado.obterCodigo()) {
//
//                case PROCESSAMENTO_TIPOS:
//
//                    imprimirProgresso(activityDownloadTrabalhoBinding.cardTipos,
//                            activityDownloadTrabalhoBinding.progressBarProgressoTipos,
//                            activityDownloadTrabalhoBinding.txtProgressoTipos,
//                            activityDownloadTrabalhoBinding.txtTituloProgressoTipos,  comunicado);
//                    break;
//
//
//                case PROCESSAMENTO_TIPOS_CONCLUIDO:
//
//                    obterTrabalho();
//                    break;
//
//
//                case PROCESSAMENTO_TRABALHO:
//
//                    imprimirProgresso(activityDownloadTrabalhoBinding.cardTrabalho,
//                            activityDownloadTrabalhoBinding.progressBarProgressoTrabalho,
//                            activityDownloadTrabalhoBinding.txtProgressoTrabalho,
//                            activityDownloadTrabalhoBinding.txtTituloProgressoTrabalho,  comunicado);
//                    break;
//
//                case PROCESSAMENTO_DOWNLOAD_CONCLUIDO:
//
//                    terminarDownload();
//                    break;
//
//                default:
//                    break;
//            }
//
//            super.handleMessage(msg);
//        }
//    };
//
//
//
//
//    /**
//     * Metodo que permite apresentar o progresso da execucao de um servico
//     * @param comunicado os dados da execucao
//     */
//    private void imprimirProgresso(MaterialCardView cardView, ProgressBar progressBarProgresso, TextView txtProgresso, TextView txtTituloProgresso, AtualizacaoUI.Comunicado comunicado){
//
//        cardView.setVisibility(View.VISIBLE);
//
//        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
//            if(progressBarProgresso.getMax() != comunicado.obterLimite()){
//                progressBarProgresso.setMax(comunicado.obterLimite());
//            }
//        }
//
//        txtProgresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
//        txtTituloProgresso.setText(comunicado.obterMensagem());
//        progressBarProgresso.setProgress(comunicado.obterPosicao());
//    }
}
