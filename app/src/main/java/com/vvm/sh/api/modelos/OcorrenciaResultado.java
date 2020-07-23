package com.vvm.sh.api.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Classe que representa uma ocorrencia do web service
 */
public class OcorrenciaResultado {

    @SerializedName("ID")
    public String idOcorrencia;


    @SerializedName("Tip")
    public String tipo;


    @SerializedName("DescTip")
    public String descricaoTipo;


    @SerializedName("DescDep")
    public String descricaoDepartamento;


    @SerializedName("Contrato")
    public String contrato;


    @SerializedName("DataEntrada")
    public String dataEntrada;


    @SerializedName("DataResol")
    public String dataResolucao;


    @SerializedName("DescOcorrencia")
    public String descricaoOcorrencia;


    @SerializedName("Marca")
    public String marca;


    @SerializedName("Estado")
    public String estado;


    @SerializedName("Historico")
    public List<OcorrenciaHistoricoResultado> historico;



    public class OcorrenciaHistoricoResultado{

        @SerializedName("Data")
        public String data;


        @SerializedName("Estado")
        public String estado;


        @SerializedName("Obs")
        public String observacao;


        @SerializedName("Dep")
        public String departamento;

    }

}
