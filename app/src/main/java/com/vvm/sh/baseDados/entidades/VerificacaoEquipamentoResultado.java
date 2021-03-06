package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.vvm.sh.util.constantes.Identificadores;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "verificacaoEquipamentosResultado",
        primaryKeys = {"idAtividade","idEquipamento","codigo"},
        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class VerificacaoEquipamentoResultado {


    @NonNull
    public int idAtividade;


    @NonNull
    public int idEquipamento;

    @NonNull
    public int codigo;


    public VerificacaoEquipamentoResultado(int idAtividade, int idEquipamento, int codigo) {
        this.idAtividade = idAtividade;
        this.idEquipamento = idEquipamento;
        this.codigo = codigo;
    }

    @Ignore
    public VerificacaoEquipamentoResultado(int idAtividade, int idEquipamento) {
        this.idAtividade = idAtividade;
        this.idEquipamento = idEquipamento;
        this.codigo = Identificadores.Estados.Equipamentos.ESTADO_DEFINITIVO;
    }

}
