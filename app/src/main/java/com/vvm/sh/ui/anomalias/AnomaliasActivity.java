package com.vvm.sh.ui.anomalias;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAnomaliasBinding;
import com.vvm.sh.databinding.ActivityTrabalhoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.anomalias.adaptadores.AnomaliaRecyclerAdapter;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AnomaliasActivity extends BaseDaggerActivity
        implements MaterialSpinner.OnItemSelectedListener {


    private ActivityAnomaliasBinding activityAnomaliasBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AnomaliasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(AnomaliasViewModel.class);

        activityAnomaliasBinding = (ActivityAnomaliasBinding) activityBinding;
        activityAnomaliasBinding.setLifecycleOwner(this);
        activityAnomaliasBinding.setViewmodel(viewModel);

        activityAnomaliasBinding.spnrEstado.setOnItemSelectedListener(this);

        subscreverObservadores();

        viewModel.obterAnomalias(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_anomalias;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    @OnClick(R.id.fab_adicionar)
    public void fab_adicionar_OnClickListener(View view) {

        DialogoAnomalia dialogo = new DialogoAnomalia();
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        Tipo tipo = (Tipo) item;

        if(tipo.id == 1){
            viewModel.obterAnomaliasExistentes(Preferencias.obterIdTarefa(this));
        }
        else{
            viewModel.obterAnomaliasRegistadas(Preferencias.obterIdTarefa(this));
        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_anomalias);
//
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        iniciarAtividade();
//        obterRegistos();
//    }


    //------------------------
    //Metodos locais
    //------------------------



}
