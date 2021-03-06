package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;

import java.util.List;

public class TrabalhadorVulneravel {

    @Embedded
    public TrabalhadorVulneravelResultado resultado;



    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "feminina")
    public boolean feminina;


    @ColumnInfo(name = "valido")
    public boolean valido;

    @Ignore
    public List<Tipo> categoriasProfissionaisHomens;

    @Ignore
    public List<Tipo> categoriasProfissionaisMulheres;
}
