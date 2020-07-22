package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AnomaliaDao;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;

import java.util.List;

import io.reactivex.Flowable;

public class AnomaliaRepositorio {

    private final AnomaliaDao anomaliaDao;

    public AnomaliaRepositorio(@NonNull AnomaliaDao anomaliaDao) {
        this.anomaliaDao = anomaliaDao;
    }


    public Flowable<List<Anomalia>> obterAnomalias(int idTarefa) {
        return anomaliaDao.obterAnomalias(idTarefa);
    }
}
