package com.vvm.sh.ui.transferencias;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityUploadBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

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
            activityUploadBinding.txtData.setVisibility(View.VISIBLE);
            activityUploadBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
            viewModel.obterPendencias(Preferencias.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)));
        }
        else {

            viewModel.obterPendencias(Preferencias.obterIdUtilizador(this));
            //viewModel.obterUpload(Preferencias.obterIdUtilizador(this), handlerNotificacoesUI);
            //viewModel.obterDadosUpload(Preferencias.obterIdUtilizador(this), handlerNotificacoesUI);
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

        viewModel.observarPendencias().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        formatarPendencias((List<Pendencia>)recurso.dados);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });


        viewModel.observarUpload().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        formatarUploads((List<Upload>)recurso.dados);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });

    }

    private void formatarUploads(List<Upload> registos) {

        if(registos.size() == 0) {

            dialogo.alerta("Upload", "Não existem dados para upload");
            activityUploadBinding.txtSubTitulo.setVisibility(View.GONE);
            activityUploadBinding.btnUpload.setVisibility(View.GONE);
            activityUploadBinding.lnrLytProgresso.setVisibility(View.GONE);

        }
        else{

            activityUploadBinding.txtSubTitulo.setText(getString(R.string.tarefas_upload));
            activityUploadBinding.lnrLytProgresso.setVisibility(View.VISIBLE);
            activityUploadBinding.btnUpload.setVisibility(View.VISIBLE);
            activityUploadBinding.rclRegistos.setVisibility(View.VISIBLE);

        }
    }

    private void formatarPendencias(List<Pendencia> registos) {

        if(registos.size() == 0) {

            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                viewModel.obterUpload(Preferencias.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)), handlerNotificacoesUI);
            }
            else{
                viewModel.obterUpload(Preferencias.obterIdUtilizador(this), handlerNotificacoesUI);
            }
        }
        else{

            activityUploadBinding.txtSubTitulo.setText(getString(R.string.tarefas_pendentes));
            activityUploadBinding.lnrLytProgresso.setVisibility(View.GONE);
            activityUploadBinding.btnUpload.setVisibility(View.GONE);
            activityUploadBinding.rclRegistosPendencias.setVisibility(View.VISIBLE);
            dialogo.alerta("Pendencias", "Existem tarefas pedentes.\r\nNão é possível realizar o upload dos dados.\r\n\r\nPor favor dê baixa de pelo menos uma atividade pendentes nas tarefas apresentadas");

        }
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
            if(activityUploadBinding.progressBarProgresso.getMax() != comunicado.obterLimite()){
                activityUploadBinding.progressBarProgresso.setMax(comunicado.obterLimite());
            }
        }

        activityUploadBinding.txtProgresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        activityUploadBinding.txtTituloProgresso.setText(comunicado.obterMensagem());
        activityUploadBinding.progressBarProgresso.setProgress(comunicado.obterPosicao());
    }



    @OnClick(R.id.btn_upload)
    public void btn_upload_OnClickListener(View view) {

    }

}
