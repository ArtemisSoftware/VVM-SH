package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class TransferenciasRepositorio {


    private final SegurancaAlimentarApi apiSA;
    private final SegurancaTrabalhoApi apiST;

    private final TransferenciasDao transferenciasDao;


    public TransferenciasRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST, @NonNull TransferenciasDao transferenciasDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.transferenciasDao = transferenciasDao;
    }


    //---------------------------
    //API
    //---------------------------
    //TODO: rever isto para poder escolher a api ou as duas
    private boolean sa = false;

    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<ISessao> obterTrabalho(String idUtilizador) {

        if(sa) {
            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador);
        }
        else{
            return apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador);
        }
    }


    /**
     * Metodo que permite obter o trabalho de um dia especifico para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param data da data do dia (YYYY-mm-dd)
     * @return o trabalho do dia
     */
    public Single<ISessao> obterTrabalho(String idUtilizador, String data) {

        if(sa) {
            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data);
        }
        else {
            return apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador, data);
        }
    }


    /**
     * Metodo que permite submeter os dados para o web service
     * @param dados os dados a submeter
     * @param idUtilizador o identificador do utilizador
     * @param id o identificador unico do bloco de imagens
     * @param messageDigest o messageDigest
     * @return um codigo com o resultado da submissao
     */
    public Single<Codigo> submeterDados(String dados, String idUtilizador, String id, String messageDigest) {
        return apiSA.submeterDados(dados, idUtilizador, id, messageDigest);
    }

    /**
     * Metodo que permite submeter as imagens para o web service
     * @param dados os dados a submeter
     * @param idUtilizador o identificador do utilizador
     * @param id o identificador unico do bloco de imagens
     * @param numeroFicheiro o numero total de ficheiros que irão ser submetidos
     * @param messageDigest o messageDigest
     * @return um codigo com o resultado da submissao
     */
    public Single<Codigo> submeterImagens(String dados, String idUtilizador, String id, String numeroFicheiro, String messageDigest) {
        return apiSA.submeterImagens(dados, idUtilizador, id, numeroFicheiro, messageDigest);
    }


    /**
     * Metodo que permite obter todas as pendencias
     * @param idUtilizador o identificador do utilizador
     * @return uma lista de pendencias
     */
    public Maybe<List<Pendencia>> obterPendencias(String idUtilizador){
        return transferenciasDao.obterPendencias(idUtilizador);
    }

    public Maybe<List<Pendencia>> obterPendencias(String idUtilizador, long data){
        return transferenciasDao.obterPendencias(idUtilizador, data);
    }


    /**
     * Metodo que permite obter os dados para upload
     * @param idUtilizador o identificador do utilizador
     * @return uma lista de uploads
     */
    public Maybe<List<Upload>> obterUploads(String idUtilizador){
        return transferenciasDao.obterUploads(idUtilizador, false);
    }


    /**
     * Metodo que permite obter os dados para upload de um dia especifico.
     * Serão selecionados os dados já sincronizados e não sincronizados
     * @param idUtilizador o identificador do utilizador
     * @param data a data dos dados
     * @return uma lista de uploads
     */
    public Maybe<List<Upload>> obterUploads(String idUtilizador, long data){
        return transferenciasDao.obterUploads(idUtilizador, data);
    }









    public Single<Integer> sincronizar(List<Upload> uploads){

        List<Resultado> resultados = new ArrayList<>();

        for(int index = 0; index < uploads.size(); ++index){

            for(int posicao = 0; posicao < uploads.get(index).resultados.size(); ++posicao){

                Resultado item = uploads.get(index).resultados.get(posicao);
                item.sincronizado = true;
                resultados.add(item);
            }
        }

        Resultado registos[] = new Resultado[resultados.size()];

        for(int index = 0; index < resultados.size(); ++index){
            registos[index] = resultados.get(index);
        }


        return transferenciasDao.atualizar(registos);
    }



    public EmailResultado obterEmail(int idTarefa){
        return transferenciasDao.obterEmail(idTarefa);
    }

    public List<AnomaliaResultado> obterAnomalias(int idTarefa){
        return transferenciasDao.obterAnomalias(idTarefa);
    }

    public List<CrossSellingResultado> obterCrossSelling(int idTarefa){
        return transferenciasDao.obterCrossSelling(idTarefa);
    }


    /**
     * Metodo que permite obter as ocorrencias
     * @param idTarefa o identificador da tarefa
     * @return uma lista de ocorrencias
     */
    public List<OcorrenciaResultado> obterOcorrencias(int idTarefa) {
        return transferenciasDao.obterOcorrencias(idTarefa);
    }


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades pendentes
     */
    public List<AtividadePendenteBd> obterAtividadesPendentes(int idTarefa) {
        return transferenciasDao.obterAtividadesPendentes(idTarefa);
    }


    /**
     * Metodo que permite obter a acao de formacao
     * @param idAtividade o identificador da atividade
     * @return uma acao de formacao
     */
    public AcaoFormacaoResultado obterAcaoFormacao(int idAtividade) {
        return transferenciasDao.obterAcaoFormacao(idAtividade);
    }


    /**
     * Metodo que permite obter os formandos da atividade
     * @param idAtividade o identificador da atividade
     * @return uma lista de formandos
     */
    public List<FormandoBd> obterFormandos(int idAtividade) {
        return transferenciasDao.obterFormandos(idAtividade);
    }


    /**
     * Metodo que permite obter uma tarefa
     * @param idTarefa o identificador da tarefa
     * @return os dados da tarefa
     */
    public Tarefa obterTarefa(int idTarefa) {
        return transferenciasDao.obterTarefa(idTarefa);
    }


    /**
     * Metodo que permite obter o registo de visita
     * @param idTarefa o identificador da tarefa
     * @return o registo de visita
     */
    public RegistoVisitaBd obterRegistoVisita(int idTarefa) {
        return transferenciasDao.obterRegistoVisita(idTarefa);
    }

    /**
     * Metodo que permite obter o trabalho realizado
     * @param idTarefa o identificador da tarefa
     * @return os registos
     */
    public List<TrabalhoRealizadoResultado> obterTrabalhoRealizado(int idTarefa) {
        return transferenciasDao.obterTrabalhoRealizado(idTarefa);
    }

    /**
     * Metodo que permite obter as imagens
     * @param ids os identificadores das imagens
     * @return uma lista de imagens
     */
    public List<ImagemResultado> obterImagens(List<Integer> ids) {
        return transferenciasDao.obterImagens(ids);
    }

    public List<ImagemResultado> obterImagens(int id, int origem) {
        return transferenciasDao.obterImagens(id, origem);
    }

    public Tipo obterChecklist(int idAtividade) {
        return transferenciasDao.obterChecklist(idAtividade);
    }

    public List<AreaBd> obterAreas(int idAtividade) {
        return transferenciasDao.obterAreas(idAtividade);
    }

    public List<String> obterSeccoes(int idRegistoArea) {
        return transferenciasDao.obterSeccoes(idRegistoArea);
    }

    public List<QuestionarioChecklistResultado> obterItens(int idRegistoArea, String idSeccao, String tipo) {
        return transferenciasDao.obterItens(idRegistoArea,idSeccao, tipo);
    }

    public List<TrabalhadorVulneravelResultado> obterTrabalhadoresVulneraveis(int idAtividade) {
        return transferenciasDao.obterTrabalhadoresVulneraveis(idAtividade);
    }

    public int obterNumeroHomens_TrabalhadoresVulneraveis(int id) {
        return transferenciasDao.obterNumeroHomens_TrabalhadoresVulneraveis(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS);
    }

    public int obterNumeroMulheres_TrabalhadoresVulneraveis(int id) {
        return transferenciasDao.obterNumeroMulheres_TrabalhadoresVulneraveis(id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES);
    }

    public List<Integer> obterCategoriasProfissionais_TrabalhadoresVulneraveis(int id, int origem) {
        return transferenciasDao.obterCategoriasProfissionais_TrabalhadoresVulneraveis(id, origem);
    }

    public RelatorioAmbientalBd obterRelatorioIluminacao(int idAtividade) {
        return transferenciasDao.obterRelatorioIluminacao(idAtividade);
    }

    public RelatorioAmbientalBd obterRelatorioTemperaturaHumidade(int idAtividade) {
        return transferenciasDao.obterRelatorioTemperaturaHumidade(idAtividade);
    }

    public List<AvaliacaoAmbientalResultado> obterAvaliacoesAmbiental(int idRelatorio) {
        return transferenciasDao.obterAvaliacoesAmbiental(idRelatorio);
    }

    public List<Integer> obterCategoriasProfissionais(int idRegisto, int origem) {
        return transferenciasDao.obterCategoriasProfissionais(idRegisto, origem);
    }

    public List<Integer> obterMedidas(int idRegisto, int origem) {
        return transferenciasDao.obterMedidas(idRegisto, origem);
    }

    //---------------------------
    //DOWNLOAD
    //---------------------------


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
     * Metodo que permite eliminar uma tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void eliminarTarefa(int idTarefa) {
        transferenciasDao.removerTarefa(idTarefa);
    }

    /**
     * Metodo que permite eliminar o trabalho
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void eliminarTrabalho(String idUtilizador, long data) {
        transferenciasDao.removerTrabalho(idUtilizador, data);
    }



}
