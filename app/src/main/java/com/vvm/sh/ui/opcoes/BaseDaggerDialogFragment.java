package com.vvm.sh.ui.opcoes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityBaseDaggerBinding;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import dagger.android.support.DaggerDialogFragment;

public abstract class BaseDaggerDialogFragment extends DaggerDialogFragment {

    protected ViewDataBinding activityBaseBinding;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        activityBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), obterLayout(), null, false);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(getActivity().getString(obterTitulo()));
        alertDialogBuilder.setView(activityBaseBinding.getRoot());

        initDialogo(alertDialogBuilder);

        activityBaseBinding.setLifecycleOwner(this);



        return alertDialogBuilder.create();
    }



    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite terminar o dialogo
     */
    protected void terminarDialogo(){

        final AlertDialog dialogo = (AlertDialog)getDialog();

        if(dialogo != null){
            dialogo.dismiss();
        }
    }



    //--------------------
    //Metodos abstratos
    //---------------------


    /**
     * Metodo que permite criar o dialogo
     * @param builder
     */
    protected abstract void initDialogo(AlertDialog.Builder builder);


    /**
     * Metodo que permite obter o layout da activity
     * @return um layout
     */
    protected abstract int obterLayout();


    /**
     * Metodo que permite obter o titulo do dialogo
     * @return um titulo
     */
    protected abstract int obterTitulo();


}