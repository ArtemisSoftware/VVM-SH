package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.OcorrenciaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaBase;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class OcorrenciaRepositorio implements Repositorio<OcorrenciaResultado>{

    private final int idApi;
    private final OcorrenciaDao ocorrenciaDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public OcorrenciaRepositorio(int idApi, @NonNull OcorrenciaDao ocorrenciaDao,
                                 @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.ocorrenciaDao = ocorrenciaDao;
        this.resultadoDao = resultadoDao;
        this.tipoDao = tipoDao;
    }



    /**
     * Metodo que permite obter as ocorrencias associadas à tarefa
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Single<List<Ocorrencia>> obterOcorrencias(int idTarefa) {
        return ocorrenciaDao.obterOcorrencias(idTarefa);
    }


    /**
     * Obter grupos de ocorrencias
     * @return uma lista
     */
    public Single<List<Tipo>> obterOcorrencias() {
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA, idApi, "");
    }



    /**
     * Metodo que permite obter as ocorrencias possiveis de serem registadas
     * @param idTarefa o identificador da tarefa
     * @param idOcorrencia o identificador do grupo de ocorrencias
     * @return uma lista
     */
    public Observable<List<OcorrenciaBase>> obterRegistoOcorrencias(int idTarefa, int idOcorrencia) {
        return ocorrenciaDao.obterOcorrencias(idTarefa, idOcorrencia, idApi);
    }


    /**
     * Metodo que permite obter as ocorrencias inseridas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Observable<List<OcorrenciaRegisto>> obterOcorrenciasRegistadas(int idTarefa) {
        return ocorrenciaDao.obterOcorrenciasRegistadas(idTarefa, idApi);
    }


    /**
     * Metodo que permite obter registo de ocorrencia
     * @param idTarefa o identificador da tarefa
     * @param id o identificador da ocorrencia
     * @param idTipo o identificador do tipo de ocorrencia
     * @return uma ocorrencia
     */
    public Single<OcorrenciaBase> obterRegistoOcorrencia(int idTarefa, int id, int idTipo){
        return ocorrenciaDao.obterOcorrenciaRegistada(idTarefa, id, idTipo, idApi);
    }



    public Single<Integer> remover(int idTarefa, int id){
        return ocorrenciaDao.remover(idTarefa, id);
    }


    /**
     * Metodo que permite obter a lista de historico
     * @param id o identificador da ocorrencia
     * @return uma lista de historico
     */
    public Single<List<OcorrenciaHistorico>> obterHistorico(int id) {
        return ocorrenciaDao.obterHistorico(id);
    }



    @Override
    public Single<Long> inserir(OcorrenciaResultado ocorrencia){
        return ocorrenciaDao.inserir(ocorrencia);
    }

    @Override
    public Single<Integer> atualizar(OcorrenciaResultado ocorrencia){
        return ocorrenciaDao.atualizar(ocorrencia);
    }

    @Override
    public Single<Integer> remover(OcorrenciaResultado item) {
        return null;
    }

}
