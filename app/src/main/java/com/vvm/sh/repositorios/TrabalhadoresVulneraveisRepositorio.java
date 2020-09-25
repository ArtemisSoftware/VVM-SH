package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravel;

import io.reactivex.Single;

public class TrabalhadoresVulneraveisRepositorio implements Repositorio<TrabalhadorVulneravel> {


    private final TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao;
    public final ResultadoDao resultadoDao;

    public TrabalhadoresVulneraveisRepositorio(int idApi,
                                               @NonNull TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao,
                                               @NonNull ResultadoDao resultadoDao) {

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

    @Override
    public Single<Integer> remover(TrabalhadorVulneravel item) {
        return null;
    }
}
