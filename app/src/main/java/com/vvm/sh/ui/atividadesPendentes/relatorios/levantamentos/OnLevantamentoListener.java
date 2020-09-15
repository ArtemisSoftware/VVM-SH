package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;

public interface OnLevantamentoListener {


    public interface OnCategoriaProfissionalListener{

        void OnCategoriaProfissionalClick(CategoriaProfissionalResultado registo);

    }

}
