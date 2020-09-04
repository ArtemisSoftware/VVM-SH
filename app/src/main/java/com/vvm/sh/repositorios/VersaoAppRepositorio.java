package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.VersaoApp;

import io.reactivex.Single;

public class VersaoAppRepositorio {


    private final SegurancaAlimentarApi api;


    public VersaoAppRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }



    public Single<VersaoApp> obterAtualizacao() {
        return api.obterAtualizacao(SegurancaAlimentarApi.HEADER);
    }


}
