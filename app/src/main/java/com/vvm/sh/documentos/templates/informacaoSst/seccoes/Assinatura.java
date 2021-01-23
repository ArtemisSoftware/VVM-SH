package com.vvm.sh.documentos.templates.informacaoSst.seccoes;

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

                //assinatura

                Image imagem = PdfUtil.createPdfImage(contexto.getResources(), ImagemUtil.converter(registo.imagem));
                imagem.scaleToFit(120f, 30f);
                table.addCell(imagem, cellConfiguration);


                //data

                Phrase frase = new Phrase(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
                table.addCell(frase, cellConfiguration);


                //descrições

                Phrase frases [] = {
                        new Phrase(contexto.getString(R.string.assinatura_representante_empresa), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                        new Phrase(Pdf.Texto.DATA, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA))
                };

                CellConfiguration cellConfiguration2 = new CellConfiguration();
                cellConfiguration2.horizontalAlign = Element.ALIGN_LEFT;
                table.addLine(frases, cellConfiguration2);

                table.addLine(new Phrase(contexto.getString(R.string.declaracao_informacao_sst), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)), cellConfiguration2);

            }

            //--table.removeBorder();
            //--table.breakTable(false);
        }
        catch (PdfLineException e) {
            e.printStackTrace();
        }
    }


}
