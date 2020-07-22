package com.vvm.sh.ui.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityInformacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class InformacaoActivity extends BaseDaggerActivity {


    private ActivityInformacaoBinding activityInformacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activityInformacaoBinding = (ActivityInformacaoBinding) activityBinding;
        activityInformacaoBinding.setLifecycleOwner(this);
        activityInformacaoBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterCliente(Preferencias.obterIdTarefa(this));

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_informacao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    */
}
