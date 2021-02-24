package com.vvm.sh.ui.agenda;

import android.content.Context;
import android.content.DialogInterface;

import com.vvm.sh.R;
import com.vvm.sh.ui.base.BaseDialogoOpcoesFragment;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class DialogoOpcoesTrabalhoFragment extends BaseDialogoOpcoesFragment {



    private OnAgendaListener.OnOpcoesListener listener;


    @Override
    protected String[] obterOpcoes() {

        if(PreferenciasUtil.agendaEditavel(getActivity()) == false){
            return  new String[]{ getString(R.string.reenviar_dados) };
        }
        else{
            return new String[]{ getString(R.string.recarregar_trabalho_dia), getString(R.string.reenviar_dados) };
        }
    }


    @Override
    protected DialogInterface.OnClickListener obterMetodo() {

        if(obterOpcoes().length == 2) {

            DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {

                    switch (item) {

                        case 0: //recarregar trabalho do dia

                            listener.recarregarTrabalho();
                            break;


                        case 1: //reenviar trabalho do dia

                            listener.reUploadDados();
                            break;

                        default:
                            break;
                    }

                    dialog.cancel();
                }
            };

            return metodo;
        }
        else{
            DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int item) {

                    switch (item) {

                        case 0: //reenviar trabalho do dia

                            listener.reUploadDados();
                            break;

                        default:
                            break;
                    }

                    dialog.cancel();
                }
            };

            return metodo;
        }
    }


    @Override
    protected boolean obterEstadoCancelamento() {
        return false;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (OnAgendaListener.OnOpcoesListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

}
