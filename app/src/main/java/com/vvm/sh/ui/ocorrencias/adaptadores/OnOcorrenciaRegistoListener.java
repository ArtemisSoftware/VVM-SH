package com.vvm.sh.ui.ocorrencias.adaptadores;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

public interface OnOcorrenciaRegistoListener {

    void OnOcorrenciaClick(Tipo ocorrencia);

    void OnOcorrenciaCheck(Tipo ocorrencia, boolean selecionado);
}
