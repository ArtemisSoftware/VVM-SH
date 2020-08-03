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


    public DadosFormularios() {

        email = new EmailInfo();
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



    public void fixarEmail(Email email){
        this.email.dados.add(email);
    }


    public void fixarAnomalias(List<Anomalia> anomalias){
        this.anomaliasCliente.dados = anomalias;
    }

}
