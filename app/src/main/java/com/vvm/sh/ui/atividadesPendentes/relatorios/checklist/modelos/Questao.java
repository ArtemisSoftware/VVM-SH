package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.ItemChecklist;

public class Questao {

    @Embedded
    public ItemChecklist registo;


    @ColumnInfo(name = "idRegistoItem")
    public int id;

    @ColumnInfo(name = "resposta")
    public String resposta;

    @ColumnInfo(name = "ni")
    public String ni;

    @ColumnInfo(name = "observacao")
    public String observacao;
}
