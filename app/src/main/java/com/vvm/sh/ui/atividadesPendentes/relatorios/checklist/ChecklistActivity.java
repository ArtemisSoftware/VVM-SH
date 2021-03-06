package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class ChecklistActivity extends BaseDaggerActivity
    implements OnChecklistListener.OnItemListener, OnChecklistListener.OnAlterarChecklistListener {


    private ActivityChecklistBinding activityRegistoVisitaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private ChecklistViewModel viewModel;


    private Tipo checklist;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);

        activityRegistoVisitaBinding = (ActivityChecklistBinding) activityBinding;
        activityRegistoVisitaBinding.setLifecycleOwner(this);
        activityRegistoVisitaBinding.setViewmodel(viewModel);
        activityRegistoVisitaBinding.setListener(this);

        activityRegistoVisitaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            viewModel.obterChecklist(idAtividade);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_checklist;
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

                        //dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        //dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });


        viewModel.observarChecklist().observe(this, new Observer<Tipo>() {
            @Override
            public void onChanged(Tipo tipo) {
                checklist = tipo;

                viewModel.inserirAreaGeral(PreferenciasUtil.obterIdTarefa(getApplicationContext()), getIntent().getExtras().getInt(getString(R.string.argumento_id_atividade)), checklist.id);
            }
        });

    }

    @Override
    public void OnItemClick(Item registo) {

        Intent intent = new Intent(this, SeccaoActivity.class);
        intent.putExtra(getString(R.string.argumento_registo_area), registo);
        intent.putExtra(getString(R.string.argumento_id_checklist), checklist.id);
        intent.putExtra(getString(R.string.argumento_id_atividade), getIntent().getExtras().getInt(getString(R.string.argumento_id_atividade)));
        startActivity(intent);

    }

    @Override
    public void OnEditarClick(Item registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoArea dialogo = DialogoArea.newInstance(idAtividade, checklist.id, registo);
        dialogo.show(getSupportFragmentManager(), "example dialog");

    }

    @Override
    public void OnRemoverClick(Item registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.removerArea(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo.id);
    }

    @Override
    public void OnNaoAplicaveis(Item registo) {


    }

    //--------------------
    //EVENTOS
    //--------------------

    @OnClick({R.id.card_areas})
    public void card_areas_OnClickListener(View view) {

        activityRegistoVisitaBinding.cardAreas.setVisibility(View.GONE);
        activityRegistoVisitaBinding.fabMenu.setVisibility(View.VISIBLE);
        viewModel.obterAreas(getIntent().getExtras().getInt(getString(R.string.argumento_id_atividade)), checklist.id);
    }

    @OnClick({R.id.fab_adicionar_area})
    public void fab_adicionar_area_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoArea dialogo = DialogoArea.newInstance(idAtividade, checklist.id);
        dialogo.show(getSupportFragmentManager(), "example dialog");

        activityRegistoVisitaBinding.fabMenu.close(true);
    }


    @OnClick({R.id.fab_eliminar})
    public void fab_eliminar_OnClickListener(View view) {

        DialogoChecklist dialogo = DialogoChecklist.newInstance();
        dialogo.show(getSupportFragmentManager(), "example dialog");

    }


    @Override
    public void OnAlterarCheckClick(int idChecklist) {
        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.remover(PreferenciasUtil.obterIdTarefa(this), idAtividade, idChecklist);
    }
}
