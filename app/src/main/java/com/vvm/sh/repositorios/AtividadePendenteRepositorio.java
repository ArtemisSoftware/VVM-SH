package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class AtividadePendenteRepositorio {


    private final AtividadePendenteDao atividadePendenteDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AtividadePendenteRepositorio(@NonNull AtividadePendenteDao atividadePendenteDao,
                                        @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.atividadePendenteDao = atividadePendenteDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa) {
        return atividadePendenteDao.obterAtividades(idTarefa);
    }

    public Single<Long> inserir(AtividadePendenteResultado atividade) {
        return atividadePendenteDao.inserir(atividade);
    }


    public Single<Integer> atualizar(AtividadePendenteResultado atividade) {
        return atividadePendenteDao.atualizar(atividade);
    }

    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.MetodosTipos.TIPOS_ANOMALIA);
    }


    public Maybe<AtividadePendenteRegisto> obterAtividadeResultado(int id) {
        return atividadePendenteDao.obterAtividade(id);
    }
}
