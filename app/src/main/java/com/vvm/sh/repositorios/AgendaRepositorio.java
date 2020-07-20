package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.Sessao;

import io.reactivex.Single;

public class AgendaRepositorio {

    private final SegurancaAlimentarApi api;

    public AgendaRepositorio(@NonNull SegurancaAlimentarApi api) {
        this.api = api;
    }

    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<Sessao> obterUtilizadores(String idUtilizador) {
        return api.obterTrabalho(idUtilizador);
    }


}
