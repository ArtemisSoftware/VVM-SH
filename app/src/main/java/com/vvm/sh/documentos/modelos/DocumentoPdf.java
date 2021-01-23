package com.vvm.sh.documentos.modelos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;

public interface DocumentoPdf {

    int PRE_VISUALIZAR_PDF = 1;
    int ENVIAR_PDF = 2;


    /**
     * Metodo que permite obter o template necessário para gerar um pdf
     * @return
     */
    Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate registo);
}
