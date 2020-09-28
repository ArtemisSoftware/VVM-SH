package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;

public interface OnLevantamentoListener {

    public interface OnLevantamentoRegistoListener{

        void OnLevantamentoClick(Levantamento registo);

        void OnDuplicarClick(Levantamento levantamento);

        void OnRemoverClick(Levantamento levantamento);

        void OnGaleriaClick(Levantamento levantamento);
    }


    public interface OnCategoriaProfissionalListener{

        void OnCategoriaProfissionalClick(CategoriaProfissionalResultado registo);

    }

}
