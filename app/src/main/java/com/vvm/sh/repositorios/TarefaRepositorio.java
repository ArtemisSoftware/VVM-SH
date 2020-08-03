package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.EmailDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TarefaDao;
import com.vvm.sh.ui.agenda.modelos.TarefaDia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.tarefa.modelos.EmailResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TarefaRepositorio {

    private final TarefaDao tarefaDao;
    private final AtividadeExecutadaDao atividadeExecutadaDao;
    private final EmailDao emailDao;
    public final ResultadoDao resultadoDao;

    public TarefaRepositorio(@NonNull TarefaDao clienteDao, @NonNull AtividadeExecutadaDao atividadeExecutadaDao,
                             @NonNull EmailDao emailDao, @NonNull ResultadoDao resultadoDao) {
        this.tarefaDao = clienteDao;
        this.atividadeExecutadaDao = atividadeExecutadaDao;
        this.emailDao = emailDao;
        this.resultadoDao = resultadoDao;
    }

    public Flowable<TarefaDia> obterTarefa(int idTarefa) {
        return tarefaDao.obterTarefaDia(idTarefa);
    }



    public Flowable<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
        return atividadeExecutadaDao.obterAtividades(idTarefa);
    }



    public Single<Long> inserir(EmailResultado email) {
        return emailDao.inserir(email);
    }


    public Single<Integer> atualizar(EmailResultado email) {
        return emailDao.atualizar(email);
    }



}
