package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

public class AtividadePendenteRegisto {


    @Embedded
    public AtividadePendente atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public AtividadePendenteResultado resultado;


    @ColumnInfo(name = "nomeRelatorio")
    public String nomeRelatorio;


    @ColumnInfo(name = "possuiRelatorio")
    public boolean possuiRelatorio;

    @ColumnInfo(name = "relatorioCompleto")
    public boolean relatorioCompleto;



    /**
     * Metodo que indica se existe um resultado
     * @return true caso exista
     */
    public boolean existeResultado(){
        return resultado != null;
    }




}
