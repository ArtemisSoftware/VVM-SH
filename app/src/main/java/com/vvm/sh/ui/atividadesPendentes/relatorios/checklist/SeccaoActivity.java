package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityChecklistBinding;
import com.vvm.sh.databinding.ActivitySeccaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class SeccaoActivity extends BaseDaggerActivity implements OnChecklistListener.OnItemListener{

    private ActivitySeccaoBinding activityRegistoVisitaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private ChecklistViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);

        activityRegistoVisitaBinding = (ActivitySeccaoBinding) activityBinding;
        activityRegistoVisitaBinding.setLifecycleOwner(this);
        activityRegistoVisitaBinding.setViewmodel(viewModel);
        activityRegistoVisitaBinding.setListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            Item registo = bundle.getParcelable(getString(R.string.argumento_registo_area));
            activityRegistoVisitaBinding.setSeccao(registo);

            int idChecklist = bundle.getInt(getString(R.string.argumento_id_checklist));
            activityRegistoVisitaBinding.setChecklist(idChecklist);

            viewModel.obterSeccoes(registo.id);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_seccao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        //dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });

    }

    @Override
    public void OnItemClick(Item registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        Intent intent = new Intent(this, QuestoesChecklistActivity.class);
        intent.putExtra(getString(R.string.argumento_registo_area), registo);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnEditarClick(Item registo) {

    }

    @Override
    public void OnRemoverClick(Item registo) {

    }

    @Override
    public void OnNaoAplicaveis(Item registo) {
        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.gravarNaoAplicavel(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo);
    }
}