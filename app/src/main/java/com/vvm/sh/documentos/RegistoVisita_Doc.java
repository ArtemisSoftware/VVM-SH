package com.vvm.sh.documentos;

import android.content.Context;

import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.documentos.eventos.CabecalhoRodape;
import com.vvm.sh.util.metodos.DiretoriasUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegistoVisita_Doc extends Template {

    private RegistoVisita registoVisita;
    private int idTarefa;

    public RegistoVisita_Doc(Context contexto, int idTarefa, RegistoVisita registoVisita) {
        super(contexto, DiretoriasUtil.obterDiretoria(DiretoriasUtil.DIRETORIA_PDF));

        this.registoVisita = registoVisita;
        this.idTarefa = idTarefa;
    }

    @Override
    protected String getFileName() {
        return "_id__" + idTarefa;
    }

    @Override
    protected List<Chapter> getChapters() {
        List<Chapter> pages = new ArrayList<>();
        pages.add(new Capitulo_RegistaVisita(context, this.registoVisita));
        return pages;
    }

    @Override
    protected PdfPageEventHelper getPageEvent() {
        return new CabecalhoRodape(this.getChapters().get(0).CHAPTER_ID, registoVisita.referencia);
    }

    @Override
    protected void setNewChapterConfigurations(PdfPageEvent pageEvent, int chapterNumber) {

    }

    @Override
    protected void setNewPageConfigurations(PdfPageEvent pageEvent, Chapter chapter, int pageNumber) {
        if(paginacao.containsKey(pageNumber) == false){
            paginacao.put(pageNumber, chapter.CHAPTER_ID);
        }

        ((CabecalhoRodape)pageEvent).setRelations(paginacao);
    }
}
