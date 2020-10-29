package com.vvm.sh.ui.transferencias;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.databinding.ActivityUploadBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class UploadTrabalhoActivity extends BaseDaggerActivity {


    private ActivityUploadBinding activityUploadBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TransferenciasViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TransferenciasViewModel.class);

        activityUploadBinding = (ActivityUploadBinding) activityBinding;
        activityUploadBinding.setLifecycleOwner(this);
        activityUploadBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            activityUploadBinding.txtTitulo.setText(getString(R.string.reupload_dados));
            activityUploadBinding.txtData.setVisibility(View.VISIBLE);
            activityUploadBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
            viewModel.obterPendencias(PreferenciasUtil.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)));
        }
        else {
            activityUploadBinding.txtData.setVisibility(View.GONE);
            viewModel.obterPendencias(PreferenciasUtil.obterIdUtilizador(this));
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_upload;
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

        viewModel.observarUpload().observe(this, new Observer<List<Upload>>() {
            @Override
            public void onChanged(List<Upload> uploads) {
                formatarUploads(uploads);
            }
        });



        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem, listenerActivity);
                        break;

                    case ERRO:

                        dialogo.erro(((Codigo)recurso.dados).mensagem);
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
     * Metodo que permite formatar os dados do upload
     * @param registos os registos de upload
     */
    private void formatarUploads(List<Upload> registos) {

        if(registos.size() == 0) {

            OnDialogoListener listener = new OnDialogoListener() {
                @Override
                public void onExecutar() {
                    finish();
                }
            };

            dialogo.alerta(getString(R.string.upload), getString(R.string.upload_dados_inexistentes), listener);
            activityUploadBinding.lnrLytProgresso.setVisibility(View.GONE);
        }
        else{
            activityUploadBinding.lnrLytProgresso.setVisibility(View.VISIBLE);
        }
    }


    /**
     * Metodo que permite formatar as pendencias
     * @param registos os registos pendentes
     */
    private void formatarPendencias(List<Pendencia> registos) {

        if(registos.size() == 0) {

            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                viewModel.obterUpload(PreferenciasUtil.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)), handlerNotificacoesUI);
            }
            else{
                viewModel.obterUpload(PreferenciasUtil.obterIdUtilizador(this), handlerNotificacoesUI);
            }
        }
        else{
            activityUploadBinding.lnrLytProgresso.setVisibility(View.GONE);
            dialogo.alerta(getString(R.string.pendencias), getString(R.string.pendencias_tarefas_upload));
        }
    }




    /**
     * Metodo que permite apresentar o progresso da execucao de um servico
     * @param comunicado os dados da execucao
     */
    private void imprimirProgresso(AtualizacaoUI.Comunicado comunicado){

        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
            if(activityUploadBinding.progressBarProgresso.getMax() != comunicado.obterLimite()){
                activityUploadBinding.progressBarProgresso.setMax(comunicado.obterLimite());
            }
        }

        activityUploadBinding.txtProgresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        activityUploadBinding.txtTituloProgresso.setText(comunicado.obterMensagem());
        activityUploadBinding.progressBarProgresso.setProgress(comunicado.obterPosicao());
    }


    /**
     * Metodo que permite realizar o upload dos dados
     * @param dadosUpload dados para upload
     */
    private void uploadDados(DadosUpload dadosUpload){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //TODO: comentado por causa de testes
                //--viewModel.upload(dadosUpload);
            }
        }, AppConfig.TEMPO_CONSULTA_UPLOAD);

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


                case PROCESSAMENTO_UPLOAD_CONCLUIDO:

                    uploadDados((DadosUpload) comunicado.objeto);
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



}
