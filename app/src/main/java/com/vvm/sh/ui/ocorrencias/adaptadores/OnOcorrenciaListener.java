package com.vvm.sh.ui.ocorrencias.adaptadores;

import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

public interface OnOcorrenciaListener {


    void OnOcorrenciaClick(Ocorrencia ocorrencia);


    void onRemoverClick(OcorrenciaRegisto ocorrencia);
}
