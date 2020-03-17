package com.vvm.sh;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.vvm.sh.apresentacao.ApresentacaoActivity;
import com.vvm.sh.apresentacao.modelos.Apresentacao;
import com.vvm.sh.autenticacao.AutenticacaoActivity;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.ui.agenda.TarefaActivity;
import com.vvm.sh.ui.agenda.TarefaRecyclerAdapter;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.Datas;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, OnItemListener {


    @BindView(R.id.fab_menu_agenda)
    FloatingActionMenu fab_menu_agenda;


    @BindView(R.id.rcl_tarefas)
    RecyclerView rcl_tarefas;

    private TarefaRecyclerAdapter tarefaRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        //Intent intent = new Intent(this, ApresentacaoActivity.class);
        //startActivity(intent);

        iniciarAtividade();
        subscreverObservadores();
        obterRegistos();

        //Intent intent = new Intent(this, AutenticacaoActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_autenticacao, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

            case R.id.item_app:


                return true;


            case R.id.item_atualizar_app:


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        tarefaRecyclerAdapter = new TarefaRecyclerAdapter(this);
        rcl_tarefas.setAdapter(tarefaRecyclerAdapter);
        rcl_tarefas.setLayoutManager(new LinearLayoutManager(this));

    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)
        List<Item> t1 = new ArrayList<>();
        t1.add(new Tarefa(1, "Tarefa numero 1", "SH"));
        t1.add(new Tarefa(2, "Tarefa numero 2", "SA"));

        tarefaRecyclerAdapter.fixarRegistos(t1);

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
        //Intent intent = new Intent(this, ApresentacaoActivity.class);
        Intent intent = new Intent(this, TarefaActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);

    }


    @OnClick(R.id.fab_calendario)
    public void fab_calendario_OnClickListener(View view) {

        fab_menu_agenda.close(true);


        DatePickerDialog dpd = Datas.obterCalendarioAgenda(this);

        dpd.show(getSupportFragmentManager(), "Datepickerdialog");

    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String data = Datas.converterData(year, monthOfYear, dayOfMonth, Datas.FORMATO_YYYY_MM_DD);

        //Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();

        //--TESTE (apagar quando houver dados)
        List<Item> t1 = new ArrayList<>();
        t1.add(new Tarefa(6, "Tarefa numero 5", "SH"));
        t1.add(new Tarefa(7, "Tarefa numero 65", "SA"));
        t1.add(new Tarefa(9, "Tarefa numero 65 Lda, filhos e cunhados", "SH"));

        tarefaRecyclerAdapter.renovarRegistos(t1);

        //TODO: chamar metodo do viewmodel

    }

}
