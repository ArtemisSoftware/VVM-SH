package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AtividadesPendentesActivity extends BaseActivity
        implements OnItemListener,
        DialogoAtividadePendente.DialogoListener, DialogoAtividadePendenteExecutada.DialogListener, DialogoAtividadePendenteNaoExecutada.DialogoListener {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;


    private AtividadePendenteRecyclerAdapter atividadePendenteRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_pendentes);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciarAtividade();
        obterRegistos();
    }


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        atividadePendenteRecyclerAdapter = new AtividadePendenteRecyclerAdapter(this);
        rcl_registos.setAdapter(atividadePendenteRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();
        t1.add(new AtividadePendente(1, "Atividade pendente numero 1", "29-10-2020", "1 e 2 anuidade", 1, "Iluminação"));
        t1.add(new AtividadePendente(2, "Atividade pendente numero 2", "30-10-2020", "1 e 2 anuidade", -1, null));

        atividadePendenteRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


    //---------------------
    //Eventos
    //---------------------

    @Override
    public void onItemClick(int position) {

        DialogoAtividadePendente dialogo = new DialogoAtividadePendente();
        dialogo.show(getSupportFragmentManager(), "example dialog");

        //Intent intent = new Intent(this, FormacaoActivity.class);
        //Intent intent = new Intent(this, TarefaActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //startActivity(intent);
    }


    @Override
    public void concluirAtividadeExecutada() {

        DialogoAtividadePendenteExecutada dialogo = new DialogoAtividadePendenteExecutada();
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void concluirAtividadeNaoExecutada() {

        DialogoAtividadePendenteNaoExecutada dialogo = new DialogoAtividadePendenteNaoExecutada();
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void iniciarRelatorio() {

        Intent intent = new Intent(this, FormacaoActivity.class);
        startActivity(intent);
    }

    @Override
    public void gravarAtividade(String minutos, String dataExecucao) {

    }

    @Override
    public void gravarAtividadeNaoExecutada(String idAnomalia, String observacao) {

    }
}
