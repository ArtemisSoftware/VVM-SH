package com.vvm.sh.documentos.informacaoSst;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.documentos.informacaoSst.capitulos.Capitulo_InformacaoSst;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.util.metodos.DiretoriasUtil;

import java.util.ArrayList;
import java.util.List;

public class InformacaoSst extends Template {

    private DadosInformacaoSst dadosInformacaoSst;
    private int idTarefa;

    public InformacaoSst(Context context, int idTarefa, DadosInformacaoSst dadosInformacaoSst) {
        super(context, DiretoriasUtil.obterDiretoria(DiretoriasUtil.DIRETORIA_PDF));

        this.dadosInformacaoSst = dadosInformacaoSst;
        this.idTarefa = idTarefa;
    }

    @Override
    protected String getFileName() {
        return "_id__" + idTarefa;
    }

    @Override
    protected List<Chapter> getChapters() {
        List<Chapter> pages = new ArrayList<>();
        pages.add(new Capitulo_InformacaoSst(context, this.dadosInformacaoSst));
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
