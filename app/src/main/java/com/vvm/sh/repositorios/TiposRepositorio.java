package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.baseDados.AtualizacaoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;

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



    public Single<TipoResposta> obterTipo(String metodo) {
        return api.obterTipo(metodo);
    }



    public void atualizarTipo(String tipo/*, Atualizacao atualizacao*/){
        atualizacaoDao.remover(tipo);
        //--atualizacaoDao.inserir(atualizacao);
        //--tipoDao.
    }

}
