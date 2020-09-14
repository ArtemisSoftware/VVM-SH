package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IRelatorioAvaliacaoRiscos {

    @SerializedName("Data")
    public String data;


    @SerializedName("CategoriasProfissionais")
    public List<ICategoriaProfissional> categoriasProfissionais;


    public class ICategoriaProfissional{

        @SerializedName("Descricao")
        public String descricao;

        @SerializedName("Medidas")
        public String medidas;

    }
}
