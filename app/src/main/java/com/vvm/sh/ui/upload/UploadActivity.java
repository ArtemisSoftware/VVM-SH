package com.vvm.sh.ui.upload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityUploadBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class UploadActivity extends BaseDaggerActivity {


    private ActivityUploadBinding activityUploadBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private UploadViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(UploadViewModel.class);

        activityUploadBinding = (ActivityUploadBinding) activityBinding;
        activityUploadBinding.setLifecycleOwner(this);
        activityUploadBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterDadosUpload(Preferencias.obterIdUtilizador(this), handlerNotificacoesUI);
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


}
