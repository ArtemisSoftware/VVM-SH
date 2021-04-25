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
import com.vvm.sh.databinding.ActivityUploadTrabalhoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class UploadTrabalhoActivity extends BaseDaggerActivity implements OnTransferenciaListener, OnTransferenciaListener.OnUploadListener {


    private ActivityUploadTrabalhoBinding activityUploadBinding;

    private TransferenciasViewModel viewModel;

    private int contador = 0;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(TransferenciasViewModel.class);

        activityUploadBinding = (ActivityUploadTrabalhoBinding) activityBinding;
        activityUploadBinding.setLifecycleOwner(this);
        activityUploadBinding.setViewmodel(viewModel);

        activityUploadBinding.setAtualizacaoTrabalho(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));

        subscreverObservadores();

        selecionarUploadDownload();


    }



    @Override
    protected int obterLayout() {
        return R.layout.activity_upload_trabalho;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarContagemUpload().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean contagem) {

                --contador;

                if(contador == 0){
                    AtualizacaoUI_ atualizacaoUI = new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_DADOS_UPLOAD, null);
                    atualizacaoUI.mensagem = "Upload realizado com sucesso";
                    activityUploadBinding.setAtualizacaoTrabalho(atualizacaoUI);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            activityUploadBinding.ctrlUploadDados.setVisibility(View.GONE);
                            activityUploadBinding.ctrlUploadRelatorio.setVisibility(View.VISIBLE);
                        }
                    }, AppConfig.TEMPO_CONSULTA_UPLOAD);
                }
            }
        });


        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:


                        break;

                    case ERRO:
                        activityUploadBinding.ctrlUploadDados.setVisibility(View.GONE);
                        dialogo.erro("Upload", ((Codigo)recurso.dados).mensagem, listenerActivity);
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


    private void selecionarUploadDownload() {

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            activityUploadBinding.txtTituloTrabalho.setText(getString(R.string.reupload_dados) + " " + DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
            viewModel.obterPendencias_(this, PreferenciasUtil.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)), true, true);
        }
        else {
            activityUploadBinding.txtTituloTrabalho.setText(getString(R.string.upload_dados));
            viewModel.obterPendencias_(this, PreferenciasUtil.obterIdUtilizador(this), true);
        }

    }

    @Override
    public void atualizar(AtualizacaoUI_ atualizacaoUI) {

        switch (atualizacaoUI.estado){

            case PROCESSAMENTO_DADOS_UPLOAD:

                activityUploadBinding.ctrlUploadDados.setVisibility(View.VISIBLE);
                activityUploadBinding.setAtualizacaoTrabalho(atualizacaoUI);
                break;

            default:
                break;

        }
    }

    @Override
    public void atualizar(DadosUpload dadosSA, DadosUpload dadosSH) {

        contador = 0;

        if(dadosSA.dados.size() > 0){
            ++contador;
        }

        if(dadosSH.dados.size() > 0){
            ++contador;
        }

        viewModel.uploadSA(dadosSA, dadosSH);

    }

    @Override
    public void erroDados() {

    }





    @Override
    public void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI) {


    }

    @Override
    public void terminarTransferencia() {

    }

    @Override
    public void erroTransferencia() {

    }
}
