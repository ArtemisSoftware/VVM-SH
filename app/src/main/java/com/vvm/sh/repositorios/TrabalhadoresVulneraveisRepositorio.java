package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravel;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TrabalhadoresVulneraveisRepositorio implements Repositorio<TrabalhadorVulneravel> {

    private final int idApi;
    private final TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao;
    public final ResultadoDao resultadoDao;

    public TrabalhadoresVulneraveisRepositorio(int idApi,
                                               @NonNull TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao,
                                               @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.trabalhadoresVulneraveisDao = trabalhadoresVulneraveisDao;
        this.resultadoDao = resultadoDao;
    }


    @Override
    public Single<Long> inserir(TrabalhadorVulneravel item) {
        return trabalhadoresVulneraveisDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(TrabalhadorVulneravel item) {
        return trabalhadoresVulneraveisDao.atualizar(item);
    }


    public Completable validarVulnerabilidades(int idAtividade){
        return trabalhadoresVulneraveisDao.inserirVulnerabilidades(idAtividade, idApi);
    }

    @Override
    public Single<Integer> remover(TrabalhadorVulneravel item) {
        return null;
    }
}
