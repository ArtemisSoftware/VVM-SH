package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AcaoFormacaoDao;
import com.vvm.sh.baseDados.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.FormandoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class FormacaoRepositorio {

    private final FormandoDao formandoDao;
    private final AcaoFormacaoDao acaoFormacaoDao;
    private final TipoDao tipoDao;
    private final AtividadePendenteResultadoDao atividadePendenteResultadoDao;
    public final ResultadoDao resultadoDao;

    public FormacaoRepositorio(@NonNull FormandoDao formandoDao, @NonNull AcaoFormacaoDao acaoFormacaoDao,
                               @NonNull AtividadePendenteResultadoDao atividadePendenteResultadoDao, @NonNull TipoDao tipoDao,
                               @NonNull ResultadoDao resultadoDao) {
        this.formandoDao = formandoDao;
        this.acaoFormacaoDao = acaoFormacaoDao;
        this.tipoDao = tipoDao;
        this.atividadePendenteResultadoDao = atividadePendenteResultadoDao;
        this.resultadoDao = resultadoDao;
    }

    public Maybe<AcaoFormacao> obterAcaoFormacao(int idAtividade) {
        return acaoFormacaoDao.obterAcaoFormacao(idAtividade, TiposConstantes.CURSOS);
    }

    public Single<Long> inserirAcaoFormacao(AcaoFormacaoResultado acaoFormacao) {
        return acaoFormacaoDao.inserir(acaoFormacao);
    }

    public Single<Integer> atualizarAcaoFormacao(AcaoFormacaoResultado acaoFormacao) {
        return acaoFormacaoDao.atualizar(acaoFormacao);
    }



    public Flowable<List<Formando>> obterFormandos(int idAtividade) {
        return formandoDao.obterFormandos(idAtividade);
    }

    public Maybe<Formando> obterFormando(int id) {
        return formandoDao.obterFormando(id);
    }

    public Single<Long> inserirFormando(FormandoResultado formando) {
        return formandoDao.inserir(formando);
    }

    public Single<Integer> atualizarFormando(FormandoResultado formando) {
        return formandoDao.atualizar(formando);
    }




    public Flowable<List<Tipo>> obterCursos() {
        return tipoDao.obterTipos(TiposConstantes.CURSOS);
    }


    public Single<Integer> removerAtividade(int idAtividade) {
        return atividadePendenteResultadoDao.remover(idAtividade);
    }



}
