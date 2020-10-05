package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityQuestoesChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class QuestoesChecklistActivity extends BaseDaggerActivity
        implements OnChecklistListener.OnQuestaoListener {



    private ActivityQuestoesChecklistBinding activityQuestoesChecklistBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private ChecklistViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);

        activityQuestoesChecklistBinding = (ActivityQuestoesChecklistBinding) activityBinding;
        activityQuestoesChecklistBinding.setLifecycleOwner(this);
        activityQuestoesChecklistBinding.setListener(this);
        activityQuestoesChecklistBinding.setViewmodel(viewModel);

        activityQuestoesChecklistBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
            viewModel.obterQuestoes(item);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_questoes_checklist;
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

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }



    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.fab_nao_aplicavel})
    public void fab_nao_aplicavel_OnClickListener(View view) {

//        Intent intent = new Intent(this, DadosClienteActivity.class);
//        startActivity(intent);
    }

    @Override
    public void OnPerguntaClick(Questao questao) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, questao.registo.uid, questao.registo.tipo, questao.id);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnObservacaoClick(Questao questao) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, questao.registo.uid, questao.registo.tipo, questao.id);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnUtClick(Questao questao, int numeroUt) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, questao.registo.uid, questao.registo.tipo, questao.id, numeroUt);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnGaleriaClick() {
        //TODO: abrir galeria
    }

    @Override
    public void OnRegistarFoto() {
        //TODO: registar foto
    }
}
