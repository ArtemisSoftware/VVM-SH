package com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.modelos.Formando;

public interface OnFormacaoListener {

    void OnFormandoClick(Formando formando);

    void OnSelecionadoCheck(Formando formando, boolean selecionado);
}
