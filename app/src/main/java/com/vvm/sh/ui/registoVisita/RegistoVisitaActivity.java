package com.vvm.sh.ui.registoVisita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityRegistoVisitaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class RegistoVisitaActivity extends BaseDaggerActivity {


    private ActivityRegistoVisitaBinding activityRegistoVisitaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private RegistoVisitaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(RegistoVisitaViewModel.class);

        activityRegistoVisitaBinding = (ActivityRegistoVisitaBinding) activityBinding;
        activityRegistoVisitaBinding.setLifecycleOwner(this);
        activityRegistoVisitaBinding.setViewmodel(viewModel);

        subscreverObservadores();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_registo_visita;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.card_dados_cliente})
    public void card_dados_cliente_OnClickListener(View view) {

        Intent intent = new Intent(this, DadosClienteActivity.class);
        startActivity(intent);

    }


}
