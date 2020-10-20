package com.vvm.sh.documentos;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.util.constantes.Pdf;

public class Assinatura extends Section {

    private ImagemResultado registo;

    public Assinatura(ImagemResultado registo) {
        this.registo = registo;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        try {
            table.addCell(obterTabelaRubrica());
            //--table.quebrarTabela(false);
        } catch (PdfLineException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo que permite obter a tabela de rubrica
     * @return uma tabela
     * @throws PdfLineException
     * @throws CloneNotSupportedException
     */
    private Table obterTabelaRubrica() throws PdfLineException /*throws Pdf_Exception, CloneNotSupportedException*/ {

        FontConfiguration fontConfiguration = new FontConfiguration();
        Table tabela = new Table(new float[]{2f, 4.6f, 4f, 2f });


        if(registo != null){

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
            cellConfiguration.verticalAlign = Element.ALIGN_BOTTOM;
            //--cellConfiguration.setEvent(new EspacoPreenchimento(0f, 10f));




//            Phrase frase = new Phrase(registo.obterData(), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
//            tabela.addCell(frase, cellConfiguration);
//
//            Image imagem = MetodosPdf.imagemPdf(contexto, registo.obterImagem());
//            imagem.scaleToFit(120f, 180f);
//            tabela.addCell(imagem, formato);
//
//            frase = new Phrase(registo.obterTecnico(), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
//            tabela.addCell(frase, cellConfiguration);
//
//            frase = new Phrase(registo.obterCap(), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
//            tabela.addCell(frase, cellConfiguration);


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

        tabela.removeBorder();
        return tabela;
    }
}
