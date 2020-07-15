package com.vvm.sh.ui.opcoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AtualizacaoAppActivity extends BaseDaggerActivity {



    @Inject
    ViewModelProviderFactory providerFactory;


    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OpcoesViewModel.class);
/*
        ActivityPokedexBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_pokedex);

        mainBinding.setLifecycleOwner(this);
        mainBinding.setViewmodel(viewModel);
        */

        subscreverObservadores();

        viewModel.obterAtualizacao();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_app2;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }


    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }
}
