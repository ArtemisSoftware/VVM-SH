package com.vvm.sh.ui.anomalias;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

import java.util.List;

import butterknife.BindView;

public class DialogoAnomalia extends BaseDialogFragment implements Validator.ValidationListener{


    @Select
    @BindView(R.id.spnr_anomalia)
    PowerSpinnerView spnr_anomalia;

    @BindView(R.id.txt_inp_observacao)
    TextInputEditText txt_inp_observacao;

    private DialogAnomaliaListener listener;
    private Validator validador;


    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

        // Validation
        validador = new Validator(this);
        validador.setValidationListener(this);

        builder.setTitle(getString(R.string.anomalia))
                .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(getString(R.string.gravar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        validador.validate();

                        //listener.gravarAnomalia(spnr_anomalia.getText().toString(), txt_inp_observacao.getText().toString());
                    }
                });
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_anomalia;
    }


    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages
            if (view instanceof PowerSpinnerView) {
                ((TextView) ((PowerSpinnerView) view).getRootView()).setError(message);
                //((TextView) ((Spinner) view).getSelectedView()).setError(message);
            }
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogAnomaliaListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogAnomaliaListener {
        void gravarAnomalia(String idAnomalia, String observacao);
    }
}
