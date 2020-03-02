package com.vvm.sh.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;

import com.vvm.sh.ui.BaseDialogFragment;


/**
 * Classe que permite criar a base de um dialogo persistente.
 * <br><br>
 * Um dialogo persistente só termina quando determinadas regras forem obedecidas
 */
public abstract class BaseDialogoPersistenteFragment extends BaseDialogFragment {

    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

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


    /**
     * Metodo que permite lidar com o click positivo do dialogo
     */
    protected abstract void clickPositivo();

}
