package com.vvm.sh.apresentacao.modelos;

public class Introducao {


    public static final int TIPO_ATUALIZACAO = 1;
    public static String ATUALIZACAO = "Atualização";

    public static final int TIPO_CORRECAO = 2;
    public static String CORRECAO = "Correção";

    public static final int TIPO_FUNCIONALIDADE = 3;
    public static String FUNCIONALIDADE = "Funcionalidade";



    public String titulo;
    public int imagem, corFundo;
    public String texto;
    public int tipo;

    public Introducao(int tipo, String titulo, String texto){

        this.tipo = tipo;
        this.texto = texto;
        this.titulo = titulo;
    }

    public Introducao(String titulo, String texto, int imagem){

        this.imagem = imagem;
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

    /**
     * Metodo que permite obter a cor de fundo da introducao
     * @return uma cor
     */
    public int obterCorFundo() {
        return corFundo;
    }
}
