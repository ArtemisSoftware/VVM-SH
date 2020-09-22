package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ITipoChecklist {

    @SerializedName("checklist")
    public int id;

    @SerializedName("description")
    public String descricao;

    @SerializedName("versao")
    public String versao;


    @SerializedName("areas")
    public List<IArea> areas;



    public class IArea{

        @SerializedName("id")
        public int id;

        @SerializedName("area")
        public String descricao;


        @SerializedName("seccoes")
        public List<ISeccao> seccoes;

    }


    public class ISeccao{

        @SerializedName("uid")
        public String uid;

        @SerializedName("type")
        public String tipo;

        @SerializedName("label")
        public String descricao;

        @SerializedName("items")
        public List<IItem> items;

    }


    public class IItem{

        @SerializedName("uid")
        public String uid;

        @SerializedName("type")
        public String tipo;


        @SerializedName("code")
        public String codigo;

        @SerializedName("label")
        public String descricao;

    }

}
