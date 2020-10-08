package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AtividadePendenteRepositorio implements Repositorio<AtividadePendenteResultado>{


    private final int idApi;
    private final AtividadePendenteDao atividadePendenteDao;
    private final ProcessoProdutivoDao processoProdutivoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AtividadePendenteRepositorio(int idApi, @NonNull AtividadePendenteDao atividadePendenteDao, @NonNull ProcessoProdutivoDao processoProdutivoDao,
                                        @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.idApi = idApi;
        this.atividadePendenteDao = atividadePendenteDao;
        this.processoProdutivoDao = processoProdutivoDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter as atividades
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades
     */
    public Observable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa) {
        return atividadePendenteDao.obterAtividades(idTarefa);
    }

    public Maybe<AtividadePendenteRegisto> obterAtividadeResultado(int id) {
        return atividadePendenteDao.obterAtividade(id);
    }

    public Maybe<ProcessoProdutivoResultado> obterProcessiProdutivo(int idAtividade) {
        return processoProdutivoDao.obterProcessiProdutivo(idAtividade);
    }


    public Single<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_ANOMALIAS, idApi);
    }







    public Single<Long> inserir(ProcessoProdutivoResultado registo) {
        return processoProdutivoDao.inserir(registo);
    }


    public Single<Integer> atualizar(ProcessoProdutivoResultado registo) {
        return processoProdutivoDao.atualizar(registo);
    }



    @Override
    public Single<Long> inserir(AtividadePendenteResultado atividade) {
        return atividadePendenteDao.inserir(atividade);
    }

    @Override
    public Single<Integer> atualizar(AtividadePendenteResultado atividade) {
        return atividadePendenteDao.atualizar(atividade);
    }

    @Override
    public Single<Integer> remover(AtividadePendenteResultado item) {
        return null;
    }


}
