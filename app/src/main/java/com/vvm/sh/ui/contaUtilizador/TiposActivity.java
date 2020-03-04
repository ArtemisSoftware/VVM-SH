package com.vvm.sh.ui.contaUtilizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciaRecyclerAdapter;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TiposActivity extends BaseActivity {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;


    private ColecaoRecyclerAdapter colecaoRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos);

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

        colecaoRecyclerAdapter = new ColecaoRecyclerAdapter();
        rcl_registos.setAdapter(colecaoRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));

        registerForContextMenu(rcl_registos);
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();
        t1.add(new Colecao("Anomalias", 2, "2019-04-12"));
        t1.add(new Colecao("Cross-Selling Dimensao", 4, "2019-06-22"));

        colecaoRecyclerAdapter.fixarRegistos(t1);

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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String titulo = colecaoRecyclerAdapter.obterRegisto(info.position).obterDescricao();
        menu.setHeaderTitle(titulo);

        menu.add(Menu.NONE, 1, Menu.NONE, getString(R.string.recarregar_tipo));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){

            case 1:
                break;

            default:
                break;
        }

        return true;
    }

}
