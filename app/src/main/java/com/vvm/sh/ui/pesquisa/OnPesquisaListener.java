package com.vvm.sh.ui.pesquisa;

import com.vvm.sh.baseDados.entidades.Tipo;

public interface OnPesquisaListener {

    void OnSelecionarClick(Tipo registo);

    void OnRemoverSelecao(Tipo registo);

}
