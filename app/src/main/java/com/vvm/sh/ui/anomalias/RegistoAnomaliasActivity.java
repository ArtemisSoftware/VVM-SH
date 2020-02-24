package com.vvm.sh.ui.anomalias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.util.adaptadores.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistoAnomaliasActivity extends AppCompatActivity {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;


    private AnomaliaRecyclerAdapter anomaliaRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_anomalias);

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

        anomaliaRecyclerAdapter = new AnomaliaRecyclerAdapter();
        rcl_registos.setAdapter(anomaliaRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();

        t1.add(new Anomalia(1, "Anomalia numero 1", "observacao norte"));
        t1.add(new Anomalia(2, "Anomalia numero 3", "observacao norte a sul e dos lados"));

        anomaliaRecyclerAdapter.fixarRegistos(t1);

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


    @OnClick(R.id.fab_adicionar)
    public void fab_adicionar_OnClickListener(View view) {
        //Intent intent = new Intent(this, TarefaActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //startActivity(intent);
    }

}
