package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

public class DadosFormularios {


    @SerializedName("email")
    public EmailInfo email;


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

    public void fixarEmail(Email email){
        this.email.dados.add(email);
    }

}
