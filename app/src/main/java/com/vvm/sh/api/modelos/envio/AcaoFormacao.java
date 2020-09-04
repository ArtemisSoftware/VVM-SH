package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class AcaoFormacao {

    @SerializedName("areaFormacao")
    public String areaFormacao = Identificadores.Estados.ST;

    @SerializedName("idDesignacao")
    public String idDesignacao;

    @SerializedName("local")
    public String local;

    @SerializedName("data")
    public String data;

    @SerializedName("inicio")
    public String inicio;

    @SerializedName("termino")
    public String termino;

    @SerializedName("formandos")
    public List<Formando> formandos;


}
