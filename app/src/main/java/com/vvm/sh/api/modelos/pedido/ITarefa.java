package com.vvm.sh.api.modelos.pedido;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    public List<IAtividadeExecutada> atividadesExecutadas = new ArrayList<>();

    @SerializedName("Ocorrencias")
    public List<IOcorrencia> ocorrencias = new ArrayList<>();


    @SerializedName("Anomalias")
    public List<IAnomalia> anomalias = new ArrayList<>();

    @SerializedName("ActivPendentes")
    public List<IAtividadePendente> atividadesPendentes = new ArrayList<>();


    @SerializedName("Dados")
    public IDados dados;

    @SerializedName("Cliente")
    public ICliente cliente;



    @SerializedName("Moradas")
    public List<IMorada> moradas = new ArrayList<>();

    @SerializedName("ParqueExtintores")
    public List<IParqueExtintor> parqueExtintores = new ArrayList<>();

    @SerializedName("MoradasExtintores")
    public List<IMorada> moradasExtintores = new ArrayList<>();

    @SerializedName("TiposExtintor")
    public List<ITipoExtintor> tiposExtintor = new ArrayList<>();

    @SerializedName("QuadroPessoal")
    public List<IColaborador> quadroPessoal = new ArrayList<>();
//
//    @SerializedName("AvaliacaoRiscosAnterior")
//    public List<IAvaliacaoRiscosAnterior> avaliacaoRiscosAnterior;

    @SerializedName("Plano_Accao")
    public IPlanoAcao planoAcao;

    @SerializedName("RelatorioAvaliacaoRisco")
    public IRelatorioAvaliacaoRiscos relatorioAvaliacaoRiscos;

}
