package com.vvm.sh.ui.imagens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityBibliotecaImagensBinding;
import com.vvm.sh.databinding.ActivityImagensBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class ImagensActivity extends BaseDaggerActivity {


    private ActivityImagensBinding activityImagensBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private ImagemViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ImagemViewModel.class);

        activityImagensBinding = (ActivityImagensBinding) activityBinding;
        activityImagensBinding.setLifecycleOwner(this);
        activityImagensBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterImagens();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_imagens;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}