package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;

public class AutenticacaoRepositorio {


    private final SegurancaAlimentarApi api;


    public AutenticacaoRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }

}
