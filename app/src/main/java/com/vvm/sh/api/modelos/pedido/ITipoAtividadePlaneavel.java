package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

public class ITipoAtividadePlaneavel {


    @SerializedName("Codigo")
    public String codigo;

    @SerializedName("ServID")
    public String servId;


    @SerializedName("Descricao")
    public String descricao;

    @SerializedName("DescricaoCompleta")
    public String descricaoCompleta;


    @SerializedName("Equipa")
    public String equipa;

    @SerializedName("SempreNecessario")
    public int sempreNecessario;


    @SerializedName("Ordem")
    public int ordem;



    @SerializedName("IsRelatorio")
    public int relatorio;


    @SerializedName("OBS")
    public String observacao;

    @SerializedName("Activo")
    public int ativo;



}
