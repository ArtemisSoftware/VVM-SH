package com.vvm.sh.ui.cliente.extintores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.databinding.ActivityExtintoresBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.cliente.extintores.adaptadores.OnExtintoresListener;
import com.vvm.sh.ui.cliente.extintores.modelos.ExtintorRegisto;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ExtintoresActivity extends BaseDaggerActivity
    implements OnExtintoresListener, DatePickerDialog.OnDateSetListener {


    private ActivityExtintoresBinding activityExtintoresBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;


    private ExtintorRegisto selecionado;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activityExtintoresBinding = (ActivityExtintoresBinding) activityBinding;
        activityExtintoresBinding.setLifecycleOwner(this);
        activityExtintoresBinding.setViewmodel(viewModel);
        activityExtintoresBinding.setListener(this);
        activityExtintoresBinding.setEstatistica(0);
        activityExtintoresBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();


        viewModel.obterExtintores(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_extintores;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }



    //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnExtintorClick(ExtintorRegisto registo) {

        selecionado = registo;

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(this);
        dialogo.fixarLimiteSuperior(Calendar.DATE, Identificadores.Dias.UM_ANO);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.fab_validar)
    public void fab_validar_OnClickListener(View view) {
        viewModel.validarExtintores(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        viewModel.gravarExtintor(selecionado, DatasUtil.converterData_Date(year, monthOfYear, dayOfMonth));
    }


//    //---------------------
//    //Eventos
//    //---------------------

//
//    /**
//     * Meodo que inicializa o diálogo
//     */
//    /*
//    private void initDialogo(View vista){
//
//        Calendar cal = Calendar.getInstance();
//
//        try{
//
//            cal.setTime(MetodosDatas.obterDataHoje());
//            cal.add(Calendar.DATE,  + FormulariosIF.DIAS_RELATORIO);
//            Date maxDate = cal.getTime();
//
//            ((DatePicker) vista.findViewById(R.id.pick_data_validade)).setMaxDate (maxDate.getTime ());
//
//            cal.setTime(MetodosDatas.obterDataHoje());
//            //por decidir//cal.add(Calendar.DATE,  - FormulariosIF.DIAS_RELATORIO);
//            Date minDate = cal.getTime();
//
//            ((DatePicker) vista.findViewById(R.id.pick_data_validade)).setMinDate (minDate.getTime ());
//
//        }
//        catch(IllegalArgumentException e){}
//
//    }
//*/
//
//
//    /**
//     * Metodo que gera o dialogo para as especificações do relatorio.
//     */
// /*   private void dialogoDataValidade(){
//
//        LayoutInflater li = LayoutInflater.from(this);
//        final View vista = li.inflate(R.layout.dialogo_extintor, null);
//        initDialogo(vista);
//
//        DialogInterface.OnClickListener metodoGravar = new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                gravar(vista);
//                dialog.cancel();
//            }
//        };
//
//        MetodosDialogo.dialogo(contexto, vista, SintaxeIF.TITULO_DATA_VALIDADE, metodoGravar).show();
//
//        MetodosDatas.fixarData_DatePicker(((DatePicker) vista.findViewById(R.id.pick_data_validade)), ((ExtintorRegisto) adaptador.obterRegistoSelecionado()).obterDataValidade(DataIF.FORMATO_YYYY_MM_DD));
//    }
//*/
}
