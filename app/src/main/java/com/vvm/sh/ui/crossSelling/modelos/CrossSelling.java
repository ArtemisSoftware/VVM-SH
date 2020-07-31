package com.vvm.sh.ui.crossSelling.modelos;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.CheckBoxIF;
import com.vvm.sh.util.metodos.Conversor;

public class CrossSelling  {


    public int idTarefa;
    public int id;
    public String descricao, dimensao, tipo;
    public int idAreaRecomendacao;
    public int idDimensao;
    public int idTipo;

    public CrossSelling(int idTarefa, int id, String descricao, int idAreaRecomendacao, int idDimensao, String dimensao, int idTipo, String tipo) {
        this.idTarefa = idTarefa;
        this.id = id;
        this.descricao = descricao;
        this.idAreaRecomendacao = idAreaRecomendacao;
        this.idDimensao = idDimensao;
        this.dimensao = dimensao;
        this.idTipo = idTipo;
        this.tipo = tipo;
    }


    /**
     * Metodo que indica se o registo foi selecionado
     * @return true caso tenha sido selecionado ou false caso contrário
     */
    public boolean obterSelecao() {
        if(idAreaRecomendacao == 0){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     * Metodo que indica se o registo possui sinaletica
     * @return true caso possua ou false caso contrário
     */
    public boolean possuiSinaletica() {
        if(idDimensao == 0 & idTipo == 0){
            return false;
        }
        else{
            return true;
        }
    }


}
