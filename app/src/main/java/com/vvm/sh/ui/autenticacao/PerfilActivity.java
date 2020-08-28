package com.vvm.sh.ui.autenticacao;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityPerfilBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class PerfilActivity extends BaseDaggerActivity {


    private ActivityPerfilBinding activityPerfilBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AutenticacaoViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AutenticacaoViewModel.class);

        activityPerfilBinding = (ActivityPerfilBinding) activityBinding;
        activityPerfilBinding.setLifecycleOwner(this);
        activityPerfilBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterUtilizador(PreferenciasUtil.obterIdUtilizador(this));

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_perfil;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {


                switch (recurso.status){

                    case ERRO:

                        dialogo.erro(getString(R.string.erro), recurso.messagem, listenerActivity);
                        break;

                }
            }
        });
    }


}
