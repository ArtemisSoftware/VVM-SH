package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AvaliacaoIluminacao {

    @SerializedName("idArea")
    public String idArea;

    @SerializedName("descricaoArea")
    public String descricaoArea;

    @SerializedName("idCategoriasProfissionais")
    public List<Integer> categoriasProfissionais;

    @SerializedName("idsMedidasRecomendadas")
    public List<Integer> medidasRecomendadas;




    @SerializedName("idTipoIluminacao")
    public String idTipoIluminacao;

    @SerializedName("emedioElx")
    public String emedioElx;

    @SerializedName("idElx")
    public String idElx;

    @SerializedName("nome")
    public String nome;

    @SerializedName("sexo")
    public String sexo;


}
