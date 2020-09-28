package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;

public class CategoriaProfissional {

    @Embedded
    public CategoriaProfissionalResultado categoria;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "valido")
    public boolean valido;
}
