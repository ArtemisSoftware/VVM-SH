package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;

import java.util.List;

import io.reactivex.Single;

public class AgendaRepositorio {

    private final SegurancaAlimentarApi api;
    private final TarefaDao tarefaDao;
    private final AtividadeExecutadaDao atividadeExecutadaDao;
    private final ClienteDao clienteDao;

    public AgendaRepositorio(@NonNull SegurancaAlimentarApi api,
                             @NonNull TarefaDao tarefaDao, @NonNull AtividadeExecutadaDao atividadeExecutadaDao, @NonNull ClienteDao clienteDao) {
        this.api = api;
        this.tarefaDao = tarefaDao;
        this.atividadeExecutadaDao = atividadeExecutadaDao;
        this.clienteDao = clienteDao;
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


}
