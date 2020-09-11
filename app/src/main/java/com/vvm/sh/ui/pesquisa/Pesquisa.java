package com.vvm.sh.ui.pesquisa;

public class Pesquisa {

    public boolean escolhaMultipla;
    public String metodo;

    public Pesquisa(String metodo) {
        this.escolhaMultipla = false;
        this.metodo = metodo;
    }

    public Pesquisa(boolean escolhaMultipla, String metodo) {
        this.escolhaMultipla = escolhaMultipla;
        this.metodo = metodo;
    }
}
