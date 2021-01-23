package com.vvm.sh.documentos.templates.registoVisita.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.titan.pdfdocumentlibrary.util.PdfUtil;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.documentos.eventos.EspacoPreenchimento;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.ImagemUtil;

public class Assinatura extends Section {

    private Rubrica registo;
    private Context contexto;

    public Assinatura(Context contexto, Rubrica registo) {
        this.registo = registo;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{2f, 4.6f, 4f, 2f });
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        try {

            if(registo != null){

                CellConfiguration cellConfiguration = new CellConfiguration();
                cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
                cellConfiguration.verticalAlign = Element.ALIGN_BOTTOM;
                cellConfiguration.event = new EspacoPreenchimento(0f, 10f);

                //data

                Phrase frase = new Phrase(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
                table.addCell(frase, cellConfiguration);

                //assinatura

                Image imagem = PdfUtil.createPdfImage(contexto.getResources(), ImagemUtil.converter(registo.imagem));
                //imagem.scaleToFit(120f, 180f);
                imagem.scaleToFit(120f, 30f);
                table.addCell(imagem, cellConfiguration);

                //nome do técnico

                frase = new Phrase(registo.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
                table.addCell(frase, cellConfiguration);

                //cap

                frase = new Phrase(registo.cap, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
                table.addCell(frase, cellConfiguration);


                //descrições

                Phrase frases [] = {
                        new Phrase(Pdf.Texto.DATA, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase(Pdf.Texto.CLIENTE, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase(Pdf.Texto.TECNICO, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase(Pdf.Texto.CAP_N, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA))
                };

                CellConfiguration cellConfiguration2 = new CellConfiguration();
                cellConfiguration2.horizontalAlign = Element.ALIGN_CENTER;
                table.addLine(frases, cellConfiguration2);
            }

            table.removeBorder();
            table.breakTable(false);
        }
        catch (PdfLineException e) {
            e.printStackTrace();
        }
    }


}
