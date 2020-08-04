package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.OcorrenciaDao;
import com.vvm.sh.baseDados.OcorrenciaResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class OcorrenciaRepositorio {

    private final OcorrenciaDao ocorrenciaDao;
    private final OcorrenciaResultadoDao ocorrenciaResultadoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public OcorrenciaRepositorio(@NonNull OcorrenciaDao ocorrenciaDao,
                                 @NonNull OcorrenciaResultadoDao ocorrenciaResultadoDao,
                                 @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.ocorrenciaDao = ocorrenciaDao;
        this.ocorrenciaResultadoDao = ocorrenciaResultadoDao;
        this.resultadoDao = resultadoDao;
        this.tipoDao = tipoDao;
    }


    /**
     * Metodo que permite obter as ocorrencias associadas à tarefa
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<Ocorrencia>> obterOcorrencias(int idTarefa) {
        return ocorrenciaResultadoDao.obterOcorrencias(idTarefa);
    }

    public Flowable<List<OcorrenciaHistorico>> obterHistorico(int id) {
        return ocorrenciaResultadoDao.obterHistorico(id);
    }

    public Flowable<List<OcorrenciaRegisto>> obterRegistoOcorrencias(int idTarefa, int idOcorrencia) {
        return ocorrenciaDao.obterOcorrencias(idTarefa, TiposConstantes.TIPIFICACAO_OCORRENCIA, idOcorrencia);
    }

    public Flowable<List<OcorrenciaRegisto>> obterRegistoOcorrencias(int idTarefa) {
        return ocorrenciaDao.obterOcorrenciasRegistadas(idTarefa, TiposConstantes.TIPIFICACAO_OCORRENCIA);
    }



    public Single<OcorrenciaRegisto> obterResultadoOcorrencia(int idTarefa, int id){
        return ocorrenciaDao.obterResultadoOcorrencia(idTarefa, TiposConstantes.TIPIFICACAO_OCORRENCIA, id);
    }

    public Single<Long> inserir(OcorrenciaResultado ocorrencia){
        return ocorrenciaResultadoDao.inserir(ocorrencia);
    }

    public Single<Integer> atualizar(OcorrenciaResultado ocorrencia){
        return ocorrenciaResultadoDao.atualizar(ocorrencia);
    }

    public Single<Integer> remover(int idTarefa, int id){
        return ocorrenciaResultadoDao.remover(idTarefa, id);
    }



    public Flowable<List<Tipo>> obterOcorrencias() {
        return tipoDao.obterTipos(TiposConstantes.TIPIFICACAO_OCORRENCIA, "");
    }



    public Flowable<Tipo> obterOcorrencia(int id) {
        return tipoDao.obterTipo(TiposConstantes.TIPIFICACAO_OCORRENCIA, id);
    }

}
