package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaHistoricoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaHistorico;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;

public class OcorrenciaRepositorio {

    private final OcorrenciaDao ocorrenciaDao;
    private final OcorrenciaHistoricoDao ocorrenciaHistoricoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public OcorrenciaRepositorio(@NonNull OcorrenciaDao ocorrenciaDao, @NonNull OcorrenciaHistoricoDao ocorrenciaHistoricoDao,
                                 @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.ocorrenciaDao = ocorrenciaDao;
        this.ocorrenciaHistoricoDao = ocorrenciaHistoricoDao;
        this.resultadoDao = resultadoDao;
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

    public Flowable<List<OcorrenciaHistorico>> obterHistorico(int id) {
        return ocorrenciaHistoricoDao.obterHistorico(id);
    }

    public Flowable<List<OcorrenciaRegisto>> obterRegistoOcorrencias(int idTarefa, int idOcorrencia) {
        return ocorrenciaDao.obterOcorrencias(idTarefa, TiposConstantes.TIPIFICACAO_OCORRENCIA, idOcorrencia);
    }




    public Flowable<List<Tipo>> obterOcorrencias() {
        return tipoDao.obterTipos(TiposConstantes.TIPIFICACAO_OCORRENCIA, "");
    }



    public Flowable<Tipo> obterOcorrencia(int id) {
        return tipoDao.obterTipo(TiposConstantes.TIPIFICACAO_OCORRENCIA, id);
    }

}
