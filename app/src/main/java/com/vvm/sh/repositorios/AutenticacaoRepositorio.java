package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;

import io.reactivex.Single;

public class AutenticacaoRepositorio {


    private final SegurancaAlimentarApi api;


    public AutenticacaoRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }



//    public Single<VersaoApp> obterAtualizacao() {
//        return api.obterAtualizacao();
//    }

}
