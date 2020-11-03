package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.ui.transferencias.modelos.DadosPendencia;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.AppConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class TransferenciasRepositorio {


    private final SegurancaAlimentarApi apiSA;
    private final SegurancaTrabalhoApi apiST;

    private final TransferenciasDao transferenciasDao;


    public TransferenciasRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST,
                                     @NonNull TransferenciasDao transferenciasDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.transferenciasDao = transferenciasDao;
    }


    //---------------------------
    //API
    //---------------------------


    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<Sessao> obterTrabalho(String idUtilizador) {

        if(AppConfig.sa) {

            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador)
                    .map(new Function<ISessao, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao) throws Exception {
                            Sessao sessao = new Sessao();
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    });

        }
        else {
            return Single.zip(
                    apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                            Sessao sessao = new Sessao();
                            sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    }
            );
        }
    }


    /**
     * Metodo que permite obter o trabalho de um dia especifico para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param data da data do dia (YYYY-mm-dd)
     * @return o trabalho do dia
     */
    public Single<Sessao> obterTrabalho(String idUtilizador, String data) {

        if(AppConfig.sa) {

            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data)
                    .map(new Function<ISessao, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao) throws Exception {
                            Sessao sessao = new Sessao();
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    });

        }
        else {
            return Single.zip(
                    apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador, data),
                    apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                            Sessao sessao = new Sessao();
                            sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    }
            );
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
    public Maybe<DadosPendencia> obterPendencias(String idUtilizador){

        return Maybe.zip(transferenciasDao.obterPendencias(idUtilizador), transferenciasDao.existemDadosUpload(idUtilizador),
                new BiFunction<List<Pendencia>, Boolean, DadosPendencia>() {
                    @Override
                    public DadosPendencia apply(List<Pendencia> pendencias, Boolean aBoolean) throws Exception {

                        DadosPendencia dadosPendencia = new DadosPendencia();
                        dadosPendencia.dadosUpload = aBoolean;
                        dadosPendencia.pendencias = pendencias;
                        return dadosPendencia;
                    }
                });

    }

    public Maybe<DadosPendencia> obterPendencias(String idUtilizador, long data){

        return Maybe.zip(transferenciasDao.obterPendencias(idUtilizador, data), transferenciasDao.existemDadosUpload(idUtilizador),
                new BiFunction<List<Pendencia>, Boolean, DadosPendencia>() {
                    @Override
                    public DadosPendencia apply(List<Pendencia> pendencias, Boolean aBoolean) throws Exception {

                        DadosPendencia dadosPendencia = new DadosPendencia();
                        dadosPendencia.dadosUpload = aBoolean;
                        dadosPendencia.pendencias = pendencias;
                        return dadosPendencia;
                    }
                });
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


    public Long inserirRelatorioAveriguacao(RelatorioAveriguacao relatorio) {
        return transferenciasDao.inserirRelatorioAveriguacao(relatorio);
    }

    public void inserirMedidas(List<MedidaResultado> registos) {
        transferenciasDao.inserirMedidas(registos);
    }
}
