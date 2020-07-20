package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.UtilizadorResposta;

import io.reactivex.Single;

public class AutenticacaoRepositorio {


    private final SegurancaAlimentarApi api;


    public AutenticacaoRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }



    public Single<UtilizadorResposta> obterUtilizadores() {
        return api.obterUtilizadores();
    }

}
