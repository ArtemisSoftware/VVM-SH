package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.ItemChecklist;

public class Questao {

    @Embedded
    public ItemChecklist registo;

}
