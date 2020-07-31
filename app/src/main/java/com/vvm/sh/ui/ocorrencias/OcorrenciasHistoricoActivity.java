package com.vvm.sh.ui.ocorrencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciasHistoricoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class OcorrenciasHistoricoActivity extends BaseDaggerActivity {

    private ActivityOcorrenciasHistoricoBinding activityOcorrenciasHistoricoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasHistoricoBinding = (ActivityOcorrenciasHistoricoBinding) activityBinding;
        activityOcorrenciasHistoricoBinding.setLifecycleOwner(this);
        activityOcorrenciasHistoricoBinding.setViewmodel(viewModel);


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            viewModel.obterHistorico(bundle.getInt(getString(R.string.argumento_id)));
            activityOcorrenciasHistoricoBinding.txtOcorrencia.setText((bundle.getString(getString(R.string.argumento_descricao))));
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencias_historico;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
