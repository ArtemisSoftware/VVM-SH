package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.ParqueExtintorDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.SinistralidadeDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.ui.parqueExtintores.modelos.ExtintorRegisto;
import com.vvm.sh.ui.tarefa.modelos.TarefaDia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.EmailResultado;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class TarefaRepositorio {

    private final TarefaDao tarefaDao;
    private final EmailDao emailDao;
    private final SinistralidadeDao sinistralidadeDao;
    private final ParqueExtintorDao parqueExtintorDao;
    public final ResultadoDao resultadoDao;

    public TarefaRepositorio(@NonNull TarefaDao tarefaDao,
                             @NonNull EmailDao emailDao, @NonNull SinistralidadeDao sinistralidadeDao, @NonNull ParqueExtintorDao parqueExtintorDao,
                             @NonNull ResultadoDao resultadoDao) {
        this.tarefaDao = tarefaDao;
        this.emailDao = emailDao;
        this.sinistralidadeDao = sinistralidadeDao;
        this.parqueExtintorDao = parqueExtintorDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter a tarefa
     * @param idTarefa o identificador da tarefa
     * @return os dados da tarefa
     */
    public Observable<TarefaDia> obterTarefa(int idTarefa) {
        return tarefaDao.obterTarefaDia(idTarefa);
    }


    /**
     * Metodo que permite obter as atividades excecutadas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Single<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
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





    /**
     * Meotdo que permite inserir um registo
     * @param registo os dados do registo
     * @return o resultado da inserção
     */
    public Single<Long> inserir(ParqueExtintorResultado registo) {
        return parqueExtintorDao.inserir(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados do registo
     * @return o resultado da atualizacao
     */
    public Single<Integer> atualizar(ParqueExtintorResultado registo) {
        return parqueExtintorDao.atualizar(registo);
    }


    /**
     * Metodo que permite obter os extintores
     * @param idTarefa o identificador da tarefa
     * @return os registos
     */
    public Flowable<List<ExtintorRegisto>> obterExtintores(int idTarefa) {
        return parqueExtintorDao.obterExtintores(idTarefa);
    }


    /**
     * Metodo que permite obter o numero de extintores validados
     * @param idTarefa o identificador da tarefa
     * @return um numero
     */
    public Observable<Integer> obterEstatisticaExtintores(int idTarefa) {
        return parqueExtintorDao.obterNumeroValidados(idTarefa);
    }


    public Completable inserirValicao(int idTarefa) {
        return parqueExtintorDao.inserirValicao(idTarefa);
    }

    public Completable atualizarValidacao(int idTarefa) {
        return parqueExtintorDao.atualizarValidacao(idTarefa);
    }

}
