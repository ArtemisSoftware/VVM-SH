package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.baseDados.AtualizacaoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class TiposRepositorio {



    private final SegurancaAlimentarApi api;
    private final AtualizacaoDao atualizacaoDao;
    private final TipoDao tipoDao;

    public TiposRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull AtualizacaoDao atualizacaoDao, @NonNull TipoDao tipoDao) {
        this.api = api;
        this.atualizacaoDao = atualizacaoDao;
        this.tipoDao = tipoDao;
    }


    public Flowable<List<Colecao>> obterTipos() {
        return tipoDao.obterTipos();
    }



    public Single<TipoResposta> obterTipo(String metodo) {
        return api.obterTipo(metodo);
    }


    /**
     * Metodo que permite atualizar um tipo
     * @param tipo o nome do tipo
     * @param atualizacao os dados da atualizacao
     * @param tipos os dados do tipo
     */
    public void atualizarTipo(String tipo, Atualizacao atualizacao, List<Tipo> tipos){
        atualizacaoDao.remover(tipo);
        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(tipos);
    }

}
