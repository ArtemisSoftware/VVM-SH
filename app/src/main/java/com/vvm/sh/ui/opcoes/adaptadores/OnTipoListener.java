package com.vvm.sh.ui.opcoes.adaptadores;

import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

public interface OnTipoListener {

    void OnTipoLongPressListener(ResumoTipo resumo);

    void OnTipoLongPressListener(ResumoChecklist resumo);

}
