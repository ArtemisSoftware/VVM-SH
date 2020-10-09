package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
import com.vvm.sh.ui.agenda.modelos.Marcacao;

import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public class AgendaRepositorio {

    private final AgendaDao agendaDao;
    private final UtilizadorDao utilizadorDao;

    public AgendaRepositorio(@NonNull AgendaDao agendaDao, @NonNull UtilizadorDao utilizadorDao) {

        this.agendaDao = agendaDao;
        this.utilizadorDao = utilizadorDao;
    }


    /**
     * Metodo que permite obter as marcações do de um dia
     * @param idUtilizador o identificador do utilizador
     * @param data a data das marcacaoes
     * @return uma lista de marcacaoes
     */
    public Observable<List<Marcacao>> obterMarcacoes(String idUtilizador, long data){
        return agendaDao.obterMarcacoes(idUtilizador, data);
    }

    /**
     * Metodo que permite obter a completude de um dia
     * @param idUtilizador o identificador do utilizador
     * @param data a data a validar
     * @return o estado da completude
     */
    public Observable<Integer> obterCompletude(String idUtilizador, long data){
        return agendaDao.obterCompletude(idUtilizador, data);
    }


    /**
     * Metodo que permite obter a  lista de datas com tarefas
     * @param idUtilizador  o identificador do utilizador
     * @return uma lista de datas
     */
    public Maybe<List<Date>> obterDatas(String idUtilizador){
        return agendaDao.obterDatas(idUtilizador);
    }


    /**
     * Metodo que permite terminar a sessao do utilizador
     * @return
     */
    public Completable terminarSessao(){
        return utilizadorDao.remover();
    }

}
