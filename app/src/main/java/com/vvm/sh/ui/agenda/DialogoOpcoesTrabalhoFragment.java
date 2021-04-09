package com.vvm.sh.ui.agenda;

import android.content.Context;
import android.content.DialogInterface;

import com.vvm.sh.R;
import com.vvm.sh.ui.base.BaseDialogoOpcoesFragment;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.base.modelos.OpcaoDialogo;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

public class DialogoOpcoesTrabalhoFragment extends BaseDialogoOpcoesFragment {



    private OnAgendaListener.OnOpcoesListener listener;


    @Override
    protected List<OpcaoDialogo> obterOpcoes() {

        List<OpcaoDialogo> opcoes = new ArrayList<>();
        opcoes.add(new OpcaoDialogo(0, getString(R.string.reenviar_dados)));

        if(PreferenciasUtil.agendaEditavel(getActivity()) == true){
            opcoes.add(new OpcaoDialogo(1, getString(R.string.recarregar_trabalho_dia)));
            opcoes.add(new OpcaoDialogo(2, getString(R.string.atualizar_trabalho_dia)));
            opcoes.add(new OpcaoDialogo(3, getString(R.string.recarregar_trabalho_dia_sa)));
            opcoes.add(new OpcaoDialogo(4, getString(R.string.recarregar_trabalho_dia_sht)));
        }

        return opcoes;
    }


    @Override
    protected DialogInterface.OnClickListener obterMetodo() {

        DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {

                    case 0:
                        listener.reUploadDados();

                        break;


                    case 1:

                        listener.recarregarTrabalho();
                        break;


                    case 2: //atualizar trabalho do dia

                        listener.atualizarTrabalho();
                        break;

                    case 3:  //reenviar trabalho do dia sa

                        listener.recarregarTrabalho(Identificadores.App.APP_SA);
                        break;

                    case 4: //reenviar trabalho do dia sht

                        listener.recarregarTrabalho(Identificadores.App.APP_ST);
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
            listener = (OnAgendaListener.OnOpcoesListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

}
