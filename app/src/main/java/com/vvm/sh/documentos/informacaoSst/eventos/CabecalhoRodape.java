package com.vvm.sh.documentos.informacaoSst.eventos;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.vvm.sh.documentos.registoVisita.modelos.Rodape;

import java.util.List;

public class CabecalhoRodape extends PdfPageEventHelper {


    public CabecalhoRodape(List<String> rodapes){

//        this.chapterId = chapterId;
//        rodape = new Rodape(referencia);
    }



    /**
     * Metodo que permite fixar o rodape
     * @param writer
     * @param document
     */
    private void fixarRodape(PdfWriter writer, Document document){

        PdfContentByte cb = writer.getDirectContent();

//        PdfContentByte canvasReferencia = writer.getDirectContent();
//        canvasReferencia.beginMarkedContentSequence(PdfName.ARTIFACT);
//        rodape.obterTabelaReferencia().getPdfTable().writeSelectedRows(0, -1, 15.7f* 36, 75, canvasReferencia);
//        canvasReferencia.endMarkedContentSequence();
//
//
//        PdfContentByte canvas = writer.getDirectContent();
//        canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
//        rodape.adicionarNumeroPagina(writer.getPageNumber(), total);
//        rodape.getSection().getPdfTable().writeSelectedRows(0, -1, 36, /*30*/90, canvas);
//        canvas.endMarkedContentSequence();
    }


}
