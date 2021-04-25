package com.vvm.sh.ui.transferencias.modelos;

import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.util.mapeamento.UploadMapping;

public class DadosUploadSH extends DadosUpload{

    public DadosUploadSH(String idUtilizador) {
        super(idUtilizador);
    }

    @Override
    protected BlocoDados obterBlocoDados() {

        for (int index = 0; index < dados.size(); ++index) {
            dados.get(index).numeroFicheirosFotos = numeroFicheirosImagens + "";
            dados.get(index).versaoApp = this.versao;
        }

        return UploadMapping.INSTANCE.mapDadosSH(this);
    }
}
