package com.vvm.sh.ui.apresentacao.modelos;

public class Introducao {

    public String titulo;
    public int imagem, corFundo;
    public String texto;
    public int tipo;


    public Introducao(String titulo, String texto, int imagem){

        this.imagem = imagem;
        this.texto = texto;
        this.titulo = titulo;
    }

}
