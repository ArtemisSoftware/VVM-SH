package com.vvm.sh.ui.pesquisa;

import com.vvm.sh.baseDados.entidades.Tipo;

public interface OnPesquisaListener {


    interface OnPesquisaSelecionadoListener {

        void OnRemoverSelecao(Tipo registo);
    }


    interface OnPesquisaRegistoListener {

        void OnSelecionarClick(Tipo registo);
    }

}
