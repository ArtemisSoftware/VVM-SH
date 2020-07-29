package com.vvm.sh.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

public abstract class BaseDaggerDialogoPersistenteFragment extends BaseDaggerDialogFragment{
    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        builder.setTitle(obterTitulo())
                .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                .setNegativeButton(android.R.string.cancel, null);

        iniciarDialogo();
    }


    @Override
    public void onResume(){

        super.onResume();
        final AlertDialog dialogo = (AlertDialog)getDialog();

        if(dialogo != null){

            dialogo.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialogo.dismiss();
                }
            });


            dialogo.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    clickPositivo();
                }
            });
        }
    }





    //------------------------
    //Metodos abstratos
    //------------------------


    /**
     * Metodo que permite realizar inicializações ao dialogo
     */
    protected abstract void iniciarDialogo();



}
