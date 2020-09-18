package com.vvm.sh.ui.pesquisa;

import com.vvm.sh.baseDados.entidades.Tipo;

public interface OnPesquisaListener {


    interface OnPesquisaSelecionadoListener {

        void OnSelecionarClick(Tipo registo);
    }


    interface OnPesquisaRegistoListener {

        void OnRemoverSelecao(Tipo registo);
    }

}
