package com.vvm.sh.documentos;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.documentos.eventos.EspacoPreenchimento;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.metodos.DatasUtil;

public class Assinatura extends Section {

    private Rubrica registo;

    public Assinatura(Rubrica registo) {
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
            cellConfiguration.event = new EspacoPreenchimento(0f, 10f);


            Phrase frase = new Phrase(DatasUtil.obterDataAtual(DatasUtil.FORMATO_DD_MM_YYYY), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
            tabela.addCell(frase, cellConfiguration);

//            Image imagem = MetodosPdf.imagemPdf(contexto, registo.obterImagem());
//            imagem.scaleToFit(120f, 180f);
//            tabela.addCell(imagem, cellConfiguration);
            tabela.addEmptyCell();

            frase = new Phrase(registo.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
            tabela.addCell(frase, cellConfiguration);

            frase = new Phrase(registo.cap, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
            tabela.addCell(frase, cellConfiguration);


            Phrase frases [] = {
                    new Phrase(Pdf.Texto.DATA, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                    new Phrase(Pdf.Texto.CLIENTE, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                    new Phrase(Pdf.Texto.TECNICO, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA)),
                    new Phrase(Pdf.Texto.CAP_N, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA))
            };

            CellConfiguration cellConfiguration2 = new CellConfiguration();
            cellConfiguration2.horizontalAlign = Element.ALIGN_CENTER;
            tabela.addLine(frases, cellConfiguration2);

        }

        tabela.removeBorder();
        return tabela;
    }
}
