package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

public interface AtividadePendenteListener {

    void OnConcluirAtividadeExecutada();

    void OnConcluirAtividadeNaoExecutada();

    void OnIniciarRelatorio(AtividadePendente atividade);

}
