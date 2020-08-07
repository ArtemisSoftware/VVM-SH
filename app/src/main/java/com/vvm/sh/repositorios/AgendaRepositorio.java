package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.DownloadTrabalhoDao;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.agenda.modelos.Marcacao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class AgendaRepositorio {

    private final SegurancaAlimentarApi api;
    private final DownloadTrabalhoDao downloadTrabalhoDao;
    private final AgendaDao agendaDao;

    private final ClienteDao clienteDao;

    public AgendaRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull DownloadTrabalhoDao downloadTrabalhoDao,
                             @NonNull AgendaDao agendaDao,
                             @NonNull ClienteDao clienteDao) {
        this.api = api;
        this.downloadTrabalhoDao = downloadTrabalhoDao;
        this.agendaDao = agendaDao;

        this.clienteDao = clienteDao;
    }



    public Flowable<List<Marcacao>> obterMarcacoes(String idUtilizador, long data){
        //TODO: terminar metodo agendaRepositorio.obterTarefas. Parametros ainda não estão a ser usados

        return agendaDao.obterMarcacoes(data);
    }

    public Flowable<Integer> obterCompletude(String idUtilizador, long data){
        //TODO: terminar metodo agendaRepositorio.obterTarefas. Parametros ainda não estão a ser usados

        return agendaDao.obterCompletude(data);
    }


    public Flowable<List<Date>> obterDatas(String idUtilizador){
        //TODO: terminar metodo agendaRepositorio.obterTarefas. Parametros ainda não estão a ser usados

        return agendaDao.obterDatas(idUtilizador);
    }


    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<SessaoResposta[]> obterTrabalho(String idUtilizador) {
        return api.obterTrabalho(idUtilizador);
    }




    public long inserirTarefa(Tarefa tarefa){
        return downloadTrabalhoDao.inserirRegisto(tarefa);
    }

    public void inserirAtividadesExecutadas(List<AtividadeExecutada> atividades){
        downloadTrabalhoDao.inserirAtividadesExecutadas(atividades);
    }

    public void inserirCliente(Cliente cliente){
        clienteDao.inserirRegisto(cliente);
    }

    public void inserirAnomalias(List<Anomalia> anomalias){
        downloadTrabalhoDao.inserirAnomalias(anomalias);
    }

    public void inserirAtividadesPendentes(List<AtividadePendente> atividades){
        downloadTrabalhoDao.inserirAtividadesPendentes(atividades);
    }

    public long inserirOcorrencia(Ocorrencia ocorrencia){
        return downloadTrabalhoDao.inserirRegisto(ocorrencia);
    }

    public void inserirHistoricoOcorrencias(List<OcorrenciaHistorico> historico){
        downloadTrabalhoDao.inserir(historico);
    }


}
