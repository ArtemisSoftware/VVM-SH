package com.vvm.sh.ui.anomalias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.skydoves.powerspinner.IconSpinnerAdapter;
import com.skydoves.powerspinner.IconSpinnerItem;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DialogoAnomalia extends BaseDialogFragment implements Validator.ValidationListener{


    @Select
    @BindView(R.id.spinner__)
    Spinner spinner__;


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


        builder.setTitle("www")
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null);



/*
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

*/
/*
        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner__.setAdapter(dataAdapter);
*/

        List<IconSpinnerItem> iconSpinnerItems = new ArrayList<>();
        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 1"));
        iconSpinnerItems.add(new IconSpinnerItem(null, "Report do Report do Report para o Report 2"));
        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 3"));
        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 4"));
        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 5"));

        IconSpinnerAdapter iconSpinnerAdapter = new IconSpinnerAdapter(spnr_anomalia);
        spnr_anomalia.setSpinnerAdapter(iconSpinnerAdapter);
        spnr_anomalia.setItems(iconSpinnerItems);

        //((TextView) (spinner__).getSelectedView()).setError("Error");
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_anomalia;
    }


    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onResume()
    {
        super.onResume();
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    validador.validate();
                    Boolean wantToCloseDialog = false;
                    //Do stuff, possibly set wantToCloseDialog to true then...
                    if(wantToCloseDialog)
                        d.dismiss();
                    //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                }
            });
        }
    }


    public void persistente(Dialog dialog){

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        validador.validate();

                        //Dismiss once everything is OK.
                        //dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
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

            if (view instanceof PowerSpinnerView) {
                ((TextView) ((PowerSpinnerView) view).getRootView()).setError(message);
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
