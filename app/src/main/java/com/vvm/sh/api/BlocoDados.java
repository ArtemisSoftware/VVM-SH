package com.vvm.sh.api;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Equipamento;
import com.vvm.sh.util.mapeamento.UploadMapping;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class BlocoDados {

    @SerializedName("Dados_Formularios")
    public List<DadosFormulario> dadosFormulario;

    @SerializedName("Novas_Maquinas")
    public List<Equipamento> novasMaquinas;

    @SerializedName("versaoApp")
    public String versaoApp;

    @SerializedName("ficheiroImagens")
    public int numeroFicheiroImagens;



}
