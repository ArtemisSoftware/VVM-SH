package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;

public class OcorrenciaRepositorio {

    private final OcorrenciaDao ocorrenciaDao;
    private final TipoDao tipoDao;

    public OcorrenciaRepositorio(@NonNull OcorrenciaDao ocorrenciaDao, TipoDao tipoDao) {
        this.ocorrenciaDao = ocorrenciaDao;
        this.tipoDao = tipoDao;
    }


    /**
     * Metodo que permite obter as ocorrencias associadas à tarefa
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<Ocorrencia>> obterOcorrencias(int idTarefa) {
        return ocorrenciaDao.obterOcorrencias(idTarefa);
    }

    public Flowable<List<Tipo>> obterOcorrencias() {
        return tipoDao.obterTipos(TiposConstantes.TIPIFICACAO_OCORRENCIA, "");
    }

    public Flowable<List<Tipo>> obterRegistoOcorrencias(int idOcorrencia) {
        return tipoDao.obterTipos(TiposConstantes.TIPIFICACAO_OCORRENCIA, idOcorrencia +"");
    }



}
