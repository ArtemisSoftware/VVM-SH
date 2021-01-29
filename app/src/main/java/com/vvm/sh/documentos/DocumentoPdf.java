package com.vvm.sh.documentos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.documentos.DadosTemplate;

import io.reactivex.Maybe;

public interface DocumentoPdf {

    int PRE_VISUALIZAR_PDF = 1;
    int ENVIAR_PDF = 2;

    enum AcaoDocumento{
        PRE_VISUALIZAR_PDF, ENVIAR_PDF

    }

    /**
     * Metodo que permite obter o template necessário para gerar um pdf
     * @return
     */
    Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate registo);

    Maybe<DadosTemplate> obterPdf(int idTarefa, int idAtividade, String idUtilizador);
    void gerarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, int acao);

    void sincronizar(int idTarefa, int idAtividade);
}


