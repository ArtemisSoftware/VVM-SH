package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTrabalhadoresVulneraveisBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TrabalhadoresVulneraveisActivity extends BaseDaggerActivity {


    private ActivityTrabalhadoresVulneraveisBinding activityTrabalhadoresVulneraveisBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TrabalhadoresVulneraveisViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TrabalhadoresVulneraveisViewModel.class);

        activityTrabalhadoresVulneraveisBinding = (ActivityTrabalhadoresVulneraveisBinding) activityBinding;
        activityTrabalhadoresVulneraveisBinding.setLifecycleOwner(this);
        //activityFormandoBinding.setListener(this);
        activityTrabalhadoresVulneraveisBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            viewModel.validarVulnerabilidades(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_trabalhadores_vulneraveis;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
