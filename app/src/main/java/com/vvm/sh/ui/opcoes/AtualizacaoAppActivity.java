package com.vvm.sh.ui.opcoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;

import javax.inject.Inject;

public class AtualizacaoAppActivity extends BaseDaggerActivity {


    @Inject
    ViewModelProviderFactory providerFactory;


    private OpcoesViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao_app2);

        viewModel = ViewModelProviders.of(this, providerFactory).get(OpcoesViewModel.class);

        subscreverObservadores();

        viewModel.obterAtualizacao();
    }


    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }
}
