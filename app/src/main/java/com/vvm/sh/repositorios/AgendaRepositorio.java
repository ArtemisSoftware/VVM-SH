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



}
