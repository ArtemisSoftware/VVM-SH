package com.vvm.sh.documentos.informacaoSst.seccoes;

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
import com.vvm.sh.documentos.Rubrica;
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
        return new Table(new float[]{4.6f, 2f, 4f });
    }

    @Override
    protected void populateSection() {


        FontConfiguration fontConfiguration = new FontConfiguration();

        if (registo != null) {

            try {

                CellConfiguration cellConfiguration_1 = new CellConfiguration();
                cellConfiguration_1.verticalAlign = Element.ALIGN_BOTTOM;
                cellConfiguration_1.horizontalAlign = Element.ALIGN_CENTER;
                cellConfiguration_1.event = new EspacoPreenchimento(0f, 10f);

                //ASSINATURA

                Image imagem = PdfUtil.createPdfImage(contexto.getResources(), ImagemUtil.converter(registo.imagem));
                imagem.scaleToFit(220f, 130f);
                table.addCell(imagem, cellConfiguration_1);


                //espaço
                table.addEmptyCell();

                //data
                Phrase titulo = new Phrase(DatasUtil.converterData(registo.data, DatasUtil.FORMATO_DD_MM_YYYY), fontConfiguration.getFont(Pdf.Fontes.FONTE_9));
                table.addCell(titulo, cellConfiguration_1);


                //notas

                CellConfiguration cellConfiguration_2 = new CellConfiguration();
                cellConfiguration_2.horizontalAlign = Element.ALIGN_CENTER;

                Phrase frases[] = {
                        new Phrase(contexto.getString(R.string.assinatura_representante_empresa), fontConfiguration.getFont(Pdf.Fontes.FONTE_8)),
                        new Phrase("", fontConfiguration.getFont(Pdf.Fontes.FONTE_8)),
                        new Phrase(contexto.getString(R.string.data), fontConfiguration.getFont(Pdf.Fontes.FONTE_8))
                };

                table.addLine(frases, cellConfiguration_2);

                table.addEmptyLine();



                //declaracao

                CellConfiguration cellConfiguration_3 = new CellConfiguration();
                cellConfiguration_3.horizontalAlign = Element.ALIGN_LEFT;
                table.addLine(new Phrase(contexto.getString(R.string.declaracao_informacao_sst), fontConfiguration.getFont(Pdf.Fontes.FONTE_8)), cellConfiguration_3);


                table.removeBorder();


            } catch (PdfLineException e) {
                e.printStackTrace();
            }

        }
    }

}
