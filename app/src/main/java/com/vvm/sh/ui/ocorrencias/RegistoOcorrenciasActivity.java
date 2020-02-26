package com.vvm.sh.ui.ocorrencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemSelected;

public class RegistoOcorrenciasActivity extends BaseActivity implements OnCheckBoxItemListener {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    @BindView(R.id.spnr_ocorrencia)
    Spinner spnr_ocorrencia;

    @BindView(R.id.txt_descricao)
    TextView txt_descricao;



    private OcorrenciaRecyclerAdapter ocorrenciaRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_ocorrencias);

        iniciarAtividade();
        obterRegistos("1", true);
    }


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        ocorrenciaRecyclerAdapter = new OcorrenciaRecyclerAdapter(this);
        rcl_registos.setAdapter(ocorrenciaRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
        rcl_registos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    }


    private void obterRegistos(String idArea, boolean sinaletica){

        //--TESTE (apagar quando houver dados)

        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_ocorrencia.setAdapter(dataAdapter);


        List<Item> t1 = new ArrayList<>();
        t1.add(new Ocorrencia(1, "Ocorrencia numero 1", "30.2", 1, 0));
        t1.add(new Ocorrencia(1, "Ocorrencia numero 2", "10.2", 1, 0));
        t1.add(new Ocorrencia(1, "Ocorrencia numero 34", "23.2", 1, 0));

        ocorrenciaRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


    //---------------------
    //Eventos
    //---------------------

    @OnItemSelected(R.id.spnr_ocorrencia)
    public void spnr_ocorrencia_ItemSelected(Spinner spinner, int position) {


        List<Item> t1 = new ArrayList<>();
        t1.add(new Ocorrencia(1, "Ocorrencia numero 1478", "560.2", 1, 0));
        t1.add(new Ocorrencia(1, "Ocorrencia numero 22", "410.2", 2, 1));

        ocorrenciaRecyclerAdapter.fixarRegistos(t1);
    }


    @Override
    public void onItemClick(int posicao, boolean selecao) {

        //TODO: saber se é sinaletica é uma informação contida no objeto da spinner


        if(selecao == true /*& sinaletica == false*/){
            //--atividade;
        }

        else{
            //--acessoBdCrossSelling.remover(registos.get(posicao).obterId());
            //--atualizar();
        }

    }


}
