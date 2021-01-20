package com.vvm.sh.documentos.certificadoTratamento.seccoes;

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
import com.vvm.sh.R;
import com.vvm.sh.documentos.eventos.EspacoPreenchimento;
import com.vvm.sh.documentos.modelos.Rubrica;
import com.vvm.sh.util.constantes.Pdf;
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
        return new Table(new float[]{4.6f, 1f, 4.4f });
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        if(registo != null) {

            try {
                table.addEmptyCell(3);


                CellConfiguration cellConfiguration = new CellConfiguration();
                cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
                cellConfiguration.colSpan = 3;


                Phrase titulo = new Phrase(contexto.getString(R.string.data_).toUpperCase().toUpperCase() + " " + registo.data, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE));
                table.addCell(titulo, cellConfiguration);


                table.addEmptyCell(3);


                CellConfiguration cellConfiguration_1 = new CellConfiguration();
                cellConfiguration_1.verticalAlign = Element.ALIGN_BOTTOM;
                cellConfiguration_1.horizontalAlign = Element.ALIGN_CENTER;
                cellConfiguration_1.event = new EspacoPreenchimento(0f, 10f);


                //ASSINATURA

                Image imagem = PdfUtil.createPdfImage(contexto.getResources(), ImagemUtil.converter(registo.imagem));
                imagem.scaleToFit(120f, 30f);
                table.addCell(imagem, cellConfiguration);


                //espaço
                table.addEmptyCell();


                //nome do tecnico
                Phrase frase = new Phrase(registo.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
                table.addCell(frase, cellConfiguration_1);


                //notas

                CellConfiguration cellConfiguration_2 = new CellConfiguration();
                cellConfiguration_2.horizontalAlign = Element.ALIGN_CENTER;

                Phrase frases[] = {
                        new Phrase(contexto.getString(R.string.pelo_cliente), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase("", fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase(contexto.getString(R.string.tecnico_seguranca_alimentar), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA))
                };

                table.addLine(frases, cellConfiguration_2);


            } catch (PdfLineException e) {
                e.printStackTrace();
            }

            table.removeBorder();
        }
    }
}
