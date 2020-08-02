package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityFormacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.AcaoFormacaoActivity;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class FormacaoActivity extends BaseDaggerActivity
        implements OnFormacaoListener {


    private ActivityFormacaoBinding activityFormacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private FormacaoViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(FormacaoViewModel.class);

        activityFormacaoBinding = (ActivityFormacaoBinding) activityBinding;
        activityFormacaoBinding.setLifecycleOwner(this);
        activityFormacaoBinding.setListener(this);
        activityFormacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            viewModel.obterFormacao(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_formacao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnFormandoClick(Formando formando) {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            Intent intent = new Intent(this, FormandoActivity.class);

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Bundle bundleFormacao = new Bundle();
            bundleFormacao.putInt(getString(R.string.argumento_id_atividade), idAtividade);

            if(formando != null){
                bundleFormacao.putInt(getString(R.string.argumento_id_formando), formando.resultado.id);
            }

            intent.putExtras(bundleFormacao);
            startActivity(intent);

        }
        else{
            finish();
        }
    }

    @Override
    public void OnSelecionadoCheck(Formando formando, boolean selecionado) {

        formando.resultado.selecionado = selecionado;
        viewModel.gravar(Preferencias.obterIdTarefa(this), formando.resultado);
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.fab_adicionar_formando)
    public void fab_adicionar_formando_OnClickListener(View view) {
        OnFormandoClick(null);
    }


    @OnClick(R.id.fab_adicionar_acao_formacao)
    public void fab_adicionar_acao_formacao_OnClickListener(View view) {

        Intent intent = new Intent(this, AcaoFormacaoActivity.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Bundle bundleFormacao = new Bundle();
            bundleFormacao.putInt(getString(R.string.argumento_id_atividade), idAtividade);

            intent.putExtras(bundleFormacao);
            startActivity(intent);
        }
        else{
            finish();
        }

    }



//
//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
//    private FormandoRecyclerAdapter formandoRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_formacao);
//
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        iniciarAtividade();
//        obterRegistos();
//    }
//
//
//    //------------------------
//    //Metodos locais
//    //------------------------
//
//
//    /**
//     * Metodo que permite iniciar a atividade
//     */
//    private void iniciarAtividade(){
//
//        formandoRecyclerAdapter = new FormandoRecyclerAdapter(this, this);
//        rcl_registos.setAdapter(formandoRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//
//    private void obterRegistos(){
//
//        //--TESTE (apagar quando houver dados)
//
//        List<Item> t1 = new ArrayList<>();
//
//        t1.add(new FormandoResultado(1, "FormandoResultado numero 1", "3564365", "12345235", 1, 1, 1));
//        t1.add(new FormandoResultado(2, "FormandoResultado numenro 2", "87647836", "674543", 1, 0, 0));
//
//        formandoRecyclerAdapter.fixarRegistos(t1);
//
//        //TODO: chamar metodo do viewmodel
//    }
//
//    /**
//     * Metodo que permite subscrever observadores
//     */
//    private void subscreverObservadores(){
//
//
//        //TODO: subscrever observadores do viewmodel
//
//    }
//
//
//
//
//
//
//    /**
//     * Metodo que permite iniciar o formulario de formando
//     */
//    public void initFormando(){
//
//        Intent intent = new Intent(this, FormandoActivity.class);
//        startActivity(intent);
//
//        /*
//        Intent intent = new Intent(this, IndiceFormando_Activity.class);
//
//        Bundle bundle = new Bundle();
//        if(adaptador.obterRegistoSelecionado() != null) {
//
//            bundle.putString(BundleIF.ID_RELATORIO, adaptador.obterRegistoSelecionado().obterId());
//        }
//        else{
//            bundle.putString(BundleIF.ID_RELATORIO, AppIF.SEM_DADOS);
//        }
//
//        bundle.putString(BundleIF.ID_ATIVIDADE_PENDENTE, idAtividadePendente);
//        intent.putExtras(bundle);
//        startActivityForResult(intent, CodigoAtividadeIF.REGISTO_FORMANDO);
//        */
//    }
//
//

//
//
//

//
//
//    @Override
//    public void onItemClick(int position) {
//        initFormando();
//    }
//
//    @Override
//    public void onItemChecked(int posicao, boolean selecao) {
//
//    }

}
