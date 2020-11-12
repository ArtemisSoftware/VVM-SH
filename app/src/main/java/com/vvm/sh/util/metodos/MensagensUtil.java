package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.vvm.sh.R;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MensagensUtil {

    private SweetAlertDialog dialogo;
    private AlertDialog.Builder builder;
    private Dialog dialogoProgresso;


    public MensagensUtil(Context contexto) {
        dialogo = new SweetAlertDialog(contexto);
        builder = new AlertDialog.Builder(contexto);
    }


    public void sucesso(String titulo, String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                    }
                });
    }


    public void sucesso(String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

        dialogo.setTitleText(Sintaxe.Palavras.SUCESSO)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                    }
                });

        dialogo.show();
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
                        listener.onExecutar();
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
                        //sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                        listener.onExecutar();
                    }
                });

        dialogo.show();
    }


    public void erro(String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);

        dialogo.setTitleText(Sintaxe.Opcoes.ERRO)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                    }
                });

        dialogo.show();
    }

    public void erro(String titulo, String mensagem) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);

        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                    }
                });

        dialogo.show();
    }

    public void erro(String titulo, String mensagem, OnDialogoListener listener) {

        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                        listener.onExecutar();
                    }
                });

        dialogo.show();
    }


    public void erro(Recurso recurso, OnDialogoListener listener) {

        String titulo = Sintaxe.Opcoes.ERRO;
        String mensagem = recurso.messagem;


        if(recurso.dados instanceof Codigo) {
            if (((Codigo) recurso.dados) != null) {
                titulo = recurso.messagem;
                mensagem = ((Codigo) recurso.dados).mensagem;
            }
        }



        dialogo.changeAlertType(SweetAlertDialog.ERROR_TYPE);
        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                        listener.onExecutar();
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
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                    }
                });

        dialogo.show();
    }


    public void alerta(String titulo, String mensagem, OnDialogoListener listener) {

        dialogo.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                        listener.onExecutar();
                    }
                });

        dialogo.show();
    }


    /**
     * Metodo que cria um dialogo que permite cancelar a acao proposta no dialogo
     * @param titulo o titulo do dialogo
     * @param mensagem a mensagem do dialogo
     * @param listener
     */
    public void alerta_OpcaoCancelar(String titulo, String mensagem, OnDialogoListener listener) {

        dialogo.changeAlertType(SweetAlertDialog.WARNING_TYPE);
        dialogo.setTitleText(titulo)
                .setContentText(mensagem)
                .setCancelText(Sintaxe.Opcoes.CANCELAR)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                    }
                })
                .setConfirmText(Sintaxe.Opcoes.OK)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        //sDialog.dismissWithAnimation();
                        sDialog.dismiss();
                        listener.onExecutar();
                    }
                });

        dialogo.show();
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


    /**
     * Metodo que permite criar uma mensagem snack
     * @param atividade a atividade onde deve aparecer a mensagem
     * @param mensagem a mensagem a figurar
     */
    public static void snack(Activity atividade, String mensagem){

        View layout = atividade.findViewById(android.R.id.content);
        Snackbar.make(layout, mensagem, Snackbar.LENGTH_LONG).show();
    }


    public void terminarProgresso(){
       if(dialogoProgresso != null) {
           dialogoProgresso.dismiss();
           dialogoProgresso = null;
       }
    }

    public void progresso(boolean show, String mensagem){
        builder.setView(R.layout.dialogo_progresso);

        dialogoProgresso = builder.create();

        if (show){
            dialogoProgresso.show();
            TextView alertTextView = (TextView) dialogoProgresso.findViewById(R.id.txt_mensagem);
            alertTextView.setText(mensagem);
        }
        else dialogoProgresso.dismiss();
    }

}
