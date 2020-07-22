package com.vvm.sh.ui.atividadesExecutadas;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesExecutadasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesExecutadas.adaptadores.AtividadeExecutadaRecyclerAdapter;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.BindView;

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

        viewModel.obterAtividadesExecutadas(Preferencias.obterIdTarefa(this));


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
//
//    //------------------------
//    //Metodos locais
//    //------------------------
//
//
//    /**
//     * Metodo que permite iniciar a atividade
//     */
//    private void iniciarAtividade(){
//
//        atividadeExecutadaRecyclerAdapter = new AtividadeExecutadaRecyclerAdapter();
//        rcl_registos.setAdapter(atividadeExecutadaRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//        rcl_registos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
//    }
//
//
//    private void obterRegistos(){
//
//        //--TESTE (apagar quando houver dados)
///*
//        List<Item> t1 = new ArrayList<>();
//        t1.add(new AtividadeExecutada(1, "Atividade numero 1", "29-10-2020"));
//        t1.add(new AtividadeExecutada(2, "Atividade numero 2", "30-10-2020"));
//
//        atividadeExecutadaRecyclerAdapter.fixarRegistos(t1);
//*/
//        //TODO: chamar metodo do viewmodel
//    }
//
//    /**
//     * Metodo que permite subscrever observadores
//     */
//    private void subscreverObservadores(){
//
//
//        //TODO: subscrever observadores do viewmodel
//
//    }
}
