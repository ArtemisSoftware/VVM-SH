package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FormacaoActivity extends BaseActivity implements OnItemListener, OnCheckBoxItemListener {


    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    private FormandoRecyclerAdapter formandoRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao);

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

        formandoRecyclerAdapter = new FormandoRecyclerAdapter(this, this);
        rcl_registos.setAdapter(formandoRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();

        t1.add(new Formando(1, "Formando numero 1", "3564365", "12345235", 1, 1, 1));
        t1.add(new Formando(2, "Formando numenro 2", "87647836", "674543", 1, 0, 0));

        formandoRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }






    /**
     * Metodo que permite iniciar o formulario de formando
     */
    public void initFormando(){

        Intent intent = new Intent(this, FormandoActivity.class);
        startActivity(intent);

        /*
        Intent intent = new Intent(this, IndiceFormando_Activity.class);

        Bundle bundle = new Bundle();
        if(adaptador.obterRegistoSelecionado() != null) {

            bundle.putString(BundleIF.ID_RELATORIO, adaptador.obterRegistoSelecionado().obterId());
        }
        else{
            bundle.putString(BundleIF.ID_RELATORIO, AppIF.SEM_DADOS);
        }

        bundle.putString(BundleIF.ID_ATIVIDADE_PENDENTE, idAtividadePendente);
        intent.putExtras(bundle);
        startActivityForResult(intent, CodigoAtividadeIF.REGISTO_FORMANDO);
        */
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.fab_adicionar_formando)
    public void fab_adicionar_formando_OnClickListener(View view) {
        initFormando();
    }



    @OnClick(R.id.fab_adicionar_acao_formacao)
    public void fab_adicionar_acao_formacao_OnClickListener(View view) {

        Intent intent = new Intent(this, AcaoFormacaoActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivityForResult(intent, 2);
    }


    @Override
    public void onItemClick(int position) {
        initFormando();
    }

    @Override
    public void onItemChecked(int posicao, boolean selecao) {

    }

}
