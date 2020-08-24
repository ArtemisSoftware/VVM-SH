package com.vvm.sh.ui.opcoes.modelos;

import com.vvm.sh.util.metodos.DatasUtil;

public class Colecao /*extends Item*/ {

    private int numeroRegistos;
    private String seloTemporal;
    private String descricao;

    public Colecao(String descricao, int numeroRegistos, String seloTemporal) {
        //super(-1, descricao);
        this.descricao = descricao;
        this.numeroRegistos = numeroRegistos;
        this.seloTemporal = DatasUtil.converterData(seloTemporal, DatasUtil.FORMATO_DD_MM_YYYY__HH_MM_SS);
    }


    /**
     * Metodo que permite obter a descricao
     * @return uma descricao
     */
    public String obterDescricao() {
        return descricao;
    }


    /**
     * Metodo que permite obter o numero de registos
     * @return o numero de registos
     */
    public int obterNumeroRegistos(){
        return numeroRegistos;
    }


    /**
     * Metodo que permite obter a dados da ultima atualização
     * @return uma dados (DD_MM_YYYY__HH_MM_SS)
     */
    public String obterSeloTemporal(){
        return seloTemporal;
    }
}
