package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.EmailResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class TarefaRepositorio {

    private final TarefaDao tarefaDao;
    private final EmailDao emailDao;
    public final ResultadoDao resultadoDao;

    public TarefaRepositorio(@NonNull TarefaDao tarefaDao,
                             @NonNull EmailDao emailDao, @NonNull ResultadoDao resultadoDao) {
        this.tarefaDao = tarefaDao;
        this.emailDao = emailDao;
        this.resultadoDao = resultadoDao;
    }

    public Flowable<TarefaDia> obterTarefa(int idTarefa) {
        return tarefaDao.obterTarefaDia(idTarefa);
    }


    public Flowable<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
        return tarefaDao.obterAtividades(idTarefa);
    }



    public Single<Long> inserir(EmailResultado email) {
        return emailDao.inserir(email);
    }


    public Single<Integer> atualizar(EmailResultado email) {
        return emailDao.atualizar(email);
    }



}
