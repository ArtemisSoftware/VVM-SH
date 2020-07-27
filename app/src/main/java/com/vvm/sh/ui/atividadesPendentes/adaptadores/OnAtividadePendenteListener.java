package com.vvm.sh.ui.atividadesPendentes.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

public interface OnAtividadePendenteListener {

    void OnAtividadeClick(AtividadePendente atividade);

    void OnConcluirAtividadeExecutada(int idAtividade);

    void OnGravarAtividadeExecutada(int idAtividade, String minutos, String data);

    void OnConcluirAtividadeNaoExecutada(int idAtividade);

    void OnGravarAtividadeNaoExecutada(int idAtividade, int idAnomalia, String observacao);


    void OnIniciarRelatorio(AtividadePendente atividade);

}
