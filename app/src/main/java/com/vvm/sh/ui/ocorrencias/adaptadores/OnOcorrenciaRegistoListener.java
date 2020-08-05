package com.vvm.sh.ui.ocorrencias.adaptadores;

import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

public interface OnOcorrenciaRegistoListener {

    void OnOcorrenciaClick(OcorrenciaRegisto ocorrencia);

    void onRemoverClick(OcorrenciaRegisto ocorrencia);
}
