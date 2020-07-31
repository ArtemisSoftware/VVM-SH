package com.vvm.sh.ui.anomalias.adaptadores;

import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;

public interface OnAnomaliasListener {

    void onEditarClick(int id);

    void onRemoverClick(AnomaliaRegistada anomalia);
}
