package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.ColaboradorBd;
import com.vvm.sh.api.modelos.bd.ExtintorBd;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.bd.RelatorioAveriguacaoBd;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

public class UploadRepositorio {

    private final UploadDao uploadDao;

    public UploadRepositorio(@NonNull UploadDao uploadDao) {
        this.uploadDao = uploadDao;
    }


    public EmailResultado obterEmail(int idTarefa){
        return uploadDao.obterEmail(idTarefa);
    }



    public List<AnomaliaResultado> obterAnomalias(int idTarefa){
        return uploadDao.obterAnomalias(idTarefa);
    }

    public List<CrossSellingResultado> obterCrossSelling(int idTarefa){
        return uploadDao.obterCrossSelling(idTarefa);
    }


    /**
     * Metodo que permite obter as ocorrencias
     * @param idTarefa o identificador da tarefa
     * @return uma lista de ocorrencias
     */
    public List<OcorrenciaResultado> obterOcorrencias(int idTarefa) {
        return uploadDao.obterOcorrencias(idTarefa);
    }


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades pendentes
     */
    public List<AtividadePendenteBd> obterAtividadesPendentes(int idTarefa) {
        return uploadDao.obterAtividadesPendentes(idTarefa);
    }


    /**
     * Metodo que permite obter a acao de formacao
     * @param idAtividade o identificador da atividade
     * @return uma acao de formacao
     */
    public AcaoFormacaoResultado obterAcaoFormacao(int idAtividade) {
        return uploadDao.obterAcaoFormacao(idAtividade);
    }


    /**
     * Metodo que permite obter os formandos da atividade
     * @param idAtividade o identificador da atividade
     * @return uma lista de formandos
     */
    public List<FormandoBd> obterFormandos(int idAtividade) {
        return uploadDao.obterFormandos(idAtividade);
    }


    /**
     * Metodo que permite obter uma tarefa
     * @param idTarefa o identificador da tarefa
     * @return os dados da tarefa
     */
    public Tarefa obterTarefa(int idTarefa) {
        return uploadDao.obterTarefa(idTarefa);
    }


    /**
     * Metodo que permite obter o registo de visita
     * @param idTarefa o identificador da tarefa
     * @return o registo de visita
     */
    public RegistoVisitaBd obterRegistoVisita(int idTarefa) {
        return uploadDao.obterRegistoVisita(idTarefa);
    }

    /**
     * Metodo que permite obter o trabalho realizado
     * @param idTarefa o identificador da tarefa
     * @return os registos
     */
    public List<TrabalhoRealizadoResultado> obterTrabalhoRealizado(int idTarefa) {
        return uploadDao.obterTrabalhoRealizado(idTarefa);
    }

    /**
     * Metodo que permite obter as imagens
     * @param ids os identificadores das imagens
     * @return uma lista de imagens
     */
    public List<ImagemResultado> obterImagens(List<Integer> ids) {
        return uploadDao.obterImagens(ids);
    }

    public List<ImagemResultado> obterImagens(int id, int origem) {
        return uploadDao.obterImagens(id, origem);
    }

    public List<Integer> obterImagens_(int id, int origem) {
        return uploadDao.obterImagens_(id, origem);
    }

    public Tipo obterChecklist(int idAtividade) {
        return uploadDao.obterChecklist(idAtividade);
    }

    public List<AreaBd> obterAreas(int idAtividade) {
        return uploadDao.obterAreas(idAtividade);
    }

    public List<String> obterSeccoes(int idRegistoArea) {
        return uploadDao.obterSeccoes(idRegistoArea);
    }

    public List<QuestionarioChecklistResultado> obterItens(int idRegistoArea, String idSeccao, String tipo) {
        return uploadDao.obterItens(idRegistoArea,idSeccao, tipo);
    }

    public List<TrabalhadorVulneravelResultado> obterTrabalhadoresVulneraveis(int idAtividade) {
        return uploadDao.obterTrabalhadoresVulneraveis(idAtividade);
    }

    public int obterNumeroHomens_TrabalhadoresVulneraveis(int id) {
        return uploadDao.obterNumeroHomens_TrabalhadoresVulneraveis(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS);
    }

    public int obterNumeroMulheres_TrabalhadoresVulneraveis(int id) {
        return uploadDao.obterNumeroMulheres_TrabalhadoresVulneraveis(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES);
    }

    public List<Integer> obterCategoriasProfissionais_TrabalhadoresVulneraveis(int id, int origem) {
        return uploadDao.obterCategoriasProfissionais_TrabalhadoresVulneraveis(id, origem);
    }

    public RelatorioAmbientalBd obterRelatorioIluminacao(int idAtividade) {
        return uploadDao.obterRelatorioIluminacao(idAtividade);
    }

    public RelatorioAmbientalBd obterRelatorioTemperaturaHumidade(int idAtividade) {
        return uploadDao.obterRelatorioTemperaturaHumidade(idAtividade);
    }

    public List<AvaliacaoAmbientalResultado> obterAvaliacoesAmbiental(int idRelatorio) {
        return uploadDao.obterAvaliacoesAmbiental(idRelatorio);
    }

    public List<Integer> obterCategoriasProfissionais(int idRegisto, int origem) {
        return uploadDao.obterCategoriasProfissionais(idRegisto, origem);
    }

    public List<Integer> obterMedidas(int idRegisto, int origem) {
        return uploadDao.obterMedidas(idRegisto, origem);
    }

    public List<String> obterEquipamentos(int idAtividade) {
        return uploadDao.obterEquipamentos(idAtividade);
    }

    public String obterProcessoProdutivo(int idAtividade) {
        return uploadDao.obterProcessoProdutivo(idAtividade);
    }


    public List<LevantamentoRiscoResultado> obterLevantamentos(int idAtividade) {
        return uploadDao.obterLevantamentos(idAtividade);
    }

    public List<RiscoResultado> obterRiscos(int idLevantamento) {
        return uploadDao.obterRiscos(idLevantamento);
    }

    public List<Integer> obterPropostaPlanoAcao(int idAtividade) {
        return uploadDao.obterPropostaPlanoAcao(idAtividade);
    }

    public int obterCapaRelatorio(int idTarefa) {
        return uploadDao.obterCapaRelatorio(idTarefa);
    }

    public List<AtividadePlanoAcaoBd> obterPlanoAcao(int idTarefa) {
        return uploadDao.obterPlanoAcao(idTarefa);
    }


    public SinistralidadeResultado obterSinistralidade(int idTarefa) {
        return uploadDao.obterSinistralidade(idTarefa);
    }

    public List<ColaboradorBd> obterQuadroPessoal(int idTarefa) {
        return uploadDao.obterColaboradores(idTarefa);
    }

    public List<TipoNovo> obterNovosEquipamentos(List<Integer> idsTarefas) {
        return uploadDao.obterNovosEquipamentos(idsTarefas);
    }

    public List<ExtintorBd> obterParqueExtintor(int idTarefa) {
        return uploadDao.obterExtintores(idTarefa);
    }

    public Integer obterNumeroExtintoresInalterados(int idTarefa) {
        return uploadDao.obterNumeroExtintoresInalterados(idTarefa);
    }

    public List<RelatorioAveriguacaoBd> obterRelatorioAveriguacao(int idTarefa) {
        return uploadDao.obterRelatorioAveriguacao(idTarefa);
    }

    public List<RelatorioAveriguacaoResultado> obterMedidasAveriguacao(int idRelatorio) {
        return uploadDao.obterMedidasAveriguacao(idRelatorio);
    }
}
