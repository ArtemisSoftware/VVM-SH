package com.vvm.sh.ui.opcoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTiposBinding;
import com.vvm.sh.databinding.ActivityTiposNovosBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class TiposNovosActivity extends BaseDaggerActivity {


    private ActivityTiposNovosBinding activityTiposBinding;

    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(OpcoesViewModel.class);

        activityTiposBinding = (ActivityTiposNovosBinding) activityBinding;
        activityTiposBinding.setLifecycleOwner(this);
        activityTiposBinding.setViewmodel(viewModel);

        viewModel.obterEquipamentos();

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_tipos_novos;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}