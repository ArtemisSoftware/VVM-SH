package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciasBinding;
import com.vvm.sh.databinding.ActivityOcorrenciasRegistoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaRegistoListener;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemSelected;

public class OcorrenciasRegistoActivity extends BaseDaggerActivity
        implements OnOcorrenciaRegistoListener, MaterialSpinner.OnItemSelectedListener, View.OnClickListener {



    private ActivityOcorrenciasRegistoBinding activityOcorrenciasRegistoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;

    private List<OcorrenciaRegisto> caminho;



    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasRegistoBinding = (ActivityOcorrenciasRegistoBinding) activityBinding;
        activityOcorrenciasRegistoBinding.setLifecycleOwner(this);
        activityOcorrenciasRegistoBinding.setViewmodel(viewModel);
        activityOcorrenciasRegistoBinding.setListener(this);
        activityOcorrenciasRegistoBinding.spnrOcorrencias.setOnItemSelectedListener(this);

        activityOcorrenciasRegistoBinding.txtDescricao.setOnClickListener(this);

        caminho = new ArrayList<>();

        subscreverObservadores();

        viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencias_registo;
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


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        caminho.clear();

        activityOcorrenciasRegistoBinding.txtDescricao.setVisibility(View.INVISIBLE);
        Tipo tipo = (Tipo) item;
        viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), tipo.id);
    }

    @Override
    public void OnOcorrenciaClick(OcorrenciaRegisto ocorrencia) {

        if(ocorrencia.itemRegisto() == true) {
            Intent intent = new Intent(this, RegistarOcorrenciaActivity.class);
            intent.putExtra(getString(R.string.argumento_id), ocorrencia.id);
            startActivityForResult(intent, 000000);
        }
        else {

            caminho.add(ocorrencia);

            activityOcorrenciasRegistoBinding.txtDescricao.setVisibility(View.VISIBLE);
            activityOcorrenciasRegistoBinding.txtDescricao.setText(ocorrencia.codigo + " " + ocorrencia.descricao);
            viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), ocorrencia.id);
        }
    }

    @Override
    public void onRemoverClick(OcorrenciaRegisto ocorrencia) {
        viewModel.remover(Preferencias.obterIdTarefa(this), ocorrencia.id);
    }

    @Override
    public void onClick(View v) {

        if(caminho.size() > 1) {

            OcorrenciaRegisto ocorrencia = caminho.get(caminho.size() - 1);

            activityOcorrenciasRegistoBinding.txtDescricao.setText(ocorrencia.codigo + " " + ocorrencia.descricao);
            viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), ocorrencia.id);
            caminho.remove(caminho.size() - 1);
        }
        else{
            Tipo tipo = (Tipo) activityOcorrenciasRegistoBinding.spnrOcorrencias.getItems().get(activityOcorrenciasRegistoBinding.spnrOcorrencias.getSelectedIndex());
            onItemSelected(activityOcorrenciasRegistoBinding.spnrOcorrencias, 0, 0, tipo);

        }
    }


}
