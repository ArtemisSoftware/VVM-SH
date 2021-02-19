package com.vvm.sh.documentos.informacaoSst.eventos;

import android.content.Context;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.vvm.sh.documentos.informacaoSst.modelos.Cabecalho;
import com.vvm.sh.documentos.informacaoSst.modelos.Rodape;

import java.util.List;

public class CabecalhoRodape extends PdfPageEventHelper {

    private Cabecalho cabecalho;
    private Rodape rodape;

    public CabecalhoRodape(Context contexto, List<String> rodapes){

        cabecalho = new Cabecalho(contexto);
        rodape = new Rodape(rodapes);
    }

    private void fixarCabecalho(PdfWriter writer, Document document){

        float height = cabecalho.getSection().getPdfTable().getTotalHeight();

        cabecalho.getSection().getPdfTable().writeSelectedRows(0, -1, 0, document.top() + 70 /* + ((document.topMargin() + lolo) / 2) +5*/, writer.getDirectContent());
    }


    /**
     * Metodo que permite fixar o rodape
     * @param writer
     * @param document
     */
    private void fixarRodape(PdfWriter writer, Document document){

        PdfContentByte cb = writer.getDirectContent();

        PdfContentByte canvasRodape_1 = writer.getDirectContent();
        canvasRodape_1.beginMarkedContentSequence(PdfName.ARTIFACT);
        rodape.gerarPrimeiroRodape().getPdfTable().writeSelectedRows(0, -1, document.left(), 90, canvasRodape_1);
        canvasRodape_1.endMarkedContentSequence();


        PdfContentByte canvasRodape_2 = writer.getDirectContent();
        canvasRodape_2.beginMarkedContentSequence(PdfName.ARTIFACT);
        rodape.gerarSegundoRodape().getPdfTable().writeSelectedRows(0, -1, 0, /*30*/32, canvasRodape_2);
        canvasRodape_2.endMarkedContentSequence();
    }



    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        fixarCabecalho(writer, document);
        fixarRodape(writer, document);
    }
}
