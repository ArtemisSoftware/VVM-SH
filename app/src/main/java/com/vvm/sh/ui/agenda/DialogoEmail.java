package com.vvm.sh.ui.agenda;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

import butterknife.BindView;

public class DialogoEmail extends BaseDialogFragment {

    private EditText editTextPassword;

    @BindView(R.id.txt_inp_email)
    TextInputEditText txt_inp_email;

    private DialogEmailListener listener;
/*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_email, null);

        builder.setView(view)
                .setTitle(getString(R.string.email))
                .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(getString(R.string.gravar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String username = editTextUsername.getText().toString();
                        //String password = editTextPassword.getText().toString();
                        listener.gravarEmail("username", 1);
                    }
                });

        //editTextUsername = view.findViewById(R.id.edit_username);
        //editTextPassword = view.findViewById(R.id.edit_password);

        return builder.create();
    }
*/
    @Override
    protected int obterLayout() {
        return R.layout.dialogo_email;
    }

    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

        builder.setTitle(getString(R.string.email))
                .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(getString(R.string.gravar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //String username = editTextUsername.getText().toString();
                        //String password = editTextPassword.getText().toString();
                        listener.gravarEmail("username", 1);
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogEmailListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogEmailListener {
        void gravarEmail(String email, int estado);
    }
}