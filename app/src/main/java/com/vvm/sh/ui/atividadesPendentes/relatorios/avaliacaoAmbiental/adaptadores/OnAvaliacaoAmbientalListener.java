package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.adaptadores;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;

public interface OnAvaliacaoAmbientalListener {

    void OnAvaliacaoClick(AvaliacaoAmbientalResultado registo);

    void onRemoverClick(AvaliacaoAmbientalResultado registo);
}
