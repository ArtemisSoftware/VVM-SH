package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

public class IDados {

    @SerializedName("Ordem")
    public String ordem;

    @SerializedName("NrCliente")
    public String numeroCliente;

    @SerializedName("ServicoTP")
    public String servicoTp;

    @SerializedName("Servico")
    public String servico;

    @SerializedName("Minutos")
    public String minutos;

    @SerializedName("UltVisita")
    public String ultimaVisita;

    @SerializedName("Contrato")
    public String contrato;

    @SerializedName("DtContrato")
    public String dataContrato;

    @SerializedName("Novo")
    public String novo;

    @SerializedName("PrefixoCT")
    public String prefixoCt;

    @SerializedName("DtInsercao")
    public String dataInsercao;

    @SerializedName("MinutosRealizados")
    public String minutosRealizados;

    @SerializedName("Periodo")
    public String periodo;

    @SerializedName("TipoPacote")
    public String tipoPacote;

    @SerializedName("AnuidadeContrato")
    public String anuidadeContrato;



}
