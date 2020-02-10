package com.vvm.sh.util;

public abstract class Introducao {


    public static final int TIPO_ATUALIZACAO = 1;
    public static String ATUALIZACAO = "Atualização";

    public static final int TIPO_CORRECAO = 2;
    public static String CORRECAO = "Correção";

    public static final int TIPO_FUNCIONALIDADE = 3;
    public static String FUNCIONALIDADE = "Funcionalidade";



    private String titulo;
    protected int imagem, corFundo;
    protected String texto;
    private int tipo;

    public Introducao(int tipo, String titulo, String texto){

        this.tipo = tipo;
        this.texto = texto;
        this.titulo = titulo;
    }


    public int obterTipo() {
        return tipo;
    }

    public String obterTexto() {
        return texto;
    }

    public String obterTitulo() {
        return titulo;
    }

    public int obterImagem() {
        return imagem;
    }

    public int obterCorFundo() {
        return corFundo;
    }
}
