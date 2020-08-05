package com.vvm.sh.util;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MensagensUtil {

    private SweetAlertDialog dialogo;


    public MensagensUtil(Context contexto) {
        dialogo = new SweetAlertDialog(contexto);
    }



    public void sucesso(String mensagem){

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        dialogo.setTitleText("Are you sure?")
                .setContentText("You won't be able to recover this file!")
                .setConfirmText("Delete!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });

        dialogo.show();


    }

    public static void sucesso(){
        //TODO: escrever mensagem + delegate
    }

    public static void erro(){
        //TODO: escrever mensagem + delegate
    }

    public static void alerta(){
        //TODO: escrever mensagem + delegate
    }

}
