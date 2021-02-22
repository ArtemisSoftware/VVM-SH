package com.vvm.sh.documentos.registoVisita.eventos;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.vvm.sh.documentos.registoVisita.modelos.Cabecalho;
import com.vvm.sh.documentos.registoVisita.modelos.Rodape;

import java.util.HashMap;

public class CabecalhoRodape extends PdfPageEventHelper {

    private Cabecalho cabecalho;
    private Rodape rodape;

    private HashMap<Integer, Integer> pagination;

    private PdfTemplate template;
    private Image total;

    public int chapterId;


    public CabecalhoRodape(Context contexto, int chapterId, String referencia){

        this.chapterId = chapterId;
        cabecalho = new Cabecalho(contexto);
        rodape = new Rodape(contexto, referencia);
    }


    public void setRelations(HashMap<Integer, Integer> pagination) {
        this.pagination = pagination;
    }



    private void fixarCabecalho(PdfWriter writer, Document document){

        PdfContentByte pdfContentByte = writer.getDirectContent();
        cabecalho.getSection().getPdfTable().writeSelectedRows(0, -1, 36, document.top() + 60 /* + ((document.topMargin() + lolo) / 2) +5*/, pdfContentByte);
    }




    /**
     * Metodo que permite fixar o rodape
     * @param writer
     * @param document
     */
    private void fixarRodape(PdfWriter writer, Document document){

        PdfContentByte canvasReferencia = writer.getDirectContent();
        canvasReferencia.beginMarkedContentSequence(PdfName.ARTIFACT);
        rodape.obterTabelaReferencia().getPdfTable().writeSelectedRows(0, -1, 15.7f* 36, 75, canvasReferencia);
        canvasReferencia.endMarkedContentSequence();

        PdfContentByte canvas = writer.getDirectContent();
        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
        rodape.obterTabelaNumeroPagina(writer.getPageNumber(), total).getPdfTable().writeSelectedRows(0, -1, 36, /*30*/90, canvas);
        canvas.endMarkedContentSequence();
    }






    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {

        template = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(template);
            total.setRole(PdfName.ARTIFACT);
        }
        catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }

    }



    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        fixarCabecalho(writer, document);
        fixarRodape(writer, document);
    }



    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {

        FontConfiguration font = new FontConfiguration();
        ColumnText.showTextAligned(template, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber()/* -1*/), font.getFont(7+6,true, BaseColor.GRAY)), 2, /*4*/3.6f, 0);
    }

}
