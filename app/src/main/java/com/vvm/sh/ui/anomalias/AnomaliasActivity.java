package com.vvm.sh.ui.anomalias;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.anomalias.adaptadores.AnomaliaRecyclerAdapter;
import com.vvm.sh.util.adaptadores.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AnomaliasActivity extends BaseActivity {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    private AnomaliaRecyclerAdapter anomaliaRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anomalias);

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

//        anomaliaRecyclerAdapter = new AnomaliaRecyclerAdapter();
//        rcl_registos.setAdapter(anomaliaRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

//        List<Item> t1 = new ArrayList<>();
//
//        t1.add(new Anomalia(1, "Anomalia numero 1", "2020-02-20","observacao norte", "12345235", "TipoResultado 1"));
//        t1.add(new Anomalia(2, "Anomalia numero 1", "2020-02-20","observacao norte", "12345235", "TipoResultado 1"));
//
//        anomaliaRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }

}
