package com.vvm.sh.ui.transferencias.modelos;

import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.util.mapeamento.UploadMapping;

public class DadosUploadSA extends DadosUpload{

    public DadosUploadSA(String idUtilizador) {
        super(idUtilizador);
    }

    @Override
    protected BlocoDados obterBlocoDados() {
        return UploadMapping.INSTANCE.mapDadosSA(this);
    }
}
