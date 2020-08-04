package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

public class DadosFormularios {


    @SerializedName("email")
    public EmailInfo email;

    @SerializedName("RegistoAnomalias")
    public AnomaliasClienteInfo anomaliasCliente;

    @SerializedName("CrossSelling")
    public CrossSellingInfo crossSelling;

    @SerializedName("RegistoOcorrencias")
    public OcorrenciasInfo ocorrencias;





    public DadosFormularios() {

        email = new EmailInfo();
        anomaliasCliente = new AnomaliasClienteInfo();
        crossSelling = new CrossSellingInfo();
        ocorrencias = new OcorrenciasInfo();
    }

    public class EmailInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Email> dados;


        public EmailInfo() {

            this.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
            this.dados = new ArrayList<>();
        }
    }



    public class AnomaliasClienteInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Anomalia> dados;


        public AnomaliasClienteInfo() {

            this.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
            this.dados = new ArrayList<>();
        }
    }



    public class CrossSellingInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<CrossSelling> dados;


        public CrossSellingInfo() {

            this.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
            this.dados = new ArrayList<>();
        }
    }



    public class OcorrenciasInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Ocorrencia> dados;


        public OcorrenciasInfo() {

            this.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
            this.dados = new ArrayList<>();
        }
    }



    public void fixarEmail(Email email){
        this.email.dados.add(email);
    }

    public void fixarAnomalias(List<Anomalia> anomalias){
        this.anomaliasCliente.dados = anomalias;
    }

    public void fixarCrossSelling(List<CrossSelling> crossSellings){
        this.crossSelling.dados = crossSellings;
    }

    public void fixarOcorrencias(List<Ocorrencia> ocorrencias){
        this.ocorrencias.dados = ocorrencias;
    }
}
