package com.vvm.sh.ui.upload.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.List;

public class Upload {

    @Embedded
    public Tarefa tarefa;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa", entity = Resultado.class)
    public List<Resultado> resultados;

}
