package com.vvm.sh.ui.atividadesPendentes;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.vvm.sh.R;
import com.vvm.sh.util.BaseDialogoPersistenteFragment;

import java.util.List;

import butterknife.BindView;

public class DialogoAtividadePendenteNaoExecutada extends BaseDialogoPersistenteFragment implements Validator.ValidationListener{


    @Select
    @BindView(R.id.spnr_anomalia)
    Spinner spnr_anomalia;


    @BindView(R.id.txt_inp_observacao)
    TextInputEditText txt_inp_observacao;

    private DialogoListener listener;
    private Validator validador;


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
        return R.layout.dialogo_atividade_pendente_nao_executada;
    }

    @Override
    public void onValidationSucceeded() {

        //listener.gravarAtividadeNaoExecutada(txt_inp_minutos.getText().toString(), txt_inp_observacao.getText().toString());
        terminarDialogo();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages

            if (view instanceof Spinner) {
                ((TextView) ((Spinner) view).getSelectedView()).setError(message);
            }

        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogoListener {
        void gravarAtividadeNaoExecutada(String idAnomalia, String observacao);
    }
}
