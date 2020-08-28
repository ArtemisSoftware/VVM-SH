package com.vvm.sh.ui.atividadesExecutadas;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesExecutadasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AtividadesExecutadasActivity extends BaseDaggerActivity {



    private ActivityAtividadesExecutadasBinding activityAtividadesExecutadasBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activityAtividadesExecutadasBinding = (ActivityAtividadesExecutadasBinding) activityBinding;
        activityAtividadesExecutadasBinding.setLifecycleOwner(this);
        activityAtividadesExecutadasBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterAtividadesExecutadas(PreferenciasUtil.obterIdTarefa(this));


    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atividades_executadas;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


//
//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
//
//    private AtividadeExecutadaRecyclerAdapter atividadeExecutadaRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_atividades_executadas);
//
//
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        iniciarAtividade();
//        obterRegistos();
//    }

}
