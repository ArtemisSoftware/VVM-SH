package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;

import java.util.List;

import io.reactivex.Flowable;

public class OcorrenciaRepositorio {

    private final OcorrenciaDao ocorrenciaDao;

    public OcorrenciaRepositorio(@NonNull OcorrenciaDao ocorrenciaDao) {
        this.ocorrenciaDao = ocorrenciaDao;
    }


    public Flowable<List<Ocorrencia>> obterOcorrencias(int idTarefa) {
        return ocorrenciaDao.obterOcorrencias(idTarefa);
    }
}
