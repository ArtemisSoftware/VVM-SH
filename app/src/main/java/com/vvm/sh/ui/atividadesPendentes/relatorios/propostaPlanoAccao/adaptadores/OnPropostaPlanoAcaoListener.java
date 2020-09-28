package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

public interface OnPropostaPlanoAcaoListener {


    void OnCheckProposta(Proposta registo, boolean selecionado);
}
