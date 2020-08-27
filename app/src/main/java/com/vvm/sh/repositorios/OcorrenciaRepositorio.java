package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.dao.OcorrenciaResultadoDao;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaBase;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class OcorrenciaRepositorio {

    private final OcorrenciaResultadoDao ocorrenciaResultadoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public OcorrenciaRepositorio(@NonNull OcorrenciaResultadoDao ocorrenciaResultadoDao,
                                 @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

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


    /**
     * Obter grupos de ocorrencias
     * @return uma lista
     */
    public Flowable<List<Tipo>> obterOcorrencias() {
        return tipoDao.obterTipos(TiposConstantes.TIPIFICACAO_OCORRENCIA, "");
    }



    /**
     * Metodo que permite obter as ocorrencias possiveis de serem registadas
     * @param idTarefa o identificador da tarefa
     * @param idOcorrencia o identificador do grupo de ocorrencias
     * @return uma lista
     */
    public Flowable<List<OcorrenciaBase>> obterRegistoOcorrencias(int idTarefa, int idOcorrencia) {
        return ocorrenciaResultadoDao.obterOcorrencias(idTarefa, TiposConstantes.MetodosTipos.TIPIFICACAO_OCORRENCIA, idOcorrencia);
    }


    /**
     * Metodo que permite obter as ocorrencias inseridas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Flowable<List<OcorrenciaRegisto>> obterOcorrenciasRegistadas(int idTarefa) {
        return ocorrenciaResultadoDao.obterOcorrenciasRegistadas(idTarefa, TiposConstantes.MetodosTipos.TIPIFICACAO_OCORRENCIA);
    }


    /**
     * Metodo que permite obter registo de ocorrencia
     * @param idTarefa o identificador da tarefa
     * @param id o identificador da ocorrencia
     * @param idTipo o identificador do tipo de ocorrencia
     * @return uma ocorrencia
     */
    public Single<OcorrenciaBase> obterRegistoOcorrencia(int idTarefa, int id, int idTipo){
        return ocorrenciaResultadoDao.obterOcorrenciaRegistada(idTarefa, TiposConstantes.MetodosTipos.TIPIFICACAO_OCORRENCIA, id, idTipo);
    }



    public Single<Integer> remover(int idTarefa, int id){
        return ocorrenciaResultadoDao.remover(idTarefa, id);
    }


    /**
     * Metodo que permite obter a lista de historico
     * @param id o identificador da ocorrencia
     * @return uma lista de historico
     */
    public Flowable<List<OcorrenciaHistorico>> obterHistorico(int id) {
        return ocorrenciaResultadoDao.obterHistorico(id);
    }

    //-----

















    public Single<Long> inserir(OcorrenciaResultado ocorrencia){
        return ocorrenciaResultadoDao.inserir(ocorrencia);
    }

    public Single<Integer> atualizar(OcorrenciaResultado ocorrencia){
        return ocorrenciaResultadoDao.atualizar(ocorrencia);
    }









    public Flowable<Tipo> obterOcorrencia(int id) {
        return tipoDao.obterTipo(TiposConstantes.TIPIFICACAO_OCORRENCIA, id);
    }

}
