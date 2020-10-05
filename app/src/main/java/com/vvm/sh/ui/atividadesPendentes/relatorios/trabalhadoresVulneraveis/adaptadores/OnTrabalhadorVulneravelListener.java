package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;

public interface OnTrabalhadorVulneravelListener {


    void OnTrabalhadorVulneravelClick(TrabalhadorVulneravel registo);

    void onRemoverClick(TrabalhadorVulneravel vulnerabilidade);
}
