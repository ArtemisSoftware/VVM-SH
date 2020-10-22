package com.vvm.sh.ui.imagens;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityGaleriaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class GaleriaActivity extends BaseDaggerActivity {


    private ActivityGaleriaBinding activityGaleriaBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private ImagemViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ImagemViewModel.class);

        activityGaleriaBinding = (ActivityGaleriaBinding) activityBinding;
        activityGaleriaBinding.setLifecycleOwner(this);
        activityGaleriaBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterImagens();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_galeria;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}