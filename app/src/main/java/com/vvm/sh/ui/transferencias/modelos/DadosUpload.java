package com.vvm.sh.ui.transferencias.modelos;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Imagem;

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

    public DadosUpload(String idUtilizador) {

        this.idBloco = 0;
        this.dados = new ArrayList<>();
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




}
