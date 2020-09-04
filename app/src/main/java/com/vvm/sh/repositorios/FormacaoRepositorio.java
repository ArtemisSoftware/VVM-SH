package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteResultadoDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.ResultadoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.baseDados.dao.ImagemResultadoDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class FormacaoRepositorio {

    private final FormandoDao formandoDao;
    private final AcaoFormacaoDao acaoFormacaoDao;
    private final TipoDao tipoDao;
    private final ImagemResultadoDao imagemResultadoDao;
    private final AtividadePendenteResultadoDao atividadePendenteResultadoDao;
    public final ResultadoDao resultadoDao;

    public FormacaoRepositorio(@NonNull FormandoDao formandoDao, @NonNull AcaoFormacaoDao acaoFormacaoDao,
                               @NonNull AtividadePendenteResultadoDao atividadePendenteResultadoDao, ImagemResultadoDao imagemResultadoDao,
                               @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.formandoDao = formandoDao;
        this.acaoFormacaoDao = acaoFormacaoDao;
        this.tipoDao = tipoDao;
        this.imagemResultadoDao = imagemResultadoDao;
        this.atividadePendenteResultadoDao = atividadePendenteResultadoDao;
        this.resultadoDao = resultadoDao;
    }

    public Flowable<AcaoFormacao> obterAcaoFormacao(int idAtividade) {
        return acaoFormacaoDao.obterAcaoFormacao(idAtividade, TiposUtil.MetodosTipos.CURSOS);
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



    public Single<Long> inserirAssinatura(ImagemResultado imagem) {
        return imagemResultadoDao.inserir(imagem);
    }

    public Single<Integer> removerAssinatura(int idFormando) {
        return imagemResultadoDao.remover(idFormando, Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO);
    }




    public Flowable<List<Tipo>> obterCursos() {
        return tipoDao.obterTipos(TiposUtil.MetodosTipos.CURSOS);
    }


    public Single<Integer> removerAtividade(int idAtividade) {
        return atividadePendenteResultadoDao.remover(idAtividade);
    }



}
