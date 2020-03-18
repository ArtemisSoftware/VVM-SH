package com.vvm.sh.ui.agenda;

import android.content.Context;
import android.content.DialogInterface;

import com.vvm.sh.ui.BaseDialogoOpcoesFragment;

public class DialogoOpcoesTarefaFragment extends BaseDialogoOpcoesFragment {

    private DialogoListener listener;

    @Override
    protected String[] obterOpcoes() {

        String opcoes [] = { "Atualizar tarefa" };
        return opcoes;
    }

    @Override
    protected DialogInterface.OnClickListener obterMetodo() {

        DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {


                    case 0: //validar dados

                        listener.recarregarTarefa();
                        //MetodosDialogo.dialogoValidacaoDados(contexto, ((MenuLateral_Agenda) menuLateral).obterData());
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

        void recarregarTarefa();
    }
}
