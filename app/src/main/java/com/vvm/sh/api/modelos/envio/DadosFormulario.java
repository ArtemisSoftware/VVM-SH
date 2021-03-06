package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

public class DadosFormulario {

    @SerializedName("idUtilizador")
    public String idUtilizador;


    @SerializedName("id")
    public Sessao id;


    @SerializedName("email")
    public EmailInfo email;

    @SerializedName("RegistoAnomalias")
    public AnomaliasClienteInfo anomaliasCliente;

    @SerializedName("CrossSelling")
    public CrossSellingInfo crossSelling;

    @SerializedName("RegistoOcorrencias")
    public OcorrenciasInfo ocorrencias;

    @SerializedName("ActividadesPendentes")
    public AtividadesPendentesInfo atividadesPendentes;


    //-sht

    @SerializedName("versaoApp")
    public String versaoApp;

    @SerializedName("numeroFicheirosFotos")
    public String numeroFicheirosFotos;

    @SerializedName("RelatorioAvaliacaoRiscos")
    public AveriguacaoInfo averiguacao;

    @SerializedName("Sinistralidade")
    public Sinistralidade sinistralidade;

    @SerializedName("PlanoAccao")
    public AtividadePlanoAcaoInfo planoAcao;

    @SerializedName("QuadroPessoal")
    public QuadroPessoalInfo quadroPessoal;

    @SerializedName("RegistoVisita")
    public RegistoVisita registoVisita;

    @SerializedName("extintores")
    public ParqueExtintor parqueExtintor;



    public DadosFormulario() {

        email = new EmailInfo();
        anomaliasCliente = new AnomaliasClienteInfo();
        crossSelling = new CrossSellingInfo();
        ocorrencias = new OcorrenciasInfo();
        atividadesPendentes = new AtividadesPendentesInfo();
        averiguacao = new AveriguacaoInfo();

        sinistralidade = new Sinistralidade();
        planoAcao = new AtividadePlanoAcaoInfo();
        quadroPessoal = new QuadroPessoalInfo();
        registoVisita = new RegistoVisita();
        parqueExtintor = new ParqueExtintor();
    }



    public class EmailInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Email> dados;


        public EmailInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class AnomaliasClienteInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Anomalia> dados;

        public AnomaliasClienteInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class CrossSellingInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<CrossSelling> dados;

        public CrossSellingInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class OcorrenciasInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Ocorrencia> dados;

        public OcorrenciasInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class AtividadesPendentesInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<AtividadePendente> dados;

        public AtividadesPendentesInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class AtividadePlanoAcaoInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<AtividadePlanoAcao> dados;

        public AtividadePlanoAcaoInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class QuadroPessoalInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<Colaborador> dados;

        public QuadroPessoalInfo() {
            this.dados = new ArrayList<>();
        }
    }

    public class AveriguacaoInfo{

        @SerializedName("dataRegisto")
        public String dataRegisto;

        @SerializedName("dados")
        public List<RelatorioAveriguacao> dados;

        public AveriguacaoInfo() {
            this.dados = new ArrayList<>();
        }
    }



    public void fixarEmail(Email email){
        this.email.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.email.dados.add(email);
    }

    public void fixarAnomalias(List<Anomalia> anomalias){
        this.anomaliasCliente.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.anomaliasCliente.dados = anomalias;
    }

    public void fixarCrossSelling(List<CrossSelling> crossSellings){
        this.crossSelling.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.crossSelling.dados = crossSellings;
    }

    public void fixarOcorrencias(List<Ocorrencia> ocorrencias){
        this.ocorrencias.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.ocorrencias.dados = ocorrencias;
    }

    public void fixarAtividadesPendentes(List<AtividadePendente> atividadesPendentes) {
        this.atividadesPendentes.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.atividadesPendentes.dados = atividadesPendentes;
    }

    public void fixarAtividadePlanoAcao(List<AtividadePlanoAcao> atividadesPlano) {
        this.planoAcao.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.planoAcao.dados = atividadesPlano;
    }


    public void fixarQuadroPessoal(List<Colaborador> colaboradores) {
        this.quadroPessoal.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.quadroPessoal.dados = colaboradores;
    }

    public void fixarAveriguacao(List<RelatorioAveriguacao> relatorios) {
        this.averiguacao.dataRegisto = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        this.averiguacao.dados = relatorios;
    }
}
