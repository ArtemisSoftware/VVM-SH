package com.vvm.sh.documentos;

import com.titan.pdfdocumentlibrary.bundle.Template;

public interface DocumentoPdf {

    int PRE_VISUALIZAR_PDF = 1;
    int ENVIAR_PDF = 2;

    Template obterTemplate();
}
