package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;

public class AnomaliaRepositorio {

    private final AnomaliaDao anomaliaDao;
    private final TipoDao tipoDao;
    private final ResultadoDao resultadoDao;

    public AnomaliaRepositorio(@NonNull AnomaliaDao anomaliaDao, @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.anomaliaDao = anomaliaDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    public Flowable<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaDao.obterAnomalias(idTarefa);
    }


    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.TIPOS_ANOMALIA);
    }
}
