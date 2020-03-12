package com.vvm.sh.servicos;

import java.util.ArrayList;
import java.util.List;

public class Datagrama {

    private String url;
    private List<String> nomes, valores;
    //private String idTabela, nomeTabela;

    public Datagrama (String urlBase, String urlRelativo){

        /*
        if(url.contains(WebServiceComIF.URL_SHT_PRODUCAO) == false && url.contains(WebServiceComIF.URL_CONTRATOS_PRODUCAO) == false) {
            url = WebServiceComIF.URL_SHT_PRODUCAO + url;
        }
        */

        this.url = urlBase + urlRelativo;
        nomes = new ArrayList<>();
        valores = new ArrayList<>();
    }


    /**
     * Metodo que devolve o url
     * @return o url
     */
    public String obterUrl(){
        return url;
    }


    /**
     * Metodo que permite adicionar parametros ao datagrama
     * @param nome nome do parametro
     * @param valor valor do parametro
     */
    public void adicionarParametros(String nome, String valor){

        nomes.add(nome);
        valores.add(valor);
    }


    /**
     * Metodo que permite obter um parametro numa determinada posicao
     * @param posicao a posicao do parametro
     * @return um duplo [nome do parametro, valor do parametro]
     */
    public String [] obterParametro(int posicao){

        String parametros [] = { nomes.get(posicao), valores.get(posicao) };
        return parametros;
    }


    /**
     * Metodo que permite saber o numero de parametros existentes associados ao datagrama
     * @return o numero de parametros
     */
    public int numeroParametros(){
        return nomes.size();
    }

}
