package com.vvm.sh.ui.atividadesPendentes;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.CheckBoxIF;
import com.vvm.sh.util.metodos.Conversor;

public class Formando extends Item implements CheckBoxIF {

    private boolean selecionado;
    public String biCartaoCidadao;
    private String niss;
    private int origem;


    public String nome;


    public Formando(int id, String nome, String biCartaoCidadao, String niss, int origem, int selecionado, int valido) {
        super(id, nome/*, valido*/);
        this.selecionado = Conversor.converter_Integer_Para_Boolean(selecionado);

        this.biCartaoCidadao = biCartaoCidadao;
        this.niss = niss;
        this.origem = origem;
    }


    /**
     * Metodo que permite obter a identificacao
     * @return a identificacao
     */
    public String obterBiCartaoCidadao() {
        return biCartaoCidadao;
    }


    /**
     * Metodo que permite obter o niss
     * @return o niss
     */
    public String obterNiss() {
        return niss;
    }

    @Override
    public void fixarSelecao(boolean selecao) {
        this.selecionado = selecao;
    }


    /**
     * Metodo que permite obter a origem do registo
     * @return a origem
     */
    public int obterOrigem(){
        return origem;
    }

    @Override
    public boolean obterSelecao() {
        return selecionado;
    }
}
