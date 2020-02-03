package com.vvm.sh.apresentacao.modelos;

public class Slider {

    private String texto;
    private int corAtiva, corInativa, corFundo;
    private int conteudo;

    public Slider(String text, int corAtiva, int corInativa, int conteudo, int corFundo) {
        this.texto = text;
        this.corAtiva = corAtiva;
        this.corInativa = corInativa;
        this.conteudo = conteudo;
        this.corFundo = corFundo;
    }

    public String obterTexto() {
        return texto;
    }

    public int obterCorFundo() {
        return corFundo;
    }

    public int obterCorAtiva() {
        return corAtiva;
    }

    public int obterCorInaAtiva() {
        return corInativa;
    }

    public int obterConteudo() {
        return conteudo;
    }

}
