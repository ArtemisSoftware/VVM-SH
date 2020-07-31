package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.baseDados.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class AtividadePendenteRepositorio {

    private final AtividadePendenteDao atividadePendenteDao;
    private final AtividadePendenteResultadoDao atividadePendenteResultadoDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AtividadePendenteRepositorio(@NonNull AtividadePendenteDao atividadePendenteDao, @NonNull AtividadePendenteResultadoDao atividadePendenteResultadoDao,
                                        @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.atividadePendenteDao = atividadePendenteDao;
        this.atividadePendenteResultadoDao = atividadePendenteResultadoDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    public Flowable<List<AtividadePendenteRegisto>> obterAtividades(int idTarefa) {
        return atividadePendenteDao.obterAtividades(idTarefa);
    }

    public Single<Long> inserir(AtividadePendenteResultado atividade) {
        return atividadePendenteResultadoDao.inserir(atividade);
    }


    public Single<Integer> atualizar(AtividadePendenteResultado atividade) {
        return atividadePendenteResultadoDao.atualizar(atividade);
    }

    public Flowable<List<Tipo>> obterTiposAnomalias(){
        return tipoDao.obterTipos(TiposConstantes.TIPOS_ANOMALIA);
    }


    public Maybe<AtividadePendenteResultado> obterAtividadeResultado(int id) {
        return atividadePendenteResultadoDao.obterAtividade(id);
    }
}
