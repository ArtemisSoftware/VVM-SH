package com.vvm.sh.ui.atividadesPendentes;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteExecutadaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogoAtividadePendenteExecutada extends BaseDaggerDialogoPersistenteFragment
        implements Validator.ValidationListener, DatePickerDialog.OnDateSetListener{



    private DialogoAtividadePendenteExecutadaBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;




    private Validator validador;


    private static final String ARGUMENTO_ID_ATIVIDADE = "id";


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_minutos)
    TextInputEditText txt_inp_minutos;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data_execucao)
    TextInputEditText txt_inp_data_execucao;




    public DialogoAtividadePendenteExecutada() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAtividadePendenteExecutada newInstance(int id) {
        DialogoAtividadePendenteExecutada fragmento = new DialogoAtividadePendenteExecutada();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_ATIVIDADE, id);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoAtividadePendenteExecutadaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_ATIVIDADE) == true){
            validador = new Validator(this);
            validador.setValidationListener(this);

            viewModel.obterAtividade(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
        }
        else{
            terminarDialogo();
        }

    }



    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_executada;
    }

    @Override
    protected int obterTitulo() {
        return R.string.concluir_atividade;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem, listener);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });
    }



    @Override
    protected void clickPositivo() {
        ativarValidacao(true);
        validador.validate();
    }



    //---------------------
    //Metodos locais
    //---------------------


    /**
     * Metodo que permite ativar a validacao de campos especificos
     * @param ativar true para ativar ou false caso contrario
     */
    private void ativarValidacao(boolean ativar){

        binding.txtInpDataExecucao.setEnabled(ativar);
    }




    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.crl_btn_data_execucao)
    public void crl_btn_data_execucao_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(this);
        dialogo.fixarLimiteInferior(Calendar.DATE, -1 * Identificadores.Dias.DIAS_ACTIVIDADES_EXECUTADA);
        dialogo.obterDatePickerDialog().show(getActivity().getSupportFragmentManager(), "Datepickerdialog");
    }



    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        String minutos = txt_inp_minutos.getText().toString();
        String data = txt_inp_data_execucao.getText().toString();

        AtividadePendenteResultado atividade = new AtividadePendenteResultado(idAtividade, minutos, DatasUtil.converterString(data, DatasUtil.FORMATO_DD_MM_YYYY));
        viewModel.gravarAtividade(PreferenciasUtil.obterIdTarefa(getContext()), atividade);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages

            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            }

        }

        ativarValidacao(false);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        binding.txtInpDataExecucao.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MM_YYYY));
    }
}
