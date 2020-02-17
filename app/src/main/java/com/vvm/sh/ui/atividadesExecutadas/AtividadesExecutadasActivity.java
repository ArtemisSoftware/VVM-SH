package com.vvm.sh.ui.atividadesExecutadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.ui.agenda.adaptadores.TarefaRecyclerAdapter;
import com.vvm.sh.util.adaptadores.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AtividadesExecutadasActivity extends BaseActivity {


    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;


    private AtividadeExecutadaRecyclerAdapter atividadeExecutadaRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_executadas);


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

        atividadeExecutadaRecyclerAdapter = new AtividadeExecutadaRecyclerAdapter();
        rcl_registos.setAdapter(atividadeExecutadaRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
        rcl_registos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();
        t1.add(new AtividadeExecutada(1, "Atividade numero 1", "29-10-2020"));
        t1.add(new AtividadeExecutada(2, "Atividade numero 2", "30-10-2020"));

        atividadeExecutadaRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }
}
