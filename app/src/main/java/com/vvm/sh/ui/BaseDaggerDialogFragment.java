package com.vvm.sh.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vvm.sh.util.metodos.MensagensUtil;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;

import butterknife.ButterKnife;
import dagger.android.support.DaggerDialogFragment;

public abstract class BaseDaggerDialogFragment extends DaggerDialogFragment {

    protected ViewDataBinding activityBaseBinding;

    public MensagensUtil dialogo;

    public OnDialogoListener listener;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        activityBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), obterLayout(), null, false);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(getActivity().getString(obterTitulo()));
        alertDialogBuilder.setView(activityBaseBinding.getRoot());

        ButterKnife.bind(this, activityBaseBinding.getRoot());


        alertDialogBuilder.setPositiveButton(Sintaxe.Opcoes.GRAVAR,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                clickPositivo();
            }
        });

        alertDialogBuilder.setNegativeButton(Sintaxe.Opcoes.CANCELAR, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                terminarDialogo();
            }
        });


        initDialogo(alertDialogBuilder);

        activityBaseBinding.setLifecycleOwner(this);

        dialogo = new MensagensUtil(getActivity());


        listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                terminarDialogo();
            }
        };

        subscreverObservadores();

        final AlertDialog dialogo = alertDialogBuilder.create();
        return dialogo;
    }



    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite formatar o dialogo
     * @param dialogo o dialogo a formatar
     * @return um dialogo formatado
     */
    protected AlertDialog formatarDialogo(AlertDialog dialogo){
        return dialogo;
    }

    /**
     * Metodo que permite terminar o dialogo
     */
    protected void terminarDialogo(){

        final AlertDialog dialogo = (AlertDialog)getDialog();

        if(dialogo != null){
            dialogo.dismiss();
        }
    }


    /**
     * Metodo que verifica a validade do bundle
     * @param argumento o nome do argumento a validar
     * @return true caso o bundle seja válido ou false caso contrário
     */
    protected boolean verificarArgumentos(String argumento){

        if(getArguments() == null) {
            return false;
        }

        return getArguments().containsKey(argumento);
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


    /**
     * Metodo que permite subscrever observadores
     */
    protected abstract void subscreverObservadores();


    /**
     * Metodo que permite lidar com o click positivo do dialogo
     */
    protected abstract void clickPositivo();
}
