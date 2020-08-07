package com.vvm.sh.ui.agenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.vvm.sh.ui.BaseDialogFragment;
import com.vvm.sh.ui.BaseDialogoOpcoesFragment;

public class DialogoOpcoesTrabalhoFragment extends BaseDialogoOpcoesFragment {



    private DialogoListener listener;


    @Override
    protected String[] obterOpcoes() {

        String opcoes [] = { "Recarregar trabalho do dia", "Reenviar dados" };
        return opcoes;
    }

    @Override
    protected DialogInterface.OnClickListener obterMetodo() {

        DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {


                    case 0: //recarregar trabalho do dia

                        listener.recarregarTrabalho();
                        //recarregarTrabalho(((MenuLateral_Agenda) menuLateral).obterData() /*"2019-01-17"*/);
                        break;


                    case 1: //reenviar trabalho do dia

                        listener.reUploadDados();
                        //reUploadDados(((MenuLateral_Agenda) menuLateral).obterData());
                        break;

                    default:
                        break;
                }


                dialog.cancel();
            }
        };

        return metodo;
    }


    @Override
    protected boolean obterEstadoCancelamento() {
        return false;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogoListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }


    public interface DialogoListener {

        void recarregarTrabalho();

        void reUploadDados();
    }

}
