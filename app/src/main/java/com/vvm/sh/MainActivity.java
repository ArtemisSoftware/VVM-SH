package com.vvm.sh;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vvm.sh.databinding.ActivityMainBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.AgendaViewModel;
import com.vvm.sh.ui.agenda.DialogoCalendario;
import com.vvm.sh.ui.agenda.DialogoOpcoesTrabalhoFragment;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.ui.autenticacao.AutenticacaoActivity;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.contaUtilizador.OpcoesAvancadasActivity;
import com.vvm.sh.ui.autenticacao.PerfilActivity;
import com.vvm.sh.ui.transferencias.DownloadTrabalhoActivity;
import com.vvm.sh.ui.transferencias.UploadTrabalhoActivity;
import com.vvm.sh.ui.tarefa.TarefaActivity;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.MensagensUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class MainActivity extends BaseDaggerActivity
        implements OnAgendaListener, DatePickerDialog.OnDateSetListener,
        DialogoOpcoesTrabalhoFragment.DialogoListener{




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

        //--iniciarSessao();

        //Intent intent = new Intent(this, AutenticacaoActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //Intent intent = new Intent(this, DownloadTrabalhoActivity.class);
        //startActivity(intent);


        //TODO: data para teste
        activityMainBinding.txtData.setText(DatasUtil.converterData(2020, 6, 23, DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
        viewModel.obterMarcacoes(PreferenciasUtil.obterIdUtilizador(this), DatasUtil.converterData(2020, 6, 23));

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

        PreferenciasUtil.fixarTarefa(this, marcacao.tarefa.idTarefa);

        Intent intent = new Intent(this, TarefaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(Marcacao marcacao) {

        OnDialogoListener listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                Intent intent = new Intent(MainActivity.this, DownloadTrabalhoActivity.class);
                intent.putExtra(getString(R.string.argumento_tarefa), marcacao.tarefa);
                intent.putExtra(getString(R.string.argumento_recarregar_tarefa), true);
                startActivity(intent);
            }
        };

        dialogo.alerta(getString(R.string.recarregar_tarefa), getString(R.string.perder_dados_tarefa), listener);

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        activityMainBinding.txtData.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
        viewModel.obterMarcacoes(PreferenciasUtil.obterIdUtilizador(this), DatasUtil.converterData(year, monthOfYear, dayOfMonth));
    }


    @OnClick(R.id.crl_btn_calendario)
    public void crl_btn_calendario_OnClickListener(View view) {
        viewModel.obterDatas(PreferenciasUtil.obterIdUtilizador(this));
    }

    @OnClick(R.id.btn_download_on_demand)
    public void btn_download_on_demand_OnClickListener(View view) {

        Intent intent = new Intent(this, DownloadTrabalhoActivity.class);
        startActivity(intent);
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
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }


    /**
     * Metodo que permite validar a sessao
     * @return true caso a sessao seja válida ou false caso contrario
     */
    private boolean validarSessao() {

        OnDialogoListener listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                terminarSessao();
            }
        };

        if(DatasUtil.validarSessao(PreferenciasUtil.obterDataValidadeAutenticacao(this)) == false){
            dialogo.alerta(Sintaxe.Palavras.SESSAO, Sintaxe.Alertas.SESSAO_EXPIRADA, listener);
            return false;
        }

        return true;
    }


    /**
     * Metodo que permite iniciar a sessao
     */
    private void iniciarSessao(){

        if(validarSessao() == false){
            terminarSessao();
        }
        else{
            activityMainBinding.txtData.setText(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MMMM_YYYY, DatasUtil.LOCAL_PORTUGAL));
            viewModel.obterMarcacoes(PreferenciasUtil.obterIdUtilizador(this), DatasUtil.obterDataAtual());
        }
    }


    /**
     * Metodo que permite terminar a sessao
     */
    private void terminarSessao(){

        PreferenciasUtil.eliminarDadosUtilizador(this);

        Intent intent = new Intent(this, AutenticacaoActivity.class);
        startActivity(intent);
        finish();
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



    @Override
    public void recarregarTrabalho() {
        Intent intent = new Intent(this, DownloadTrabalhoActivity.class);
        intent.putExtra(getString(R.string.argumento_data), DatasUtil.converterDataLong(activityMainBinding.txtData.getText().toString(), DatasUtil.FORMATO_DD_MMMM_YYYY));
        startActivity(intent);
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


            case R.id.item_terminar_sessao:

                terminarSessao();
                break;


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

    @Override
    protected void onResume() {
        super.onResume();

        validarSessao();
    }



}
