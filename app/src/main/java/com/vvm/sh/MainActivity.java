package com.vvm.sh;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vvm.sh.databinding.ActivityMainBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.ui.upload.UploadActivity;
import com.vvm.sh.ui.tarefa.TarefaActivity;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseDaggerActivity
        implements OnAgendaListener/*OnItemListener, OnItemLongListener,
                   DatePickerDialog.OnDateSetListener,  DialogoOpcoesTrabalhoFragment.DialogoListener, DialogoOpcoesTarefaFragment.DialogoListener */{




    private ActivityMainBinding activityMainBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AgendaViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(AgendaViewModel.class);

        activityMainBinding = (ActivityMainBinding) activityBinding;
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.setViewmodel(viewModel);
        activityMainBinding.setListener(this);
        //activityTrabalhoBinding.setActivity(this);

        setSupportActionBar(activityMainBinding.toolbar);

        activityMainBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));

        subscreverObservadores();


        //TODO: verificar se deve chamar a TrabalhoActivity ou carregar os dados da bd

        //viewModel.obterTrabalho("12724");



        //Intent intent = new Intent(this, AutenticacaoActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //Intent intent = new Intent(this, TrabalhoActivity.class);
        Intent intent = new Intent(this, TiposActivity.class);
        //startActivity(intent);
        viewModel.obterMarcacoes("12724", "2020-07-21");


    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_main;
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
    public void onItemClick(Marcacao marcacao) {

        Preferencias.fixarTarefa(this, marcacao.tarefa.idTarefa);

        Intent intent = new Intent(this, TarefaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(Marcacao marcacao) {

    }



    //------------------------
    //Metodos locais
    //------------------------


//    /**
//     * Metodo que permite iniciar a atividade
//     */
//    private void iniciarAtividade(){
//
//
//        data = DatasUtil.obterDataAtual(DatasUtil.FORMATO_YYYY_MM_DD);
//
//    }




//
//    @BindView(R.id.txt_data)
//    TextView txt_data;
//
//    @BindView(R.id.txt_estado)
//    TextView txt_estado;
//
//    @BindView(R.id.fab_menu_agenda)
//    FloatingActionMenu fab_menu_agenda;
//
//
//    @BindView(R.id.rcl_tarefas)
//    RecyclerView rcl_tarefas;
//
//
//    private String data;
//    private MarcacaoRecyclerAdapter tarefaRecyclerAdapter;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(null);
//
//
//        //Intent intent = new Intent(this, ApresentacaoActivity.class);
//        //startActivity(intent);
//
//        iniciarAtividade();
//        subscreverObservadores();
//        obterRegistos();
//
//        //Intent intent = new Intent(this, AutenticacaoActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        Intent intent = new Intent(this, TrabalhoActivity.class);
//        startActivity(intent);
//    }
//
//
//    //------------------------
//    //Metodos locais
//    //------------------------

//    /**
//     * Metodo que permite terminar a sessao
//     */
//    private void terminarSessao(){
//
//        Preferencias.eliminarDadosUtilizador(this);
//
//        //TODO: apagar dados da bd - chamar viewmodel
//        finish();
//    }
//
//    //---------------------
//    //Eventos
//    //---------------------
//
//

//
//
//    @Override
//    public void onItemLongClick(int posicao) {
//
//        DialogoOpcoesTarefaFragment dialogo = new DialogoOpcoesTarefaFragment();
//        dialogo.show(getSupportFragmentManager(), "dialogo_tarefa");
//    }
//
//
//    @OnClick(R.id.fab_calendario)
//    public void fab_calendario_OnClickListener(View view) {
//
//        fab_menu_agenda.close(true);
//
//        DatePickerDialog dialogo = DatasUtil.obterCalendarioAgenda(this);
//        dialogo.show(getSupportFragmentManager(), "Datepickerdialog");
//    }
//
//
//
//    @Override
//    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//
//        txt_data.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
//
//        data = DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_YYYY_MM_DD);
//
//
//        //Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
//
//        //--TESTE (apagar quando houver dados)
//        List<Item> t1 = new ArrayList<>();
//        t1.add(new Tarefa(6, "Tarefa numero 5", "SH"));
//        t1.add(new Tarefa(7, "Tarefa numero 65", "SA"));
//        t1.add(new Tarefa(9, "Tarefa numero 65 Lda, filhos e cunhados", "SH"));
//
//        tarefaRecyclerAdapter.renovarRegistos(t1);
//
//        //TODO: chamar metodo do viewmodel
//
//    }
//
//
//
//    @Override
//    public void recarregarTrabalho() {
//
//    }
//
//    @Override
//    public void reUploadDados() {
//
//    }
//
//
//    @Override
//    public void recarregarTarefa() {
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

//            case R.id.item_perfil:
//
//                intent = new Intent(this, PerfilActivity.class);
//                startActivity(intent);
//                return true;
//
//            case R.id.item_definicoes:
//
//                intent = new Intent(this, DefinicoesActivity.class);
//                startActivity(intent);
//                return true;
//
//
//            case R.id.item_opcoes_trabalho:
//
//
//                DialogoOpcoesTrabalhoFragment dialogo = new DialogoOpcoesTrabalhoFragment();
//                dialogo.show(getSupportFragmentManager(), "example dialog");
//                return true;
//
//            case R.id.item_opcoes_avancadas:
//
//                intent = new Intent(this, OpcoesAvancadasActivity.class);
//                startActivity(intent);
//                return true;
//
//            case R.id.item_terminar_sessao:
//
//                terminarSessao();
//                return true;
//
//
//            case R.id.item_atualizar_app:
//
//                intent = new Intent(this, AtualizacaoAppActivity.class);
//                startActivity(intent);
//                return true;


            case R.id.item_upload_dados:

                intent = new Intent(this, UploadActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
