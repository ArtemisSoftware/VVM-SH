package com.vvm.sh.ui.atividadesPendentes;

import android.content.Context;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.util.BaseDialogoPersistenteFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogoAtividadePendenteExecutada extends BaseDialogoPersistenteFragment implements Validator.ValidationListener{


    @NotEmpty(message = "Campo de preenchimento obrigatório")
    @BindView(R.id.txt_inp_minutos)
    TextInputEditText txt_inp_minutos;

    @NotEmpty(message = "Campo de preenchimento obrigatório")
    @BindView(R.id.txt_inp_data_execucao)
    TextInputEditText txt_inp_data_execucao;


    private Validator validador;

    private DialogListener listener;


    @Override
    protected void iniciarDialogo() {

        validador = new Validator(this);
        validador.setValidationListener(this);
    }

    @Override
    protected void clickPositivo() {
        validador.validate();
    }

    @Override
    protected String obterTitulo() {
        return getString(R.string.concluir_atividade);
    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_executada;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    @Override
    public void onValidationSucceeded() {

        listener.gravarAtividade(txt_inp_minutos.getText().toString(), txt_inp_data_execucao.getText().toString());
        terminarDialogo();
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
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.crl_btn_data_execucao)
    public void crl_btn_data_execucao_OnClickListener(View view) {

    }




    public interface DialogListener {
        void gravarAtividade(String minutos, String dataExecucao);
    }
}
