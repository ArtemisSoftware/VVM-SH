package com.vvm.sh.documentos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.api.modelos.pedido.Codigo;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface OnDocumentoListener {

    enum AcaoDocumento{
        PRE_VISUALIZAR_PDF,
        ENVIAR_PDF,
        ENVIAR_PDF__DADOS_FTP,
    }



    interface OnVisualizar {

        void executarPdf(Context contexto, int idTarefa, String idUtilizador, OnDocumentoListener.AcaoDocumento acao);

        void executarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnDocumentoListener.AcaoDocumento acao);


        Maybe<DadosTemplate> obterPdf(int idTarefa, int idAtividade, String idUtilizador);
        Single<Codigo> uploadRelatorio(int idTarefa, String caminhoPdf);
        Single<Integer> sincronizar(int idTarefa, int idAtividade);
        void concluirPdf(int idTarefa, int idAtividade);
    }
}
