package com.vvm.sh.ui.ocorrencias;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBinding;
import com.vvm.sh.databinding.ActivityOcorrenciasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRecyclerAdapter;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OcorrenciasActivity extends BaseDaggerActivity /*implements OnItemListener*/ {



    private ActivityOcorrenciasBinding activityOcorrenciasBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;





    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasBinding = (ActivityOcorrenciasBinding) activityBinding;
        activityOcorrenciasBinding.setLifecycleOwner(this);
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
//
//
//    private void obterRegistos(){
//
//        //--TESTE (apagar quando houver dados)
//
//        List<Item> t1 = new ArrayList<>();
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 1", "Departamento norte", "12345235", "2020-02-20", "Marca 1", "estado 1"));
////        t1.add(new Ocorrencia(2, "Ocorrencia numero 2", "Departamento sul", "674543", "2020-02-18", "Marca 20", "estado raly"));
//
//        //ocorrenciaRecyclerAdapter.renovarRegistos(t1);
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
//    //---------------------
//    //Eventos
//    //---------------------
//
////    @OnClick(R.id.crl_img_regressar)
//    public void crl_img_regressar_OnClickListener(View view) {
//        obterRegistos();
//    }
//
//    @OnClick(R.id.fab_adicionar)
//    public void fab_calendario_OnClickListener(View view) {
//        Intent intent = new Intent(this, RegistoOcorrenciasActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivityForResult(intent, 1111111);
//    }
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
