package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.AnomaliaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaRegistada;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class AnomaliaRepositorio {

    private final AnomaliaDao anomaliaDao;
    private final AnomaliaResultadoDao anomaliaResultadoDao;
    private final TipoDao tipoDao;
    private final ResultadoDao resultadoDao;

    public AnomaliaRepositorio(@NonNull AnomaliaDao anomaliaDao, @NonNull AnomaliaResultadoDao anomaliaResultadoDao,
                               @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.anomaliaDao = anomaliaDao;
        this.anomaliaResultadoDao = anomaliaResultadoDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }

    public Single<Long> inserir(AnomaliaResultado anomalia) {
        return anomaliaResultadoDao.inserir(anomalia);
    }

    public Single<Integer> atualizar(AnomaliaResultado anomalia) {
        return anomaliaResultadoDao.atualizar(anomalia);
    }

    public Single<Integer> remover(int idAnomalia) {
        return anomaliaResultadoDao.remover(idAnomalia);
    }



    public Flowable<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaDao.obterAnomalias(idTarefa);
    }


    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.TIPOS_ANOMALIA);
    }

    public Flowable<List<AnomaliaRegistada>> obterAnomaliasRegistadas(int idTarefa) {
        return anomaliaResultadoDao.obterAnomaliasRegistadas(idTarefa, TiposConstantes.TIPOS_ANOMALIA);
    }

    public Single<AnomaliaResultado> obterAnomaliaResultado(int id) {
        return anomaliaResultadoDao.obterAnomaliaResultado(id);
    }
}
