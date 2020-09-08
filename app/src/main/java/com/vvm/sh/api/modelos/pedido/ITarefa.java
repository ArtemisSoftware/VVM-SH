package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Classe que representa uma tarefa proveniente do web service
 */
public class ITarefa {

    @SerializedName("SaldoCartaoVM")
    public String saldoCartaoVm;


    @SerializedName("Notas")
    public String notas;

    @SerializedName("ActivExecutadas")
    public List<IAtividadeExecutada> atividadesExecutadas;

    @SerializedName("Ocorrencias")
    public List<IOcorrencia> ocorrencias;


    @SerializedName("Anomalias")
    public List<IAnomalia> anomalias;

    @SerializedName("ActivPendentes")
    public List<IAtividadePendente> atividadesPendentes;


    @SerializedName("Dados")
    public IDados dados;

    @SerializedName("Cliente")
    public ICliente cliente;


    @SerializedName("ParqueExtintores")
    public List<IParqueExtintor> parqueExtintores;

    @SerializedName("MoradasExtintores")
    public List<IMoradaExtintor> moradasExtintores;

    @SerializedName("TiposExtintor")
    public List<ITipoExtintor> tiposExtintor;
}
