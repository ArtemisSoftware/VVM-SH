package com.vvm.sh.ui.crossSelling.modelos;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.CheckBoxIF;
import com.vvm.sh.util.metodos.Conversor;

public class CrossSelling extends Item implements CheckBoxIF {

    private String dimensao, tipo;
    private boolean selecionado;

    public CrossSelling(int id, String descricao, String dimensao, String tipo, int selecionado) {
        super(id, descricao);

        this.dimensao = dimensao;
        this.tipo = tipo;
        this.selecionado = Conversor.converter_Integer_Para_Boolean(selecionado);
    }


    /**
     * Metodo que permite obter a dimensao
     * @return a dimensao
     */
    public String obterDimensao() {
        return dimensao;
    }


    /**
     * Metodo que permite obter o tipo
     * @return o tipo
     */
    public String obterTipo() {
        return tipo;
    }


    /**
     * Metodo que inidica se existe detalhe de sinalietica (tipo + dimensao)
     * @return true caso exista ou false caso contrario
     */
    public boolean existeSinaletica(){

        if(dimensao == null){
            return false;
        }

        return true;
    }

    @Override
    public void fixarSelecao(boolean selecao) {
        this.selecionado = selecao;
    }

    @Override
    public boolean obterSelecao() {
        return selecionado;
    }
}
