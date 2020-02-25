package com.vvm.sh.ui.agenda;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.agenda.adaptadores.OpcaoClienteRecyclerAdapter;
import com.vvm.sh.ui.anomalias.AnomaliasActivity;
import com.vvm.sh.ui.anomalias.DialogoAnomalia;
import com.vvm.sh.ui.anomalias.RegistoAnomaliasActivity;
import com.vvm.sh.ui.atividadesExecutadas.AtividadesExecutadasActivity;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesActivity;
import com.vvm.sh.ui.cliente.InformacaoActivity;
import com.vvm.sh.ui.cliente.SinistralidadeActivity;
import com.vvm.sh.ui.cliente.extintores.ExtintoresActivity;
import com.vvm.sh.ui.contaCorrente.ContaCorrenteActivity;
import com.vvm.sh.ui.crossSelling.CrossSellingActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasActivity;
import com.vvm.sh.util.interfaces.OnItemListener;

import butterknife.BindView;
import butterknife.OnClick;

public class TarefaActivity extends BaseActivity implements OnItemListener, DialogoEmail.DialogEmailListener {



    @BindView(R.id.rcl_opcoes_cliente)
    RecyclerView rcl_opcoes_cliente;

    private OpcaoClienteRecyclerAdapter opcaoClienteRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.z_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        iniciarAtividade();
        subscreverObservadores();
        obterRegistos();

    }






    @Override
    public void gravarEmail(String email, int estado) {
        //textViewUsername.setText(username);
        //textViewPassword.setText(password);
    }

    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        opcaoClienteRecyclerAdapter = new OpcaoClienteRecyclerAdapter(this);
        rcl_opcoes_cliente.setAdapter(opcaoClienteRecyclerAdapter);
        rcl_opcoes_cliente.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }



    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        opcaoClienteRecyclerAdapter.gerarOpcoes();

        //TODO: chamar metodo do viewmodel
    }



    @OnClick(R.id.crd_atividades_pendentes)
    public void crd_atividades_pendentes_OnClickListener(View view) {
        Intent intent = new Intent(this, AtividadesPendentesActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }

    @OnClick(R.id.crd_anomalias)
    public void crd_anomalias_OnClickListener(View view) {
        Intent intent = new Intent(this, AnomaliasActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }

    @OnClick(R.id.crd_atividades_executadas)
    public void crd_atividades_executadas_OnClickListener(View view) {
        Intent intent = new Intent(this, AtividadesExecutadasActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }


    @OnClick(R.id.crd_conta_corrente)
    public void crd_conta_corrente_OnClickListener(View view) {
        Intent intent = new Intent(this, ContaCorrenteActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }


    @OnClick(R.id.crd_ocorrencias)
    public void crd_ocorrencias_OnClickListener(View view) {
        Intent intent = new Intent(this, OcorrenciasActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }

    //---------------------
    //Eventos
    //---------------------


    @Override
    public void onItemClick(int position) {

        Intent intent = null;

        switch (opcaoClienteRecyclerAdapter.obterRegisto(position).obterId()){

            case OpcaoClienteRecyclerAdapter.OPCAO_INFORMACAO:

                intent = new Intent(this, InformacaoActivity.class);
                break;

            case OpcaoClienteRecyclerAdapter.OPCAO_CROSS_SELLING:

                intent = new Intent(this, CrossSellingActivity.class);
                break;

            case OpcaoClienteRecyclerAdapter.OPCAO_SINISTRALIDADE:

                intent = new Intent(this, SinistralidadeActivity.class);
                break;

            case OpcaoClienteRecyclerAdapter.OPCAO_EXTINTORES:

                intent = new Intent(this, ExtintoresActivity.class);
                break;

            case OpcaoClienteRecyclerAdapter.OPCAO_EMAIL:

                DialogoEmail dialogo = new DialogoEmail();
                dialogo.show(getSupportFragmentManager(), "example dialog");
                break;

            case OpcaoClienteRecyclerAdapter.OPCAO_ANOMALIA:

                intent = new Intent(this, RegistoAnomaliasActivity.class);
                break;

            default:
                break;
        }

        if(intent != null) {
            //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
            startActivity(intent);
        }
    }

    /*https://www.11zon.com/android/android_cardview.php*/
}
