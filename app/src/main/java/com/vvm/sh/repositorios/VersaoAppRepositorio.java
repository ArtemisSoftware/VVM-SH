package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaHigieneApi;
import com.vvm.sh.api.modelos.pedido.IVersaoApp;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class VersaoAppRepositorio {


    private final SegurancaHigieneApi api;


    public VersaoAppRepositorio(@NonNull SegurancaHigieneApi api) {
        this.api = api;
    }


    /**
     * Metodo que permite obter a atualizacao da app
     * @return a versao
     */
    public Single<IVersaoApp> obterAtualizacao(String idUtilizador) {
        return api.obterAtualizacao(SegurancaAlimentarApi.HEADER)
                .map(new Function<IVersaoApp, IVersaoApp>() {
                    @Override
                    public IVersaoApp apply(IVersaoApp versaoApp) throws Exception {

                        versaoApp.fixarUtilizador(idUtilizador);
                        return versaoApp;
                    }
                });
    }


}
