package com.vvm.sh.ui.cliente.extintores;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.metodos.Conversor;
import com.vvm.sh.util.metodos.Datas;

public class Extintor extends Item /*ItemFormulario*/{

    //TODO: esta classe é formulario -> deve indicar se os dados são validos

    private String quantidade, endereco, dataValidade;
    private boolean novaDataValidade;


    public Extintor(int id, String descricao, String quantidade, String endereco, String dataValidade, int novaDataValidade, int valido) {
        super(id, descricao/*, valido*/);

        this.dataValidade = dataValidade;

        this.novaDataValidade = Conversor.converter_Integer_Para_Boolean(novaDataValidade);
        this.endereco = endereco;
        this.quantidade = quantidade;
    }


    /**
     * Metodo que indica se existe uma nova data de validade
     * @return true caso exista ou false caso contrario
     */
    public boolean obterEstadoNovaDataValidade(){
        return novaDataValidade;
    }



    /**
     * Metodo que permite obter a data de validade
     * @return a data (dd-MM-yyyy)
     */
    public String obterDataValidade(){
        return Datas.converterData(this.dataValidade, Datas.DATA_FORMATO_DD_MM_YYYY);
    }



    /**
     * Metodo que permite obter o endereco
     * @return o endereco
     */
    public String obterEndereco(){
        return endereco;
    }

    /**
     * Metodo que permite obter a quantidade
     * @return a quantidade
     */
    public String obterQuantidade(){
        return quantidade;
    }
}