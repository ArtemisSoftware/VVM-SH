package com.vvm.sh.ui.contaUtilizador;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.metodos.Datas;

public class Colecao extends Item {

    private int numeroRegistos;
    private String data;

    public Colecao(String descricao, int numeroRegistos, String data) {
        super(-1, descricao);

        this.numeroRegistos = numeroRegistos;
        this.data = Datas.converterData(data, Datas.DATA_FORMATO_DD_MM_YYYY__HH_MM_SS);
    }


    /**
     * Metodo que permite obter o numero de registos
     * @return o numero de registos
     */
    public int obterNumeroRegistos(){
        return numeroRegistos;
    }


    /**
     * Metodo que permite obter a data da ultima atualização
     * @return uma data (DD_MM_YYYY__HH_MM_SS)
     */
    public String obterData(){
        return data;
    }
}
