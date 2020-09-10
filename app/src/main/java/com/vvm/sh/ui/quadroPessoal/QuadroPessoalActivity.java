package com.vvm.sh.ui.quadroPessoal;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityQuadroPessoalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnColaboradorListener;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnOpcoesColaboradorListener;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class QuadroPessoalActivity extends BaseDaggerActivity
    implements OnColaboradorListener, OnOpcoesColaboradorListener {


    private ActivityQuadroPessoalBinding activityQuadroPessoalBinding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private QuadroPessoalViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(QuadroPessoalViewModel.class);

        activityQuadroPessoalBinding = (ActivityQuadroPessoalBinding) activityBinding;
        activityQuadroPessoalBinding.setLifecycleOwner(this);
        activityQuadroPessoalBinding.setListener(this);
        activityQuadroPessoalBinding.setViewmodel(viewModel);

        activityQuadroPessoalBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        viewModel.obterQuadroPessoal(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_quadro_pessoal;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    @Override
    public void OnColaboradorClick(ColaboradorRegisto registo) {

    }

    @Override
    public void OnEditarColaborador(int id) {

    }

    @Override
    public void OnDemitirColaborador(int id) {

    }

    @Override
    public void OnReademitirColaborador(int id) {

    }

    @Override
    public void OnRemoverColaborador(int id) {

    }

    @Override
    public void OnDetalheColaborador(int id) {

    }
}
