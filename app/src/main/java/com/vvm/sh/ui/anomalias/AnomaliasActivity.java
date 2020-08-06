package com.vvm.sh.ui.anomalias;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAnomaliasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.anomalias.adaptadores.OnAnomaliasListener;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class AnomaliasActivity extends BaseDaggerActivity
        implements MaterialSpinner.OnItemSelectedListener, OnAnomaliasListener {


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
        activityAnomaliasBinding.setListener(this);

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

        if(tipo.id == TiposConstantes.OpcoesRegistos.CONSULTAR.id){
            viewModel.obterAnomaliasExistentes(Preferencias.obterIdTarefa(this));
            activityAnomaliasBinding.recyclerViewExistentes.setVisibility(View.VISIBLE);
            activityAnomaliasBinding.recyclerViewRegistados.setVisibility(View.GONE);
        }
        else{
            viewModel.obterAnomaliasRegistadas(Preferencias.obterIdTarefa(this));
            activityAnomaliasBinding.recyclerViewExistentes.setVisibility(View.GONE);
            activityAnomaliasBinding.recyclerViewRegistados.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEditarClick(int id) {
        DialogoAnomalia dialogo = DialogoAnomalia.newInstance(id);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void onRemoverClick(AnomaliaRegistada anomalia) {

        viewModel.remover(Preferencias.obterIdTarefa(this), anomalia);
    }



}
