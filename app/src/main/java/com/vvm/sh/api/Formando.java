package com.vvm.sh.api;

import com.google.gson.annotations.SerializedName;

public class Formando {


    //TODO: completar o genero e o album

//
//					if(cursor.getString(cursor.getColumnIndex("sexo")).equals(SintaxeIF.F) || cursor.getString(cursor.getColumnIndex("sexo")).equals(SintaxeIF.FEMININO)){
//        formando.put(JsonEnvioIF.GENERO, SintaxeIF.F);
//    }
//					else{
//        formando.put(JsonEnvioIF.GENERO, SintaxeIF.M);
//    }

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
}
