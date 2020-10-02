package com.vvm.sh.ui.pesquisa;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;
import com.vvm.sh.ui.pesquisa.modelos.Medida;

public interface OnPesquisaListener {


    interface OnPesquisaSelecionadoListener {

        void OnRemoverSelecao(Tipo registo);
    }


    interface OnPesquisaRegistoListener {

        void OnSelecionarClick(Tipo registo);
    }




    interface OnPesquisaEquipamentoSelecionadoListener {

        void OnRemoverSelecao(Equipamento registo);
    }

    interface OnPesquisaEquipamentoRegistoListener {

        void OnSelecionarClick(Equipamento registo);
    }



    interface OnPesquisaMedidaListener {

        void OnSelecionarClick(Medida registo, boolean selecionado);
    }

}
