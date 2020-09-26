package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;

public interface OnAtividadePendenteListener {

    void OnAtividadeClick(AtividadePendenteRegisto atividade);

    void OnConcluirAtividadeExecutada(int idAtividade);


    void OnConcluirAtividadeNaoExecutada(int idAtividade);


    void OnIniciarRelatorio(int idAtividade, int idRelatorio);

    void OnDetalhe(int idAtividade);

    void OnIniciarChecklist(int idAtividade);

    void OnIniciarProcessoProdutivo(int idAtividade);
    void OnIniciarVulnerabilidades(int idAtividade);


}
