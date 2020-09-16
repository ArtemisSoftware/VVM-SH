package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ITipoTemplateAvrRisco {

    @SerializedName("Id")
    public int id;

    @SerializedName("idRisco")
    public int idRisco;

    @SerializedName("idRiscoEspecifico")
    public int idRiscoEspecifico;

    @SerializedName("Consequencias")
    public String consequencias;

    @SerializedName("Activo")
    public int ativo;

    @SerializedName("ParentId")
    public String idPai;

    @SerializedName("MedidasExistentes")
    public List<Integer> medidasExistentes;

    @SerializedName("MedidasRecomendadas")
    public List<Integer> medidasRecomendadas;

}
