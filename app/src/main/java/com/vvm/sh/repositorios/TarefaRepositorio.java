package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.SinistralidadeDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.EmailResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class TarefaRepositorio {

    private final TarefaDao tarefaDao;
    private final EmailDao emailDao;
    private final SinistralidadeDao sinistralidadeDao;
    public final ResultadoDao resultadoDao;

    public TarefaRepositorio(@NonNull TarefaDao tarefaDao,
                             @NonNull EmailDao emailDao, @NonNull SinistralidadeDao sinistralidadeDao, @NonNull ResultadoDao resultadoDao) {
        this.tarefaDao = tarefaDao;
        this.emailDao = emailDao;
        this.sinistralidadeDao = sinistralidadeDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter a tarefa
     * @param idTarefa o identificador da tarefa
     * @return os dados da tarefa
     */
    public Flowable<TarefaDia> obterTarefa(int idTarefa) {
        return tarefaDao.obterTarefaDia(idTarefa);
    }


    /**
     * Metodo que permite obter as atividades excecutadas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
        return tarefaDao.obterAtividades(idTarefa);
    }


    /**
     * Meotdo que permite inserir um email
     * @param email os dados do email
     * @return o resultado da inserção
     */
    public Single<Long> inserir(EmailResultado email) {
        return emailDao.inserir(email);
    }


    /**
     * Metodo que permite atualizar um email
     * @param email os dados do email
     * @return o resultado da atualizacao
     */
    public Single<Integer> atualizar(EmailResultado email) {
        return emailDao.atualizar(email);
    }



    /**
     * Meotdo que permite inserir uma sinistralidade
     * @param sinistralidade os dados da sinistralidade
     * @return o resultado da inserção
     */
    public Single<Long> inserir(SinistralidadeResultado sinistralidade) {
        return sinistralidadeDao.inserir(sinistralidade);
    }


    /**
     * Metodo que permite atualizar uma sinistralidade
     * @param sinistralidade os dados da sinistralidade
     * @return o resultado da atualizacao
     */
    public Single<Integer> atualizar(SinistralidadeResultado sinistralidade) {
        return sinistralidadeDao.atualizar(sinistralidade);
    }


    /**
     * Metodo que permite obter a sinistralidade
     * @param idTarefa o identificador da tarefa
     * @return os dados da sinistralidade
     */
    public Maybe<SinistralidadeResultado> obterSinistralidade(int idTarefa) {
        return sinistralidadeDao.obterSinistralidade(idTarefa);
    }

}
