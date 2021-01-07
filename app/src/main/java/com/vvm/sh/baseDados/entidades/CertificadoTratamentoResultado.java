package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "certificadoTratamentoResultado",

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class CertificadoTratamentoResultado {


    @PrimaryKey
    public int idTarefa;

    @NonNull
    public boolean pragaBarata;

    @NonNull
    public int visitaPragaBarata;

    @NonNull
    public String produtoAplicado;


    @NonNull
    public int avaliacaoCondicoesHigiene;

    @NonNull
    public int avaliacaoManutencaoInstalacoes;

    @NonNull
    public int avaliacaoCondicoesArmazenamento;

    @NonNull
    public boolean observacaoVestigiosPragas;

    @NonNull
    public boolean observacaoProdutosEmGel;
}
