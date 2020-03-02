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


    @BindView(R.id.txt_inp_email)
    TextInputEditText txt_inp_email;

    private DialogEmailListener listener;


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
                        listener.gravarEmail(txt_inp_email.getText().toString(), 1);
                    }
                });

        //editTextUsername = view.findViewById(R.id.edit_username);
        //editTextPassword = view.findViewById(R.id.edit_password);
    }

    @Override
    protected String obterTitulo() {
        return getString(R.string.email);
    }

    /*
  	@SuppressWarnings("serial")
	Map<String, String > EMAIL_PERGUNTAS = new HashMap<String, String>(){{
		put(EMAIL_CLIENTE_NAO_TEM_EMAIL,ID_EMAIL_CLIENTE_NAO_TEM_EMAIL);
		put(EMAIL_AUTORIZADO,ID_EMAIL_AUTORIZADO);
		put(EMAIL_NAO_AUTORIZADO,ID_EMAIL_NAO_AUTORIZADO);
	}};



*/


    /**
     * Metodo que valida o email
     * @return true caso os dados sejam válidos e false caso contrário
     */
    /*
    private boolean validarEmail(){

        boolean valido = true;
        LinearLayout lnr_lyt_email_erros = (LinearLayout)viewEmail.findViewById(R.id.lnr_lyt_email_erros);
        TextView txt_view_email_erro = (TextView)viewEmail.findViewById(R.id.txt_view_email_erro);

        txt_view_email_erro.setVisibility(View.GONE);
        lnr_lyt_email_erros.setVisibility(View.GONE);

        if(spnr_email_estado.getSelectedItem().toString().equals(SintaxeIF.EMAIL_RESPOSTA_AUTORIZADO) == true
                ||
                spnr_email_estado.getSelectedItem().toString().equals(SintaxeIF.EMAIL_RESPOSTA_NAO_AUTORIZADO) == true){

            if(edit_txt_informacao_email.getText().toString().equals(AppIF.SEM_TEXTO)
                    ||
                    edit_txt_informacao_email.getText().toString().contains(SintaxeIF.ARROBA) == false){

                txt_view_email_erro.setVisibility(View.VISIBLE);
                valido = false & valido;
            }
        }

        if(valido == false){
            lnr_lyt_email_erros.setVisibility(View.VISIBLE);
        }
        return valido;
    }
*/

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