package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaListener;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class OcorrenciasActivity extends BaseDaggerActivity
        implements OnOcorrenciaListener {



    private ActivityOcorrenciasBinding activityOcorrenciasBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;





    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasBinding = (ActivityOcorrenciasBinding) activityBinding;
        activityOcorrenciasBinding.setLifecycleOwner(this);
        activityOcorrenciasBinding.setListener(this);
        activityOcorrenciasBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterOcorrencias(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencias;
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


    @OnClick(R.id.fab_adicionar)
    public void fab_adicionar_OnClickListener(View view) {
        Intent intent = new Intent(this, OcorrenciasRegistoActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnOcorrenciaClick(Ocorrencia ocorrencia) {

        Intent intent = new Intent(this, OcorrenciasHistoricoActivity.class);
        intent.putExtra(getString(R.string.argumento_descricao), ocorrencia.descricaoOcorrencia);
        intent.putExtra(getString(R.string.argumento_id), ocorrencia.id);
        startActivity(intent);
    }


//
//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
////    @BindView(R.id.txt_historico)
////    TextView txt_historico;
////
////    @BindView(R.id.txt_marca)
////    TextView txt_marca;
//
//    @BindView(R.id.txt_estado)
//    TextView txt_estado;
//
//
////    @BindView(R.id.crs_historico)
////    CardView crs_historico;
//
//    private OcorrenciaRecyclerAdapter ocorrenciaRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ocorrencias);
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
////        crs_historico.setVisibility(View.GONE);
//        //ocorrenciaRecyclerAdapter = new OcorrenciaRecyclerAdapter(this);
//        rcl_registos.setAdapter(ocorrenciaRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//    }

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
////    @OnClick(R.id.crl_img_regressar)
//    public void crl_img_regressar_OnClickListener(View view) {
//        obterRegistos();
//    }
//

//
//
//    @Override
//    public void onItemClick(int position) {
//
//        try {
//
//            //--TESTE (apagar quando houver dados)
//
////            Ocorrencia registo = (Ocorrencia) ocorrenciaRecyclerAdapter.obterRegisto(position);
////
////            crs_historico.setVisibility(View.VISIBLE);
////            txt_historico.setText(registo.obterDescricao());
////            txt_marca.setText(registo.obterMarca());
////            txt_estado.setText(registo.obterSituacao());
////
////            List<Item> t1 = new ArrayList<>();
////            t1.add(new Ocorrencia("2019-02-04", "Resolvido", "uma observação grande", "departamento interno"));
////            t1.add(new Ocorrencia("2019-02-02", "Resolvido por enquanto", "uma observação pequena", "departamento externo"));
////            ocorrenciaRecyclerAdapter.renovarRegistos(t1);
//
//            //TODO: chamar metodo do viewmodel
//
//        }
//        catch(IndexOutOfBoundsException e){}
//
//    }
}
