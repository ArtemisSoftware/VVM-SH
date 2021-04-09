package com.vvm.sh.ui.transferencias.adaptadores;

import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.util.AtualizacaoUI_;

public interface OnTransferenciaListener {

    void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI);
    void terminarTransferencia();
    void erroTransferencia();


    interface OnUploadListener {
        void atualizar(AtualizacaoUI_ atualizacaoUI);
        void atualizar(DadosUpload dadosSA, DadosUpload dadosSH);
        void erroDados();
    }
}
