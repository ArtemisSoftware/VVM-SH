package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "certificadoTratamentoResultado",
        indices = {@Index(value="idAtividade", unique = false) },
        primaryKeys = {"idAtividade"},
        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class CertificadoTratamentoResultado {



    @NonNull
    public int idAtividade;

    @NonNull
    public int idPraga;

    @NonNull
    public int idVisita;

    @NonNull
    public int idProdutoAplicado;


    @NonNull
    public int avaliacaoCondicoesHigiene;

    @NonNull
    public int avaliacaoManutencaoInstalacoes;

    @NonNull
    public int avaliacaoCondicoesArmazenamento;

    @NonNull
    @ColumnInfo(name = "observacaoVestigiosPragas", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean observacaoVestigiosPragas;

    @NonNull
    @ColumnInfo(name = "observacaoProdutosEmGel", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean observacaoProdutosEmGel;

    public String observacao;
}
