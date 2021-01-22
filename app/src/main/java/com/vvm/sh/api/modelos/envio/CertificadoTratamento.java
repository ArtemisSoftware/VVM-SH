package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CertificadoTratamento {

    @SerializedName("observacaoProdutosEmGel")
    public int observacaoProdutosEmGel;

    @SerializedName("observacaoVestigiosPragas")
    public int observacaoVestigiosPragas;

    @SerializedName("praga")
    public String praga;

    @SerializedName("avaliacaoCondicoesHigiene")
    public String avaliacaoCondicoesHigiene;

    @SerializedName("avaliacaoManutencaoInstalacoes")
    public String avaliacaoManutencaoInstalacoes;

    @SerializedName("avaliacaoCondicoesArmazenamento")
    public String avaliacaoCondicoesArmazenamento;

    @SerializedName("produtoAplicado")
    public String produtoAplicado;

    @SerializedName("visita")
    public String visita;

    @SerializedName("observacao")
    public String observacao;

    @SerializedName("Album")
    public ArrayList<String> album;


}
