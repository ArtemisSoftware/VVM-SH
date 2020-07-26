package com.vvm.sh.ui.atividadesPendentes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AtividadesPendentesActivity extends BaseDaggerActivity

        implements OnAtividadePendenteListener
        /*implements OnItemListener,
        DialogoAtividadePendente.DialogoListener, DialogoAtividadePendenteExecutada.DialogListener, DialogoAtividadePendenteNaoExecutada.DialogoListener*/ {


    private ActivityAtividadesPendentesBinding activityAtividadesPendentesBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AtividadesPendentesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);

        activityAtividadesPendentesBinding = (ActivityAtividadesPendentesBinding) activityBinding;
        activityAtividadesPendentesBinding.setLifecycleOwner(this);
        activityAtividadesPendentesBinding.setListener(this);
        activityAtividadesPendentesBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterAtividades(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atividades_pendentes;
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
    public void OnAtividadeClick(AtividadePendente atividade) {

        DialogoAtividadePendente dialogo = DialogoAtividadePendente.newInstance(atividade.id, atividade.existeRelatorio());
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnConcluirAtividadeExecutada(int idAtividade) {



    }

    @Override
    public void OnConcluirAtividadeNaoExecutada(int idAtividade) {

        DialogoAtividadePendenteNaoExecutada dialogo = DialogoAtividadePendenteNaoExecutada.newInstance(idAtividade);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnIniciarRelatorio(AtividadePendente atividade) {

    }

//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
//
//    private AtividadePendenteRecyclerAdapter atividadePendenteRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_atividades_pendentes);
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
//
//    //------------------------
//    //Metodos locais
//    //------------------------
//


//


//
//    @Override
//    public void concluirAtividadeExecutada() {
//
//        DialogoAtividadePendenteExecutada dialogo = new DialogoAtividadePendenteExecutada();
//        dialogo.show(getSupportFragmentManager(), "example dialog");
//    }

//    @Override
//    public void iniciarRelatorio() {
//
//        Intent intent = new Intent(this, FormacaoActivity.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public void gravarAtividade(String minutos, String dataExecucao) {
//
//    }
//
//    @Override
//    public void gravarAtividadeNaoExecutada(String idAnomalia, String observacao) {
//
//    }
}
