package com.vvm.sh.autenticacao;

public class Utilizador {

    private String id, nome, cap;


    public Utilizador (String id, String nome, String cap){
        this.id = id;
        this.nome = nome;
        this.cap = cap;
    }



    /**
     * Metodo que devolve o identificador do utilizador
     * @return o identificador
     */
    public String obterId(){
        return id;
    }


    /**
     * Metodo que devolve o nome do utilizador
     * @return o nome
     */
    public String obterNome(){
        return nome;
    }


    /**
     * Metodo que devolve o cap do utilizador
     * @return o cap
     */
    public String obterCap(){
        return cap;
    }


    /**
     * Metodo que permite obter as inicias do utilizador
     * @return as iniciais
     */
    public  String obterIniciais(){
        return nome.replaceAll("^\\s*([a-zA-Z]).*\\s+([a-zA-Z])\\S+$", "$1$2").toUpperCase();
    }
}
