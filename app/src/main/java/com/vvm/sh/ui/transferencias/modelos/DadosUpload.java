package com.vvm.sh.ui.transferencias.modelos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.BuildConfig;
import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Equipamento;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.mapeamento.UploadMapping;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DadosUpload {

    public int idBloco;
    public String idUtilizador;
    public String versao;

    public String idUpload;

    public HashMap<Integer, List<Imagem>> imagens;
    public List<DadosFormulario> dados;
    public List<Equipamento> equipamentos;




    private String upload;
    public String messageDigest;

    public DadosUpload(String idUtilizador) {

        this.idBloco = 0;
        this.dados = new ArrayList<>();
        this.equipamentos = new ArrayList<>();
        this.imagens = new HashMap<>();
        this.idUtilizador = idUtilizador;
        this.idUpload = UUID.randomUUID().toString();

        this.versao = BuildConfig.VERSION_NAME;
    }

    public void fixarDados(DadosFormulario dado) {
        this.dados.add(dado);
    }


    /**
     * Metodo que permite fixar um bloco de imagens
     * @param imagens a lista de imagens
     */
    public void fixarImagens(List<Imagem> imagens) {
        this.imagens.put(idBloco, imagens);
        ++idBloco;
    }


    public void formatarDados(){


        if(AppConfig.sa == false) { //TODO:só para sa


                for (int index = 0; index < dados.size(); ++index) {
                    dados.get(index).numeroFicheirosFotos = idBloco + "";
                    dados.get(index).versaoApp = "1.42.20"; //TODO:só para sa
                }


        }

        Gson gson = new Gson();
        BlocoDados registoDados = UploadMapping.INSTANCE.map(this);
        String dados = gson.toJson(registoDados);


        try {

            JsonObject data = new Gson().fromJson(dados, JsonObject.class);
            removeDetails(data);

            upload = data.toString();
            messageDigest = Hasher.Companion.hash(upload, HashType.MD5);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    public void removeDetails(JsonObject data) throws JSONException {
        JsonElement items =  data.get("Dados_Formularios");
        if (!items.isJsonArray()) {
            return;
        }

        JsonArray Dados_Formularios = items.getAsJsonArray();


        for(int index = 0; index < Dados_Formularios.size(); ++index){

            JsonObject dado = Dados_Formularios.get(index).getAsJsonObject();

            if(dado.get("extintores").getAsJsonObject().size() == 0){
                dado.remove("extintores");
            }

            if(dado.get("RegistoVisita").getAsJsonObject().size() == 0){
                dado.remove("RegistoVisita");
            }

            if(dado.get("Sinistralidade").getAsJsonObject().size() == 0){
                dado.remove("Sinistralidade");
            }





            if(dado.get("RegistoAnomalias").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("RegistoAnomalias");
            }

            if(dado.get("ActividadesPendentes").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("ActividadesPendentes");
            }

            if(dado.get("RelatorioAvaliacaoRiscos").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("RelatorioAvaliacaoRiscos");
            }

            if(dado.get("CrossSelling").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("CrossSelling");
            }

            if(dado.get("RegistoOcorrencias").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("RegistoOcorrencias");
            }

            if(dado.get("PlanoAccao").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("PlanoAccao");
            }

            if(dado.get("QuadroPessoal").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("QuadroPessoal");
            }

            if(dado.get("email").getAsJsonObject().get("dados").getAsJsonArray().size() == 0){
                dado.remove("email");
            }
        }
    }

    public String obterDados() {
        return upload;
    }
}
