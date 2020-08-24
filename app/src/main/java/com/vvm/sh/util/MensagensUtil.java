package com.vvm.sh.util;

import android.content.Context;

import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MensagensUtil {

    private SweetAlertDialog dialogo;


    public MensagensUtil(Context contexto) {
        dialogo = new SweetAlertDialog(contexto);
    }


    public void sucesso(String titulo, String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

        dialogo.setTitleText(Sintaxe.Palavras.SUCESSO)
                .setTitleText(titulo)
                .setContentText(mensagem)
                .show();
    }


    public void sucesso(OnDialogoListener listener){

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        dialogo.setTitleText(Sintaxe.Palavras.SUCESSO)
                .setContentText(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        listener.onTerminarDialogo();
                    }
                });

        dialogo.show();
    }



    public void sucesso(String mensagem, OnDialogoListener listener){

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
        dialogo.setTitleText(Sintaxe.Palavras.SUCESSO)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        listener.onTerminarDialogo();
                    }
                });

        dialogo.show();
    }


    public void erro(String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);

        dialogo.setTitleText("Oops...")
                .setContentText(mensagem)
                .show();
    }

    public void erro(String titulo, String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);

        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .show();
    }

    public void erro(String titulo, String mensagem, OnDialogoListener listener) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        listener.onTerminarDialogo();
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



    public void alerta(String titulo, String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.WARNING_TYPE);

        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .show();
    }


//
//// 1. Success message
//new SweetAlertDialog(MainActivity.this)
//    .setTitleText("Here's a message!")
//    .show();
//
//// 2. Confirmation message
//new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
//    .setTitleText("Are you sure?")
//    .setContentText("You won't be able to recover this file!")
//    .setConfirmText("Delete!")
//    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//        @Override
//        public void onClick(SweetAlertDialog sDialog) {
//            sDialog.dismissWithAnimation();
//        }
//    })
//            .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
//        @Override
//        public void onClick(SweetAlertDialog sDialog) {
//            sDialog.dismissWithAnimation();
//        }
//    })
//            .show();
//
//// 3. Error message
//new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
//        .setTitleText("Oops...")
//        .setContentText("Something went wrong!")
//        .show();
//
//    // 4. Loading message
//    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading ...");
//        pDialog.setCancelable(true);
//        pDialog.show();
//
//// 5. Confirm success
//new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
//    .setTitleText("Are you sure?")
//    .setContentText("Won't be able to recover this file!")
//    .setConfirmText("Yes,delete it!")
//    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//        @Override
//        public void onClick(SweetAlertDialog sDialog) {
//            sDialog
//                    .setTitleText("Deleted!")
//                    .setContentText("Your imaginary file has been deleted!")
//                    .setConfirmText("OK")
//                    .setConfirmClickListener(null)
//                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//        }
//    })
//            .show();




}
