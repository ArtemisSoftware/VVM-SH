package com.vvm.sh.documentos.certificadoTratamento;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.titan.pdfdocumentlibrary.elements.TemplateConfiguration;
import com.vvm.sh.documentos.certificadoTratamento.capitulos.Capitulo_CertificadoTratamento;
import com.vvm.sh.documentos.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.documentos.informacaoSst.capitulos.Capitulo_InformacaoSst;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.util.metodos.DiretoriasUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CertificadoTratamento extends Template {


    private DadosCertificadoTratamento dadosCertificadoTratamento;
    private int idTarefa, idAtividade;


    public CertificadoTratamento(Context context, int idTarefa, int idAtividade, DadosCertificadoTratamento dadosCertificadoTratamento) {
        super(context, DiretoriasUtil.obterDiretoria(DiretoriasUtil.DIRETORIA_PDF));
        this.dadosCertificadoTratamento = dadosCertificadoTratamento;
        this.idTarefa = idTarefa;
        this.idAtividade = idAtividade;
    }

    @Override
    protected String getFileName() {
        return "_id__" + idTarefa + "_idAct__" + idAtividade;
    }

    @Override
    protected List<Chapter> getChapters() {
        List<Chapter> pages = new ArrayList<>();
        pages.add(new Capitulo_CertificadoTratamento(context, this.dadosCertificadoTratamento));
        return pages;
    }

    @Override
    protected PdfPageEventHelper getPageEvent() {
        return null;
    }

    @Override
    protected void setNewChapterConfigurations(PdfPageEvent pageEvent, int chapterNumber) {

    }

    @Override
    protected void setNewPageConfigurations(PdfPageEvent pageEvent, Chapter chapter, int pageNumber) {

    }
}
