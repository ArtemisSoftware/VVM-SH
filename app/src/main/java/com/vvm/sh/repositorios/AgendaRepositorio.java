package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.ui.agenda.modelos.Marcacao;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;

public class AgendaRepositorio {

    private final AgendaDao agendaDao;

    public AgendaRepositorio(@NonNull AgendaDao agendaDao) {

        this.agendaDao = agendaDao;
    }


    /**
     * Metodo que permite obter as marcações do de um dia
     * @param idUtilizador o identificador do utilizador
     * @param data a data das marcacaoes
     * @return uma lista de marcacaoes
     */
    public Flowable<List<Marcacao>> obterMarcacoes(String idUtilizador, long data){
        return agendaDao.obterMarcacoes(idUtilizador, data);
    }

    /**
     * Metodo que permite obter a completude de um dia
     * @param idUtilizador o identificador do utilizador
     * @param data a data a validar
     * @return o estado da completude
     */
    public Flowable<Integer> obterCompletude(String idUtilizador, long data){
        return agendaDao.obterCompletude(idUtilizador, data);
    }


    /**
     * Metodo que permite obter a  lista de datas com tarefas
     * @param idUtilizador  o identificador do utilizador
     * @return uma lista de datas
     */
    public Flowable<List<Date>> obterDatas(String idUtilizador){
        return agendaDao.obterDatas(idUtilizador);
    }



}
