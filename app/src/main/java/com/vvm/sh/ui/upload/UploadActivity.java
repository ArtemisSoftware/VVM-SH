package com.vvm.sh.ui.upload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityUploadBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class UploadActivity extends BaseDaggerActivity {


    private ActivityUploadBinding activityUploadBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private UploadViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(UploadViewModel.class);

        activityUploadBinding = (ActivityUploadBinding) activityBinding;
        activityUploadBinding.setLifecycleOwner(this);
        //activityTiposBinding.setListener(this);
        activityUploadBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterDadosUpload(Preferencias.obterIdUtilizador(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_upload;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
