package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

public interface OnAtividadePendenteListener {

    void OnAtividadeClick(AtividadePendente atividade);

    void OnConcluirAtividadeExecutada();

    void OnConcluirAtividadeNaoExecutada();

    void OnIniciarRelatorio(AtividadePendente atividade);

}
