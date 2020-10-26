package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;

import java.util.List;

public class Risco {

    @Embedded
    public RiscoResultado resultado;

    @ColumnInfo(name = "risco")
    public String risco;

    @ColumnInfo(name = "riscoEspecifico")
    public String riscoEspecifico;

    @ColumnInfo(name = "valido")
    public boolean valido;

    @ColumnInfo(name = "numeroImagens")
    public int numeroImagens;


    @Ignore
    public List<Tipo> medidasRecomendadas;

    @Ignore
    public List<Tipo> medidasExistentes;

    @Ignore
    public List<ImagemResultado> imagem;

}
