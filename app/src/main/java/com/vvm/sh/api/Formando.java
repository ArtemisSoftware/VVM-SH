package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Formando {


    //TODO: completar o album



//					jAlbum.put(cursor.getInt(cursor.getColumnIndex("idRegisto")));
//					formando.put(JsonEnvioIF.ALBUM, jAlbum);


    @SerializedName("nome")
    public String nome;

    @SerializedName("biCartaoCidadao")
    public String biCartaoCidadao;

    @SerializedName("niss")
    public String niss;

    @SerializedName("dataNascimento")
    public String dataNascimento;

    @SerializedName("nacionalidade")
    public String nacionalidade;

    @SerializedName("naturalidade")
    public String naturalidade;

    @SerializedName("genero")
    public String genero;

    @SerializedName("Album")
    public ArrayList<String> album;
}
