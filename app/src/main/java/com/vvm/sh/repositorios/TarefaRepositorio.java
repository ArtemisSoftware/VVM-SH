package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TarefaRepositorio {

    private final TarefaDao tarefaDao;
    private final AtividadeExecutadaDao atividadeExecutadaDao;

    public TarefaRepositorio(@NonNull TarefaDao clienteDao, @NonNull AtividadeExecutadaDao atividadeExecutadaDao) {
        this.tarefaDao = clienteDao;
        this.atividadeExecutadaDao = atividadeExecutadaDao;
    }

    public Single<TarefaDia> obterTarefa(int idTarefa) {
        return tarefaDao.obterTarefaDia(idTarefa);
    }



    public Flowable<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
        return atividadeExecutadaDao.obterAtividades(idTarefa);
    }



}
