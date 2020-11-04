package com.vvm.sh.ui.transferencias.modelos;

import com.google.gson.Gson;
import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.BuildConfig;
import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Equipamento;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.util.mapeamento.UploadMapping;

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


    public void lolo(){
        Gson gson = new Gson();
        BlocoDados registoDados = UploadMapping.INSTANCE.map(this);
        String dados = gson.toJson(registoDados);






        String messageDigest = Hasher.Companion.hash(dados, HashType.MD5);


    }




    public void removeDetails() {
        JsonElement items = data.get("items");
        if (!items.isJsonArray()) {
            return;
        }
        JsonArray array = items.getAsJsonArray();
        array.forEach(item -> {
            if (item.isJsonObject()) {
                JsonObject node = item.getAsJsonObject();
                JsonElement view = node.get("view");
                if (view.isJsonObject()) {
                    view.getAsJsonObject().remove("details");
                }
            }
        });
    }
}
