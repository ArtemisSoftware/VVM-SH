package com.vvm.sh.documentos.certificadoTratamento.eventos;

import android.content.Context;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.vvm.sh.documentos.certificadoTratamento.seccoes.Cabecalho;

public class CabecalhoRodape extends PdfPageEventHelper {

    private Cabecalho cabecalho;


    public CabecalhoRodape(Context contexto){

        cabecalho = new Cabecalho(contexto);
    }


    private void fixarCabecalho(PdfWriter writer, Document document){

        float height = 0;

        //--cabecalho.getSection().getPdfTable().writeSelectedRows(0, -1, document.left(), document.top() + ((document.topMargin() + height) / 2), writer.getDirectContent());
    }



    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        fixarCabecalho(writer, document);
    }




}
