package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "certificadoTratamentoResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class CertificadoTratamentoResultado {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


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


    @NonNull
    @ColumnInfo(name = "sincronizacao", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean sincronizacao;


    @Ignore
    public CertificadoTratamentoResultado(int idAtividade,
                                          int idPraga, int idVisita, int idProdutoAplicado,
                                          int avaliacaoCondicoesHigiene, int avaliacaoManutencaoInstalacoes, int avaliacaoCondicoesArmazenamento,
                                          boolean observacaoVestigiosPragas, boolean observacaoProdutosEmGel,
                                          String observacao) {
        this.idAtividade = idAtividade;
        this.idPraga = idPraga;
        this.idVisita = idVisita;
        this.idProdutoAplicado = idProdutoAplicado;
        this.avaliacaoCondicoesHigiene = avaliacaoCondicoesHigiene;
        this.avaliacaoManutencaoInstalacoes = avaliacaoManutencaoInstalacoes;
        this.avaliacaoCondicoesArmazenamento = avaliacaoCondicoesArmazenamento;
        this.observacaoVestigiosPragas = observacaoVestigiosPragas;
        this.observacaoProdutosEmGel = observacaoProdutosEmGel;
        this.observacao = observacao;
    }


    public CertificadoTratamentoResultado(int id, int idAtividade,
                                          int idPraga, int idVisita, int idProdutoAplicado,
                                          int avaliacaoCondicoesHigiene, int avaliacaoManutencaoInstalacoes, int avaliacaoCondicoesArmazenamento,
                                          boolean observacaoVestigiosPragas, boolean observacaoProdutosEmGel,
                                          String observacao, boolean sincronizacao) {

        this.id = id;
        this.idAtividade = idAtividade;
        this.idPraga = idPraga;
        this.idVisita = idVisita;
        this.idProdutoAplicado = idProdutoAplicado;
        this.avaliacaoCondicoesHigiene = avaliacaoCondicoesHigiene;
        this.avaliacaoManutencaoInstalacoes = avaliacaoManutencaoInstalacoes;
        this.avaliacaoCondicoesArmazenamento = avaliacaoCondicoesArmazenamento;
        this.observacaoVestigiosPragas = observacaoVestigiosPragas;
        this.observacaoProdutosEmGel = observacaoProdutosEmGel;
        this.observacao = observacao;
        this.sincronizacao = sincronizacao;
    }
}
