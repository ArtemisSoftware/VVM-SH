package com.vvm.sh.ui.agenda.adaptadores;

import com.vvm.sh.ui.agenda.modelos.Marcacao;

public interface OnAgendaListener {

    void onItemClick(Marcacao marcacao);

    void onItemLongPress(Marcacao marcacao);


    interface OnOpcoesListener {

        void recarregarTrabalho();

        void reUploadDados();
    }

}
