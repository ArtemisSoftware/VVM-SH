package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaAveriguacao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;

import java.util.List;

public class DownloadTrabalhoRepositorio {


    private final TransferenciasDao transferenciasDao;

    public DownloadTrabalhoRepositorio(@NonNull TransferenciasDao transferenciasDao) {
        this.transferenciasDao = transferenciasDao;
    }


    /**
     * Metodo que permite eliminar o trabalho
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void eliminarTrabalho(String idUtilizador, long data, int api) {

        if(api == 0) {
            transferenciasDao.removerTrabalho(idUtilizador, data);
        }
        else{
            transferenciasDao.removerTrabalho(idUtilizador, data, api);
        }
    }


    /**
     * Metodo que permite eliminar uma tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void eliminarTarefa(int idTarefa) {
        transferenciasDao.removerTarefa(idTarefa);
    }








    /**
     * Metodo que permite inserir uma tarefa
     * @param tarefa os dados da tarefa
     * @return o resultado da insercao
     */
    public long inserirTarefa(Tarefa tarefa){
        return transferenciasDao.inserirRegisto(tarefa);
    }

    public void inserirAtividadesExecutadas(List<AtividadeExecutada> atividades){
        transferenciasDao.inserirAtividadesExecutadas(atividades);
    }

    public void inserirCliente(Cliente cliente){
        transferenciasDao.inserirRegisto(cliente);
    }

    public void inserirAnomalias(List<Anomalia> anomalias){
        transferenciasDao.inserirAnomalias(anomalias);
    }

    public Long inserirAtividadePendente(AtividadePendente atividade){
        return transferenciasDao.inserirAtividadePendente(atividade);
    }

    public void inserirAtividadesPendentes(List<AtividadePendente> atividades){
        transferenciasDao.inserirAtividadesPendentes(atividades);
    }


    /**
     * Metodo que permite inserir o historico de ocorrencias
     * @param ocorrencia os dados da ocorrencia
     * @return o identificador da ocorrencia
     */
    public long inserirOcorrencia(Ocorrencia ocorrencia){
        return transferenciasDao.inserirRegisto(ocorrencia);
    }


    /**
     * Metodo que permite inserir o historico de ocorrencias
     * @param historico uma lista de historicos
     */
    public void inserirHistoricoOcorrencias(List<OcorrenciaHistorico> historico){
        transferenciasDao.inserir(historico);
    }


    /**
     * Metodo que permite inserir as moradas
     * @param registos os dados a inserir
     */
    public void inserirMoradas(List<Morada> registos) {
        transferenciasDao.inserirMoradas(registos);
    }


    /**
     * Metodo que permite inserir os tipos extintores
     * @param registos os dados a inserir
     */
    public void inserirTipoExtintor(List<TipoExtintor> registos) {
        transferenciasDao.inserirTipoExtintor(registos);
    }

    /**
     * Metodo que permite inserir o parque extintor
     * @param registos os dados a inserir
     */
    public void inserirParqueExtintores(List<ParqueExtintor> registos) {
        transferenciasDao.inserirParqueExtintores(registos);
    }


    /**
     * Metodo que permite inserir o quadro pessoal
     * @param registos os dados a inserir
     */
    public void inserirQuadroPessoal(List<Colaborador> registos) {
        transferenciasDao.inserirQuadroPessoal(registos);
    }

    /**
     * Metodo que permite inserir o plano de acao
     * @param registo os dados a inserir
     */
    public void inserirPlanoAcao(PlanoAcao registo) {
        transferenciasDao.inserirPlanoAcao(registo);
    }

    /**
     * Metodo que permite inserir as atividades do plano de acao
     * @param registos os dados a inserir
     */
    public void inserirPlanoAtividades(List<PlanoAcaoAtividade> registos) {
        transferenciasDao.inserirPlanoAtividades(registos);
    }




    /**
     * Metodo que permite eliminar o trabalho
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void eliminarTrabalho(String idUtilizador, long data) {
        transferenciasDao.removerTrabalho(idUtilizador, data);
    }


    public Long inserirRelatorioAveriguacao(RelatorioAveriguacao relatorio) {
        return transferenciasDao.inserirRelatorioAveriguacao(relatorio);
    }

    public void inserirMedidasAveriguacao(List<MedidaAveriguacao> registos) {
        transferenciasDao.inserirMedidasAveriguacao(registos);
    }

    public void inserirProcessoProdutivo(ProcessoProdutivoResultado processoProdutivo) {
        transferenciasDao.inserirProcessoProdutivo(processoProdutivo);
    }

    public void inserirEquipamentos(List<VerificacaoEquipamentoResultado> equipamentos) {
        transferenciasDao.inserirEquipamentos(equipamentos);
    }

    public long inserirTrabalhadorVulneravel(TrabalhadorVulneravelResultado trabalhadorVulneravel) {
        return transferenciasDao.inserirTrabalhadorVulneravel(trabalhadorVulneravel);
    }

    public void inserirCategoriasProfissionais(List<CategoriaProfissionalResultado> categorias) {
        transferenciasDao.inserirCategoriasProfissionais(categorias);
    }

    public long inserirLevantamento(LevantamentoRiscoResultado levantamento) {
        return transferenciasDao.inserirLevantamento(levantamento);
    }

    public long inserirRisco(RiscoResultado risco) {
        return transferenciasDao.inserirRisco(risco);
    }

    public void inserirMedidas(List<MedidaResultado> medidas) {
        transferenciasDao.inserirMedidas(medidas);
    }

    public List<Tipo> obterNi() {
        return transferenciasDao.obterNi();
    }

    public boolean validarMedida(int idMedida, String tipo) {
        return transferenciasDao.validarMedida(idMedida, tipo);
    }

    public boolean verificarItemChecklist(String idChecklist, String idArea, String idSeccao, String idItem) {
        return transferenciasDao.verificarItemChecklist(Integer.parseInt(idChecklist), Integer.parseInt(idArea), idSeccao, idItem);
    }

    public String obterTipoItemChecklist(String idChecklist, String idArea, String idSeccao, String idItem) {
        return transferenciasDao.obterTipoItemChecklist(Integer.parseInt(idChecklist), Integer.parseInt(idArea), idSeccao, idItem);
    }

    public void inserirQuestoes(List<QuestionarioChecklistResultado> questoes) {
        transferenciasDao.inserirQuestoes(questoes);
    }

    public long inserirQuestao(QuestionarioChecklistResultado questao) {
        return transferenciasDao.inserirQuestao(questao);
    }

    public long inserirAreaChecklist(AreaChecklistResultado area) {
        return transferenciasDao.inserirQuestoes(area);
    }

    public void inserirPropostaPlanoAcao(List<PropostaPlanoAcaoResultado> medidasChecklist) {
        transferenciasDao.inserirPropostaPlanoAcao(medidasChecklist);
    }

    public List<Tipo> obterUts() {
        return transferenciasDao.obterUts();
    }



    public void inserirFormandos(List<FormandoResultado> formandos) {
        transferenciasDao.inserirFormandos(formandos);
    }


}
