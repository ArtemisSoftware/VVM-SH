package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.FormandoResultado_;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.Codigo;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class TransferenciasRepositorio {


    private final SegurancaAlimentarApi api;

    private final TransferenciasDao transferenciasDao;


    public TransferenciasRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull TransferenciasDao transferenciasDao) {
        this.api = api;
        this.transferenciasDao = transferenciasDao;
    }





    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<SessaoResposta[]> obterTrabalho(String idUtilizador) {
        return api.obterTrabalho(idUtilizador);
    }


    /**
     * Metodo que permite submeter os dados para o web service
     * @param dados os dados a submeter
     * @param idUtilizador o identificador do utilizador
     * @return um codigo com o resultado da submissao
     */
    public Single<Codigo> submeterDados(String dados, String idUtilizador) {
        return api.submeterDados(dados, idUtilizador);
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
        return api.submeterImagens(dados, idUtilizador, id, numeroFicheiro, messageDigest);
    }








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
     *
     * @param idUtilizador
     * @param data
     * @return
     */
    public Maybe<List<Upload>> obterUploads(String idUtilizador, long data){
        return transferenciasDao.obterUploads(idUtilizador, data, false);
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

        Resultado registos[] = (Resultado[]) resultados.toArray();

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

    public List<OcorrenciaResultado> obterOcorrencias(int idTarefa) {
        return transferenciasDao.obterOcorrencias(idTarefa);
    }


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades pendentes
     */
    public List<AtividadePendenteResultado_> obterAtividadesPendentes(int idTarefa) {
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
    public List<FormandoResultado_> obterFormandos(int idAtividade) {
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
     * Metodo que permite obter as imagens
     * @param ids os identificadores das imagens
     * @return uma lista de imagens
     */
    public List<ImagemResultado> obterImagens(List<Integer> ids) {
        return transferenciasDao.obterImagens(ids);
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

    public long inserirOcorrencia(Ocorrencia ocorrencia){
        return transferenciasDao.inserirRegisto(ocorrencia);
    }

    public void inserirHistoricoOcorrencias(List<OcorrenciaHistorico> historico){
        transferenciasDao.inserir(historico);
    }



}
