package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;

public interface OnChecklistListener {

    interface OnItemListener {

        void OnItemClick(Item registo);
        void OnEditarClick(Item registo);
        void OnRemoverClick(Item registo);
    }

    interface OnQuestaoListener{

        void OnPerguntaClick(Questao registo);
        void OnObservacaoClick(Questao registo);

        void OnUtClick(Questao registo, int numeroUt);

        void OnGaleriaClick();

        void OnRegistarFoto();
    }
}
