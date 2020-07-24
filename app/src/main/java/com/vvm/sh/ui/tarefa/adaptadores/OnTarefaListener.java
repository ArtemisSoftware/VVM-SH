package com.vvm.sh.ui.tarefa.adaptadores;

import com.vvm.sh.ui.tarefa.modelos.Email;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;

public interface OnTarefaListener {

    void OnOpcaoItemListener(OpcaoCliente opcao);

    void OnGravarEmailListener(Email email);
}
