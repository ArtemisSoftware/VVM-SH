package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RegistoVisitaRepositorio {


    private final RegistoVisitaDao registoVisitaDao;
    private final TrabalhosRealizadosDao trabalhosRealizadosDao;
    public final ResultadoDao resultadoDao;

    public RegistoVisitaRepositorio(@NonNull RegistoVisitaDao registoVisitaDao, @NonNull TrabalhosRealizadosDao trabalhosRealizadosDao,
                                    @NonNull ResultadoDao resultadoDao) {

        this.registoVisitaDao = registoVisitaDao;
        this.trabalhosRealizadosDao = trabalhosRealizadosDao;
        this.resultadoDao = resultadoDao;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(RegistoVisitaResultado registo){
        return registoVisitaDao.inserir(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(RegistoVisitaResultado registo){
        return registoVisitaDao.atualizar(registo);
    }


    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(TrabalhoRealizadoResultado registo){
        return trabalhosRealizadosDao.inserir(registo);
    }


    public Single<Integer> remover(int idTarefa, int id){
        return trabalhosRealizadosDao.remover(idTarefa, id);
    }


    public Observable<List<Tipo>> obterTrabalhosRealizados(int idTarefa){
        return trabalhosRealizadosDao.obterTrabalhosRealizados(idTarefa);
    }


    public Maybe<RegistoVisitaResultado> obterRegisto(int idTarefa){
        return registoVisitaDao.obterRegistoVisita(idTarefa);
    }
}
