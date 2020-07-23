package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AtividadesPendentesActivity extends BaseDaggerActivity
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
//    /**
//     * Metodo que permite iniciar a atividade
//     */
//    private void iniciarAtividade(){
//
//        atividadePendenteRecyclerAdapter = new AtividadePendenteRecyclerAdapter(this);
//        rcl_registos.setAdapter(atividadePendenteRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//
//    private void obterRegistos(){
//
//        //--TESTE (apagar quando houver dados)
//
//        List<Item> t1 = new ArrayList<>();
//        t1.add(new AtividadePendente(1, "Atividade pendente numero 1", "29-10-2020", "1 e 2 anuidade", 1, "Iluminação"));
//        t1.add(new AtividadePendente(2, "Atividade pendente numero 2", "30-10-2020", "1 e 2 anuidade", -1, null));
//
//        atividadePendenteRecyclerAdapter.fixarRegistos(t1);
//
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
//
//
//    //---------------------
//    //Eventos
//    //---------------------
//
//    @Override
//    public void onItemClick(int position) {
//
//        DialogoAtividadePendente dialogo = new DialogoAtividadePendente();
//        dialogo.show(getSupportFragmentManager(), "example dialog");
//
//        //Intent intent = new Intent(this, FormacaoActivity.class);
//        //Intent intent = new Intent(this, TarefaActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        //startActivity(intent);
//    }
//
//
//    @Override
//    public void concluirAtividadeExecutada() {
//
//        DialogoAtividadePendenteExecutada dialogo = new DialogoAtividadePendenteExecutada();
//        dialogo.show(getSupportFragmentManager(), "example dialog");
//    }
//
//    @Override
//    public void concluirAtividadeNaoExecutada() {
//
//        DialogoAtividadePendenteNaoExecutada dialogo = new DialogoAtividadePendenteNaoExecutada();
//        dialogo.show(getSupportFragmentManager(), "example dialog");
//    }
//
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
