package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CertificadoTratamento {




    @SerializedName("observacaoProdutosEmGel")
    public String observacaoProdutosEmGel;

    @SerializedName("observacaoVestigiosPragas")
    public String observacaoVestigiosPragas;

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

    @SerializedName("praga")
    public String praga;

    @SerializedName("Album")
    public ArrayList<String> album;


    String CERTIFICADO_TRATAMENTO = "certificadoTratamento";

}
