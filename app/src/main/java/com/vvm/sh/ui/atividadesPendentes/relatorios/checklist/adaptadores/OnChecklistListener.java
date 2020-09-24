package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores;

import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;

public interface OnChecklistListener {

    void OnItemClick(Item registo);

    void OnEditarClick(Item registo);

    void OnRemoverClick(Item registo);
}
