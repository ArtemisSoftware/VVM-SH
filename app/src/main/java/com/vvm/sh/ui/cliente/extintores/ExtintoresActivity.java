package com.vvm.sh.ui.cliente.extintores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ExtintoresActivity extends BaseActivity implements OnItemListener {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    private ExtintorRecyclerAdapter extintorRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extintores);

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

        extintorRecyclerAdapter = new ExtintorRecyclerAdapter(this);
        rcl_registos.setAdapter(extintorRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();
        t1.add(new Extintor(1, "Extintor grande", "2", "Rua de baixo", "2020-03-04", 1, 1));
        t1.add(new Extintor(2, "Extintor pequeno", "7", "Rua de cima", "2020-03-07", 0, 0));

        extintorRecyclerAdapter.fixarRegistos(t1);

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


    @OnClick(R.id.fab_validar)
    public void fab_validar_OnClickListener(View view) {
        //Intent intent = new Intent(this, TarefaActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        //startActivity(intent);
    }


    @Override
    public void onItemClick(int position) {



    }



    /**
     * Meodo que inicializa o diálogo
     */
    /*
    private void initDialogo(View vista){

        Calendar cal = Calendar.getInstance();

        try{

            cal.setTime(MetodosDatas.obterDataHoje());
            cal.add(Calendar.DATE,  + FormulariosIF.DIAS_RELATORIO);
            Date maxDate = cal.getTime();

            ((DatePicker) vista.findViewById(R.id.pick_data_validade)).setMaxDate (maxDate.getTime ());

            cal.setTime(MetodosDatas.obterDataHoje());
            //por decidir//cal.add(Calendar.DATE,  - FormulariosIF.DIAS_RELATORIO);
            Date minDate = cal.getTime();

            ((DatePicker) vista.findViewById(R.id.pick_data_validade)).setMinDate (minDate.getTime ());

        }
        catch(IllegalArgumentException e){}

    }
*/


    /**
     * Metodo que gera o dialogo para as especificações do relatorio.
     */
 /*   private void dialogoDataValidade(){

        LayoutInflater li = LayoutInflater.from(this);
        final View vista = li.inflate(R.layout.dialogo_extintor, null);
        initDialogo(vista);

        DialogInterface.OnClickListener metodoGravar = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                gravar(vista);
                dialog.cancel();
            }
        };

        MetodosDialogo.dialogo(contexto, vista, SintaxeIF.TITULO_DATA_VALIDADE, metodoGravar).show();

        MetodosDatas.fixarData_DatePicker(((DatePicker) vista.findViewById(R.id.pick_data_validade)), ((Extintor) adaptador.obterRegistoSelecionado()).obterDataValidade(DataIF.FORMATO_YYYY_MM_DD));
    }
*/
}
