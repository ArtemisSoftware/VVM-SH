package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAveriguacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AveriguacaoActivity extends BaseDaggerActivity {


    private ActivityAveriguacaoBinding activityAveriguacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    //--private AveriguacaoViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        //--viewModel = ViewModelProviders.of(this, providerFactory).get(AveriguacaoViewModel.class);

        activityAveriguacaoBinding = (ActivityAveriguacaoBinding) activityBinding;
        activityAveriguacaoBinding.setLifecycleOwner(this);
        //activityFormandoBinding.setListener(this);
        //activityAveriguacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            //viewModel.obterRelatorio(PreferenciasUtil.obterIdTarefa(this), bundle.getInt(getString(R.string.argumento_id_tipo)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_averiguacao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
