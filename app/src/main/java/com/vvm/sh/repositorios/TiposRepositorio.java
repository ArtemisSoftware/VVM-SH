package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.TipoResposta;

import io.reactivex.Single;

public class TiposRepositorio {



    private final SegurancaAlimentarApi api;


    public TiposRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }



    public Single<TipoResposta> obterTipo(String metodo) {
        return api.obterTipo(metodo);
    }



}
