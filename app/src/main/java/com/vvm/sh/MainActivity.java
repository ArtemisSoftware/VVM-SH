package com.vvm.sh;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vvm.sh.databinding.ActivityMainBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.ui.agenda.DialogoCalendario;
import com.vvm.sh.ui.agenda.DialogoOpcoesTrabalhoFragment;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.contaUtilizador.OpcoesAvancadasActivity;
import com.vvm.sh.ui.autenticacao.PerfilActivity;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.ui.transferencias.DownloadTrabalhoActivity;
import com.vvm.sh.ui.transferencias.UploadTrabalhoActivity;
import com.vvm.sh.ui.tarefa.TarefaActivity;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends BaseDaggerActivity
        implements OnAgendaListener, DatePickerDialog.OnDateSetListener,
        DialogoOpcoesTrabalhoFragment.DialogoListener/*OnItemListener, OnItemLongListener,
                   DialogoOpcoesTrabalhoFragment.DialogoListener, DialogoOpcoesTarefaFragment.DialogoListener */{




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


        subscreverObservadores();


        //TODO: verificar se deve chamar a DownloadTrabalhoActivity ou carregar os dados da bd

        //viewModel.obterTrabalho("12724");



        //Intent intent = new Intent(this, AutenticacaoActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //Intent intent = new Intent(this, DownloadTrabalhoActivity.class);
        Intent intent = new Intent(this, TiposActivity.class);
        //startActivity(intent);

        activityMainBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
        //viewModel.obterMarcacoes(Preferencias.obterIdUtilizador(this), DatasUtil.obterDataAtual());

        //TODO: data para teste
        viewModel.obterMarcacoes(Preferencias.obterIdUtilizador(this), DatasUtil.converterData(2020, 6, 23));

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

        viewModel.observarDatas().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogoDatas((List<Date>) recurso.dados);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });
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


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        activityMainBinding.txtData.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
        viewModel.obterMarcacoes(Preferencias.obterIdUtilizador(this), DatasUtil.converterData(year, monthOfYear, dayOfMonth));
    }


    @OnClick(R.id.crl_btn_calendario)
    public void crl_btn_calendario_OnClickListener(View view) {
        viewModel.obterDatas(Preferencias.obterIdUtilizador(this));
    }




    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que inicia o dialogo das datas
     * @param datas uma lista de datas
     */
    private void dialogoDatas(List<Date> datas) {

        DialogoCalendario dialogo = new DialogoCalendario(this, datas);
        //dialogo.realcarDias(datas);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }

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
//        Intent intent = new Intent(this, DownloadTrabalhoActivity.class);
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




    @Override
    public void recarregarTrabalho() {

    }

    @Override
    public void reUploadDados() {
        Intent intent = new Intent(this, UploadTrabalhoActivity.class);
        intent.putExtra(getString(R.string.argumento_data), DatasUtil.converterDataLong(activityMainBinding.txtData.getText().toString(), DatasUtil.FORMATO_DD_MMMM_YYYY));
        startActivity(intent);
    }

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

        Intent intent = null;

        switch (item.getItemId()){

            case R.id.item_perfil:

                intent = new Intent(this, PerfilActivity.class);
                startActivity(intent);
                break;

            case R.id.item_definicoes:

                intent = new Intent(this, DefinicoesActivity.class);
                break;


            case R.id.item_opcoes_trabalho:


                DialogoOpcoesTrabalhoFragment dialogo = new DialogoOpcoesTrabalhoFragment();
                dialogo.show(getSupportFragmentManager(), "example dialog");
                break;

            case R.id.item_opcoes_avancadas:

                intent = new Intent(this, OpcoesAvancadasActivity.class);
                break;
//
//            case R.id.item_terminar_sessao:
//
//                terminarSessao();
//                break;
//
//
//            case R.id.item_atualizar_app:
//
//                intent = new Intent(this, AtualizacaoAppActivity.class);
//                startActivity(intent);
//                break;


            case R.id.item_download_dados:

                intent = new Intent(this, DownloadTrabalhoActivity.class);
                break;


            case R.id.item_upload_dados:

                intent = new Intent(this, UploadTrabalhoActivity.class);
                break;


            default:
                break;
        }


        if(intent != null){
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
