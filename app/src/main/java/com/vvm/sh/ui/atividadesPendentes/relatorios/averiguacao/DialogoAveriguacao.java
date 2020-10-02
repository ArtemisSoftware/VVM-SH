package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoAveriguacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.OnClick;

public class DialogoAveriguacao extends BaseDaggerDialogoPersistenteFragment {



    private DialogoAveriguacaoBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;


    //--private AveriguacaoViewModel viewModel;


    private static final String ARGUMENTO_ID_RELATORIO = "idRelatorio";
    private static final String ARGUMENTO_ID = "id";


    public DialogoAveriguacao() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAveriguacao newInstance(int idRelatorio, int id) {
        DialogoAveriguacao fragmento = new DialogoAveriguacao();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_RELATORIO, idRelatorio);
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }



    @Override
    protected void iniciarDialogo() {

        //viewModel = ViewModelProviders.of(this, providerFactory).get(AveriguacaoViewModel.class);
        binding = (DialogoAveriguacaoBinding) activityBaseBinding;
        //binding.setViewmodel(viewModel);

        subscreverObservadores();

        if(verificarArgumentos(ARGUMENTO_ID) == true){

            binding.spnrImplementacao.setOnItemSelectedListener(spnr_implementacao_ItemSelected);
            binding.spnrRiscos.setOnItemSelectedListener(spnr_riscos_ItemSelected);

            //--viewModel.obterImplementacao(getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            terminarDialogo();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_averiguacao;
    }

    @Override
    protected int obterTitulo() {
        return R.string.implementacao;
    }

    @Override
    protected void subscreverObservadores() {

//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        dialogo.sucesso(recurso.messagem, listener);
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem);
//                        break;
//
//                    default:
//                        break;
//                }
//
//            }
//        });
    }

    @Override
    protected void clickPositivo() {

        RelatorioAveriguacaoResultado registo;

        Tipo implementacao = (Tipo) binding.spnrImplementacao.getItems().get(binding.spnrImplementacao.getSelectedIndex());
        Tipo risco = (Tipo) binding.spnrRiscos.getItems().get(binding.spnrRiscos.getSelectedIndex());

        if(implementacao.id == TiposConstantes.Averiguacao.MEDIDA_NAO_IMPLEMENTADA.id){
            registo = new RelatorioAveriguacaoResultado(getArguments().getInt(ARGUMENTO_ID_RELATORIO), getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            Date data = DatasUtil.converterString(binding.txtViewData.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
            registo = new RelatorioAveriguacaoResultado(getArguments().getInt(ARGUMENTO_ID_RELATORIO), getArguments().getInt(ARGUMENTO_ID), risco, data);
        }

        //--viewModel.gravar(registo);

    }



    //-------------------
    //Eventos
    //-------------------



    @OnClick(R.id.crl_btn_data)
    public void crl_btn_data_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(binding.txtViewData);
        dialogo.fixarLimites(Calendar.DATE, Identificadores.Dias.UM_ANO, Identificadores.Dias.UM_ANO);
        dialogo.obterDatePickerDialog().show(getActivity().getSupportFragmentManager(), "Datepickerdialog");
    }


    MaterialSpinner.OnItemSelectedListener spnr_implementacao_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            Tipo implementacao = (Tipo) binding.spnrImplementacao.getItems().get(position);

            if(implementacao.id == TiposConstantes.Averiguacao.MEDIDA_IMPLEMENTADA.id){
                binding.lnrLytRisco.setVisibility(View.VISIBLE);
            }
            else{
                binding.lnrLytRisco.setVisibility(View.GONE);
            }
        }
    };


    MaterialSpinner.OnItemSelectedListener spnr_riscos_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            Tipo risco = (Tipo) binding.spnrRiscos.getItems().get(position);
            binding.txtPonderacao.setText(risco.id + "");
        }
    };



}
