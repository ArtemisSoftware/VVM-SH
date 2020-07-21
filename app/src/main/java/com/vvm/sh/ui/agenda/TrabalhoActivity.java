package com.vvm.sh.ui.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTrabalhoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TrabalhoActivity extends BaseDaggerActivity {


    private ActivityTrabalhoBinding activityTrabalhoBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AgendaViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AgendaViewModel.class);

        activityTrabalhoBinding = (ActivityTrabalhoBinding) activityBinding;
        activityTrabalhoBinding.setLifecycleOwner(this);
        activityTrabalhoBinding.setViewmodel(viewModel);
        //activityTrabalhoBinding.setActivity(this);

        subscreverObservadores();

        viewModel.obterTrabalho("12724");
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_trabalho;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
