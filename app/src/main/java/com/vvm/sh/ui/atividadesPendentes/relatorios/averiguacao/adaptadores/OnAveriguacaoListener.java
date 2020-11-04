package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;

public interface OnAveriguacaoListener {

    void OnItemClick(Averiguacao registo);

    void OnNaoImplementados(Averiguacao registo);

    interface OnAveriguacaoItemListener {

        void OnItemClick(AveriguacaoRegisto registo);
    }
}
