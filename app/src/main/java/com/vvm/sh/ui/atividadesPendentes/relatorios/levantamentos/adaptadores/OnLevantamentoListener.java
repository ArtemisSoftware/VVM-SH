package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;

public interface OnLevantamentoListener {

    public interface OnLevantamentoRegistoListener{

        void OnLevantamentoClick(Levantamento registo);

        void OnDuplicarClick(Levantamento levantamento);

        void OnRemoverClick(Levantamento levantamento);

        void OnGaleriaClick(Levantamento levantamento);

        void dialogoCategoriasProfissionais(int idModelo);
    }


    public interface OnCategoriaProfissionalListener{

        void OnCategoriaProfissionalClick(CategoriaProfissional registo);

        void OnRemoverClick(CategoriaProfissional categoria);
    }


    public interface OnRiscoListener{

        void OnRiscoClick(Risco registo);

        void OnRemoverClick(Risco registo);
    }
}
