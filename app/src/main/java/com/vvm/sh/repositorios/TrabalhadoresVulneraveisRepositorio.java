package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class TrabalhadoresVulneraveisRepositorio implements Repositorio<TrabalhadorVulneravelResultado> {

    private final int idApi;
    private final TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;
    public final ResultadoDao resultadoDao;

    public TrabalhadoresVulneraveisRepositorio(int idApi,
                                               @NonNull TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                               @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.trabalhadoresVulneraveisDao = trabalhadoresVulneraveisDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;
        this.resultadoDao = resultadoDao;
    }


    @Override
    public Single<Long> inserir(TrabalhadorVulneravelResultado item) {
        return trabalhadoresVulneraveisDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(TrabalhadorVulneravelResultado item) {
        return trabalhadoresVulneraveisDao.atualizar(item);
    }


    public Completable validarVulnerabilidades(int idAtividade){
        return trabalhadoresVulneraveisDao.inserirVulnerabilidades(idAtividade, idApi);
    }

    public Observable<List<TrabalhadorVulneravel>> obterVulnerabilidades(int idAtividade){
        return trabalhadoresVulneraveisDao.obterVulnerabilidades(idAtividade, idApi);
    }


    @Override
    public Single<Integer> remover(TrabalhadorVulneravelResultado item) {
        return null;
    }
}
