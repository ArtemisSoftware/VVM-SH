package com.vvm.sh.documentos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;

import io.reactivex.Maybe;

public interface OnDocumentoListener {

    enum AcaoDocumento{
        PRE_VISUALIZAR_PDF, ENVIAR_PDF
    }


    interface OnCriar {

        void preVisualizarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnVisualizar listener);

        void enviarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnVisualizar listener);

        void gerarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnVisualizar listener, OnDocumentoListener.AcaoDocumento acao);
    }

    interface OnVisualizar {
        Maybe<DadosTemplate> obterPdf(int idTarefa, int idAtividade, String idUtilizador);
        void sincronizar(int idTarefa, int idAtividade);
    }
}
