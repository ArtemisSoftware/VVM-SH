package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RegistoVisitaRepositorio {


    private final RegistoVisitaDao registoVisitaDao;
    private final TrabalhosRealizadosDao trabalhosRealizadosDao;
    private final ImagemDao imagemDao;
    public final ResultadoDao resultadoDao;
    private final int api;

    public RegistoVisitaRepositorio(int api, @NonNull RegistoVisitaDao registoVisitaDao, @NonNull TrabalhosRealizadosDao trabalhosRealizadosDao,
                                    @NonNull ImagemDao imagemDao,
                                    @NonNull ResultadoDao resultadoDao) {

        this.registoVisitaDao = registoVisitaDao;
        this.trabalhosRealizadosDao = trabalhosRealizadosDao;
        this.resultadoDao = resultadoDao;
        this.imagemDao = imagemDao;
        this.api = api;
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


    public Observable<List<TrabalhoRealizado>> obterTrabalhosRealizados(int idTarefa){
        return trabalhosRealizadosDao.obterTrabalhosRealizados(idTarefa, api);
    }


    public Maybe<DadosCliente> obterDadosCliente(int idTarefa){
        return registoVisitaDao.obterDadosCliente(idTarefa);
    }


    public Observable<RegistoVisita> obterValidadeRegistoVisita(int idTarefa){
        return registoVisitaDao.obterValidadeRegistoVisita(idTarefa);
    }


    public Flowable<? extends Number> gravarAssinatura(ImagemResultado imagemResultado) {

        return Single.concat(imagemDao.remover(imagemResultado.id, imagemResultado.origem), imagemDao.inserir(imagemResultado));

    }
}
