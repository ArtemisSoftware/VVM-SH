package com.vvm.sh.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.vvm.sh.R;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(obterLayout(), null);

        builder.setView(view);

        ButterKnife.bind(this, view);
        criarDialogo(builder);

        return builder.create();
    }


    //--------------------
    //Metodos abstratos
    //---------------------


    /**
     * Metodo que permite criar o dialogo
     * @param builder
     */
    protected abstract void criarDialogo(AlertDialog.Builder builder);


    /**
     * Metodo que permite obter o titulo do dialogo
     * @return o titulo
     */
    protected abstract String obterTitulo();


    /**
     * Metodo que permite obter o layout do dialogo
     * @return o layout
     */
    protected abstract int obterLayout();


}
