package com.vvm.sh.ui.transferencias.modelos;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.api.DadosFormularios;
import com.vvm.sh.api.Imagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DadosUpload {

    private int idBloco;
    public String versao;
    public HashMap<Integer, List<Imagem>> imagens;
    public List<DadosFormularios> dados;

    public DadosUpload() {

        this.idBloco = 0;
        this.dados = new ArrayList<>();
        this.imagens = new HashMap<>();

        this.versao = BuildConfig.VERSION_NAME;
    }

    public void fixarDados(DadosFormularios dado) {
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
