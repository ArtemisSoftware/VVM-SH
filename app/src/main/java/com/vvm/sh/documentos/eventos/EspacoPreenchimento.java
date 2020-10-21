package com.vvm.sh.documentos.eventos;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class EspacoPreenchimento implements PdfPCellEvent {

    private float alinhamentoVertical, alinhamentoHorizontal;


    public EspacoPreenchimento() {
        alinhamentoVertical = 4.5f;
        alinhamentoHorizontal = 0f;
    }

    public EspacoPreenchimento(float alinhamentoVertical, float alinhamentoHorizontal) {
        this.alinhamentoVertical = alinhamentoVertical;
        this.alinhamentoHorizontal = alinhamentoHorizontal;
    }


    @Override
    public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {

        PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];

        canvas = canvases[PdfPTable.LINECANVAS];
        /*
        CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
        canvas.setColorStroke(magentaColor);
        */


        canvas.moveTo(position.getLeft() + alinhamentoHorizontal, /*position.getTop()*/position.getBottom() + alinhamentoVertical);
        canvas.lineTo(position.getRight() - alinhamentoHorizontal, position.getBottom() + alinhamentoVertical);
        canvas.setLineWidth(0.1f);
        canvas.stroke();
    }
}
