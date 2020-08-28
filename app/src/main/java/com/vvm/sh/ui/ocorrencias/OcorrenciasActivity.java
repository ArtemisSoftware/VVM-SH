package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaListener;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class OcorrenciasActivity extends BaseDaggerActivity
        implements MaterialSpinner.OnItemSelectedListener, OnOcorrenciaListener {


    private ActivityOcorrenciasBinding activityOcorrenciasBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasBinding = (ActivityOcorrenciasBinding) activityBinding;
        activityOcorrenciasBinding.setLifecycleOwner(this);
        activityOcorrenciasBinding.setListener(this);
        activityOcorrenciasBinding.spnrEstado.setOnItemSelectedListener(this);
        activityOcorrenciasBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterOcorrencias(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencias;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }



    //---------------------
    //Eventos
    //---------------------


    @OnClick(R.id.fab_adicionar)
    public void fab_adicionar_OnClickListener(View view) {
        Intent intent = new Intent(this, OcorrenciasRegistoActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnOcorrenciaClick(Ocorrencia ocorrencia) {

        Intent intent = new Intent(this, OcorrenciasHistoricoActivity.class);
        intent.putExtra(getString(R.string.argumento_descricao), ocorrencia.descricaoOcorrencia);
        intent.putExtra(getString(R.string.argumento_id), ocorrencia.id);
        startActivity(intent);
    }

    @Override
    public void onRemoverClick(OcorrenciaRegisto ocorrencia) {
        viewModel.remover(PreferenciasUtil.obterIdTarefa(this), ocorrencia.resultado.id);
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        Tipo tipo = (Tipo) item;

        if(tipo.id == TiposConstantes.OpcoesRegistos.CONSULTAR.id){
            activityOcorrenciasBinding.rclRegistos.setVisibility(View.VISIBLE);
            activityOcorrenciasBinding.rclRegistosInseridos.setVisibility(View.GONE);
        }
        else{
            activityOcorrenciasBinding.rclRegistos.setVisibility(View.GONE);
            activityOcorrenciasBinding.rclRegistosInseridos.setVisibility(View.VISIBLE);
        }
    }


}
