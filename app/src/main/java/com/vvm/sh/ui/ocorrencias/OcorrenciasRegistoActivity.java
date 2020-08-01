package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciasBinding;
import com.vvm.sh.databinding.ActivityOcorrenciasRegistoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.adaptadores.OcorrenciaRecyclerAdapter;
import com.vvm.sh.ui.ocorrencias.adaptadores.OnOcorrenciaRegistoListener;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemSelected;

public class OcorrenciasRegistoActivity extends BaseDaggerActivity
        implements OnOcorrenciaRegistoListener, MaterialSpinner.OnItemSelectedListener, View.OnClickListener {



    private ActivityOcorrenciasRegistoBinding activityOcorrenciasRegistoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;

    private List<OcorrenciaRegisto> caminho;



    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciasRegistoBinding = (ActivityOcorrenciasRegistoBinding) activityBinding;
        activityOcorrenciasRegistoBinding.setLifecycleOwner(this);
        activityOcorrenciasRegistoBinding.setViewmodel(viewModel);
        activityOcorrenciasRegistoBinding.setListener(this);
        activityOcorrenciasRegistoBinding.spnrOcorrencias.setOnItemSelectedListener(this);

        activityOcorrenciasRegistoBinding.txtDescricao.setOnClickListener(this);

        caminho = new ArrayList<>();

        subscreverObservadores();

        viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencias_registo;
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
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        caminho.clear();

        activityOcorrenciasRegistoBinding.txtDescricao.setVisibility(View.INVISIBLE);
        Tipo tipo = (Tipo) item;
        viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), tipo.id);
    }

    @Override
    public void OnOcorrenciaClick(OcorrenciaRegisto ocorrencia) {

        if(ocorrencia.itemRegisto() == true) {
            Intent intent = new Intent(this, RegistarOcorrenciaActivity.class);
            intent.putExtra(getString(R.string.argumento_id), ocorrencia.id);
            startActivityForResult(intent, 000000);
        }
        else {

            caminho.add(ocorrencia);

            activityOcorrenciasRegistoBinding.txtDescricao.setVisibility(View.VISIBLE);
            activityOcorrenciasRegistoBinding.txtDescricao.setText(ocorrencia.codigo + " " + ocorrencia.descricao);
            viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), ocorrencia.id);
        }
    }

    @Override
    public void onRemoverClick(OcorrenciaRegisto ocorrencia) {
        viewModel.remover(Preferencias.obterIdTarefa(this), ocorrencia.id);
    }

    @Override
    public void onClick(View v) {

        if(caminho.size() > 1) {

            OcorrenciaRegisto ocorrencia = caminho.get(caminho.size() - 1);

            activityOcorrenciasRegistoBinding.txtDescricao.setText(ocorrencia.codigo + " " + ocorrencia.descricao);
            viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), ocorrencia.id);
            caminho.remove(caminho.size() - 1);
        }
        else{
            Tipo tipo = (Tipo) activityOcorrenciasRegistoBinding.spnrOcorrencias.getItems().get(activityOcorrenciasRegistoBinding.spnrOcorrencias.getSelectedIndex());
            onItemSelected(activityOcorrenciasRegistoBinding.spnrOcorrencias, 0, 0, tipo);

//            caminho.clear();
//
//            activityOcorrenciasRegistoBinding.txtDescricao.setVisibility(View.INVISIBLE);
//            Tipo tipo = (Tipo) activityOcorrenciasRegistoBinding.spnrOcorrencias.getItems().get(activityOcorrenciasRegistoBinding.spnrOcorrencias.getSelectedIndex());
//            viewModel.obterRegistosOcorrencias(Preferencias.obterIdTarefa(this), tipo.id);
        }
    }


//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
//    @BindView(R.id.spnr_ocorrencias)
//    Spinner spnr_ocorrencias;
//
//    @BindView(R.id.txt_descricao)
//    TextView txt_descricao;
//
//
//
//    private OcorrenciaRecyclerAdapter ocorrenciaRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ocorrencias_registo);
//
//        iniciarAtividade();
//        obterRegistos("1", true);
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
//        //ocorrenciaRecyclerAdapter = new OcorrenciaRecyclerAdapter(this, this);
//        rcl_registos.setAdapter(ocorrenciaRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//        rcl_registos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
//    }
//
//
//    private void obterRegistos(String idArea, boolean sinaletica){
//
//        //--TESTE (apagar quando houver dados)
//
//        List<String> list = new ArrayList<>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnr_ocorrencia.setAdapter(dataAdapter);
//
//
//        List<Item> t1 = new ArrayList<>();
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 1", "30.2", 1, 0));
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 2", "10.2", 1, 0));
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 34", "23.2", 1, 0));
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

//
//    @OnItemSelected(R.id.spnr_ocorrencia)
//    public void spnr_ocorrencia_ItemSelected(Spinner spinner, int position) {
//
//        //--TESTE (apagar quando houver dados)
//
//        txt_descricao.setVisibility(View.GONE);
//
//        List<Item> t1 = new ArrayList<>();
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 1478", "560.2", 1, 0));
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 22", "410.2", 2, 1));
//
//        //ocorrenciaRecyclerAdapter.renovarRegistos(t1);
//
//        //TODO: chamar metodo do viewmodel
//    }
//
//
//    @Override
//    public void onItemClick(int posicao) {
//
//        //--TESTE (apagar quando houver dados)
//
////        Ocorrencia registo = (Ocorrencia) ocorrenciaRecyclerAdapter.obterRegisto(posicao);
////
////        txt_descricao.setText(registo.obterCodigo() + " " + registo.obterDescricao());
////        txt_descricao.setVisibility(View.VISIBLE);
////
////        List<Item> t1 = new ArrayList<>();
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 50000", "2340.2", 1, 0));
////        t1.add(new Ocorrencia(1, "Ocorrencia numero 65434", "410.2", 2, 1));
////
////        ocorrenciaRecyclerAdapter.renovarRegistos(t1);
//
//
//
//        //TODO: chamar metodo do viewmodel
//
//    }
//
//    @Override
//    public void onItemChecked(int posicao, boolean selecao) {
//
//
//        if(selecao == true){
//            Intent intent = new Intent(this, RegistarOcorrenciaActivity.class);
//            //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//            startActivityForResult(intent, 000000);
//        }
//
//        else{
//
//            //TODO: chamar metodo do viewmodel
//            //--acessoBdCrossSelling.remover(registos.get(posicao).obterId());
//            //--atualizar();
//        }
//
//    }
//
//

}
