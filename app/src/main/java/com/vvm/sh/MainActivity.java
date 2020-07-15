package com.vvm.sh;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.agenda.DialogoOpcoesTarefaFragment;
import com.vvm.sh.ui.agenda.DialogoOpcoesTrabalhoFragment;
import com.vvm.sh.ui.agenda.Tarefa;
import com.vvm.sh.ui.agenda.TarefaActivity;
import com.vvm.sh.ui.agenda.TarefaRecyclerAdapter;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.contaUtilizador.OpcoesAvancadasActivity;
import com.vvm.sh.ui.contaUtilizador.PerfilActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.interfaces.OnItemLongListener;
import com.vvm.sh.util.metodos.Datas;
import com.vvm.sh.util.metodos.Preferencias;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements OnItemListener, OnItemLongListener,
                   DatePickerDialog.OnDateSetListener,  DialogoOpcoesTrabalhoFragment.DialogoListener, DialogoOpcoesTarefaFragment.DialogoListener {


    @BindView(R.id.txt_data)
    TextView txt_data;

    @BindView(R.id.txt_estado)
    TextView txt_estado;

    @BindView(R.id.fab_menu_agenda)
    FloatingActionMenu fab_menu_agenda;


    @BindView(R.id.rcl_tarefas)
    RecyclerView rcl_tarefas;


    private String data;
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


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        txt_data.setText(Datas.obterDataAtual(Datas.FORMATO_DD_MMMM_YYYY, Datas.LOCAL_PORTUGAL));
        data = Datas.obterDataAtual(Datas.FORMATO_YYYY_MM_DD);

        tarefaRecyclerAdapter = new TarefaRecyclerAdapter(this, this);
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


    /**
     * Metodo que permite terminar a sessao
     */
    private void terminarSessao(){

        Preferencias.eliminarDadosUtilizador(this);

        //TODO: apagar dados da bd - chamar viewmodel
        finish();
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


    @Override
    public void onItemLongClick(int posicao) {

        DialogoOpcoesTarefaFragment dialogo = new DialogoOpcoesTarefaFragment();
        dialogo.show(getSupportFragmentManager(), "dialogo_tarefa");
    }


    @OnClick(R.id.fab_calendario)
    public void fab_calendario_OnClickListener(View view) {

        fab_menu_agenda.close(true);

        DatePickerDialog dialogo = Datas.obterCalendarioAgenda(this);
        dialogo.show(getSupportFragmentManager(), "Datepickerdialog");
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        txt_data.setText(Datas.converterData(year, monthOfYear, dayOfMonth, Datas.FORMATO_DD_MMMM_YYYY, Datas.LOCAL_PORTUGAL));

        data = Datas.converterData(year, monthOfYear, dayOfMonth, Datas.FORMATO_YYYY_MM_DD);


        //Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();

        //--TESTE (apagar quando houver dados)
        List<Item> t1 = new ArrayList<>();
        t1.add(new Tarefa(6, "Tarefa numero 5", "SH"));
        t1.add(new Tarefa(7, "Tarefa numero 65", "SA"));
        t1.add(new Tarefa(9, "Tarefa numero 65 Lda, filhos e cunhados", "SH"));

        tarefaRecyclerAdapter.renovarRegistos(t1);

        //TODO: chamar metodo do viewmodel

    }



    @Override
    public void recarregarTrabalho() {

    }

    @Override
    public void reUploadDados() {

    }


    @Override
    public void recarregarTarefa() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

            case R.id.item_perfil:

                intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_definicoes:

                intent = new Intent(this, DefinicoesActivity.class);
                startActivity(intent);
                return true;


            case R.id.item_opcoes_trabalho:


                DialogoOpcoesTrabalhoFragment dialogo = new DialogoOpcoesTrabalhoFragment();
                dialogo.show(getSupportFragmentManager(), "example dialog");
                return true;

            case R.id.item_opcoes_avancadas:

                intent = new Intent(this, OpcoesAvancadasActivity.class);
                startActivity(intent);
                return true;

            case R.id.item_terminar_sessao:

                terminarSessao();
                return true;


            case R.id.item_atualizar_app:

                intent = new Intent(this, AtualizacaoAppActivity.class);
                startActivity(intent);
                return true;


            case R.id.item_upload_dados:

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
