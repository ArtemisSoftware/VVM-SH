package com.vvm.sh.ui.cliente;

public class Sinistralidade {

    private String acidentesComBaixa, diasUteis, totalTrabalhadores, horasAnoTrabalhadores, faltas;

    public Sinistralidade(String acidentesComBaixa, String diasUteis, String totalTrabalhadores, String horasAnoTrabalhadores, String faltas) {

        this.acidentesComBaixa = acidentesComBaixa;
        this.diasUteis = diasUteis;
        this.totalTrabalhadores = totalTrabalhadores;
        this.horasAnoTrabalhadores = horasAnoTrabalhadores;
        this.faltas = faltas;
    }

    /**
     * Metodo que permite obter o numero de acidentes com baixa
     * @return o numero de acidentes com baixa
     */
    public String obterAcidentesComBaixa() {
        return acidentesComBaixa;
    }


    /**
     * Metodo que permite obter o numero de dias uteis
     * @return o numero de dias uteis
     */
    public String obterDiasUteis() {
        return diasUteis;
    }


    /**
     * Metodo que permite obter o total de trabalhadores
     * @return o total sde trabalhadores
     */
    public String obterTotalTrabalhadores() {
        return totalTrabalhadores;
    }


    /**
     * Metodo que permite obter as horas / ano / trabalhadores
     * @return as horas / ano / trabalhadores
     */
    public String obterHorasAnoTrabalhadores() {
        return horasAnoTrabalhadores;
    }


    /**
     * Metodo que permite obter as faltas
     * @return o numero de faltas
     */
    public String obterFaltas() {
        return faltas;
    }
}
