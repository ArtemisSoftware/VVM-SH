package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaHistoricoDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.baseDados.dao.DownloadTrabalhoDao;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaHistorico;

import java.util.List;

import io.reactivex.Single;

public class AgendaRepositorio {

    private final SegurancaAlimentarApi api;
    private final TarefaDao tarefaDao;
    private final AtividadeExecutadaDao atividadeExecutadaDao;
    private final ClienteDao clienteDao;
    private final AtividadePendenteDao atividadePendenteDao;
    private final OcorrenciaDao ocorrenciaDao;
    private final OcorrenciaHistoricoDao ocorrenciaHistoricoDao;
    private final DownloadTrabalhoDao downloadTrabalhoDao;

    public AgendaRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull DownloadTrabalhoDao downloadTrabalhoDao,
                             @NonNull TarefaDao tarefaDao, @NonNull AtividadeExecutadaDao atividadeExecutadaDao,
                             @NonNull ClienteDao clienteDao, @NonNull AtividadePendenteDao atividadePendenteDao,
                             @NonNull OcorrenciaDao ocorrenciaDao, OcorrenciaHistoricoDao ocorrenciaHistoricoDao) {
        this.api = api;
        this.downloadTrabalhoDao = downloadTrabalhoDao;
        this.tarefaDao = tarefaDao;
        this.atividadeExecutadaDao = atividadeExecutadaDao;
        this.clienteDao = clienteDao;
        this.atividadePendenteDao = atividadePendenteDao;
        this.ocorrenciaDao = ocorrenciaDao;
        this.ocorrenciaHistoricoDao = ocorrenciaHistoricoDao;
    }

    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<SessaoResposta[]> obterTrabalho(String idUtilizador) {
        return api.obterTrabalho(idUtilizador);
    }


    public Single<List<TarefaDia>> obterTarefas(String idUtilizador, String data){
        return tarefaDao.obterTarefasDia();
    }


    public long inserirTarefa(Tarefa tarefa){
        return tarefaDao.inserirRegisto(tarefa);
    }

    public void inserirAtividadesExecutadas(List<AtividadeExecutada> atividades){
        atividadeExecutadaDao.inserir(atividades);
    }

    public void inserirCliente(Cliente cliente){
        clienteDao.inserirRegisto(cliente);
    }

    public void inserirAnomalias(List<Anomalia> anomalias){
        downloadTrabalhoDao.inserirAnomalias(anomalias);
    }

    public void inserirAtividadesPendentes(List<AtividadePendente> atividades){
        atividadePendenteDao.inserir(atividades);
    }

    public long inserirOcorrencia(Ocorrencia ocorrencia){
        return ocorrenciaDao.inserirRegisto(ocorrencia);
    }

    public void inserirHistoricoOcorrencias(List<OcorrenciaHistorico> historico){
        ocorrenciaHistoricoDao.inserir(historico);
    }


}
