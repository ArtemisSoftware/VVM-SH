package com.vvm.sh.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;


public abstract class BaseDialogoOpcoesFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setCancelable(obterEstadoCancelamento());

        builder.setItems(obterOpcoes(), obterMetodo());

        return builder.create();
    }



    //--------------------
    //Metodos abstratos
    //---------------------


    /**
     * Metodo que permite obter a descricao da opcoes
     * @return uma lista
     */
    protected abstract String [] obterOpcoes();


    /**
     * Metodo que permite obter o metodo que lida com as opções
     * @return um metodo
     */
    protected abstract DialogInterface.OnClickListener obterMetodo();


    /**
     * Metodo que indica se o dialogo pode ser cancelado
     * @return true caso possa ser cancelado ou false caso contrario
     */
    protected abstract boolean obterEstadoCancelamento();


    /*


    	public static void dialogoOpcoesTrabalho(Context contexto, String titulo, final CharSequence[] items, DialogInterface.OnClickListener metodo) {

		Builder dialogo = dialogoBase(contexto, titulo);
		dialogo.setCancelable(true);
	    dialogo.setItems(items, metodo).show();
	}

     */
}
