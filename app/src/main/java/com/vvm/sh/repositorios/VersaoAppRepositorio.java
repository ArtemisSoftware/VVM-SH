package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaHigieneApi;
import com.vvm.sh.api.modelos.VersaoApp;

import io.reactivex.Single;

public class VersaoAppRepositorio {


    private final SegurancaHigieneApi api;


    public VersaoAppRepositorio(@NonNull SegurancaHigieneApi api) {
        this.api = api;
    }


    /**
     * Metodo que permite obter a atualizacao da app
     * @return a versao
     */
    public Single<VersaoApp> obterAtualizacao() {
        return api.obterAtualizacao(SegurancaAlimentarApi.HEADER);
    }


}
