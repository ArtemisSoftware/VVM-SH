package com.vvm.sh.documentos.eventos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.vvm.sh.util.constantes.Pdf;

public class TituloBorda implements PdfPCellEvent {

    private String titulo;

    private float alinhamento_Esquerda, alinhamento_Topo;
    private BaseColor corFundo;
    private Phrase frase;

    public TituloBorda(String title) {
        this.titulo = title;

        this.alinhamento_Esquerda = 5;
        this.alinhamento_Topo = 5;
        this.corFundo = BaseColor.LIGHT_GRAY;

        FontConfiguration fontConfiguration = new FontConfiguration();

        Chunk chunk = new Chunk(titulo, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE));
        chunk.setBackground(corFundo);
        frase = new Phrase(chunk);

    }

    public TituloBorda(String titulo, float alinhamento_Esquerda, float alinhamento_Topo) {
        this.titulo =titulo;

        this.alinhamento_Esquerda = alinhamento_Esquerda;
        this.alinhamento_Topo = alinhamento_Topo;
        this.corFundo = BaseColor.WHITE;
        FontConfiguration fontConfiguration = new FontConfiguration();
        Chunk chunk = new Chunk(titulo, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));
        chunk.setBackground(corFundo);
        frase = new Phrase(chunk);
    }


    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {

        PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT,  frase, position.getLeft(alinhamento_Esquerda), position.getTop(alinhamento_Topo), 0);

    }
}