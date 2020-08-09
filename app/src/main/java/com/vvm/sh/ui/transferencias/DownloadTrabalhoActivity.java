package com.vvm.sh.ui.transferencias;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTrabalhoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class DownloadTrabalhoActivity extends BaseDaggerActivity {


    private ActivityTrabalhoBinding activityTrabalhoBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private TransferenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TransferenciasViewModel.class);

        activityTrabalhoBinding = (ActivityTrabalhoBinding) activityBinding;
        activityTrabalhoBinding.setLifecycleOwner(this);
        activityTrabalhoBinding.setViewmodel(viewModel);
        //activityTrabalhoBinding.setActivity(this);

        subscreverObservadores();


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            activityTrabalhoBinding.txtTitulo.setText(getString(R.string.recarregar_trabalho));
            activityTrabalhoBinding.txtData.setVisibility(View.VISIBLE);
            activityTrabalhoBinding.txtData.setText(DatasUtil.converterData(bundle.getLong(getString(R.string.argumento_data)), DatasUtil.FORMATO_DD_MM_YYYY));
            viewModel.obterPendencias(Preferencias.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)));
        }
        else {
            activityTrabalhoBinding.txtData.setVisibility(View.GONE);
            viewModel.obterPendencias(Preferencias.obterIdUtilizador(this));
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_trabalho;
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


    }


    //----------------------
    //Metodos locais
    //----------------------

    /**
     * Metodo que permite formatar as pendencias
     * @param registos os registos pendentes
     */
    private void formatarPendencias(List<Pendencia> registos) {

        if(registos.size() == 0) {

            Bundle bundle = getIntent().getExtras();

            if(bundle != null){
                //--viewModel.obterUpload(Preferencias.obterIdUtilizador(this), bundle.getLong(getString(R.string.argumento_data)), handlerNotificacoesUI);
            }
            else{
                viewModel.obterTrabalho(Preferencias.obterIdUtilizador(this));
            }
        }
        else{
            viewModel.obterTrabalho(Preferencias.obterIdUtilizador(this));
//            activityTrabalhoBinding.txtSubTitulo.setText(getString(R.string.tarefas_pendentes));
//            activityTrabalhoBinding.lnrLytProgresso.setVisibility(View.GONE);
//            activityTrabalhoBinding.rclRegistosPendencias.setVisibility(View.VISIBLE);
//            dialogo.alerta("Pendencias", "Existem tarefas pedentes.\r\nNão é possível realizar o upload dos dados.\r\n\r\nPor favor dê baixa de pelo menos uma atividade pendentes nas tarefas apresentadas");

        }
    }

}
