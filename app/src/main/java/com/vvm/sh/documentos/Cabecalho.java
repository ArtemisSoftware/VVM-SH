package com.vvm.sh.documentos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;

public class Cabecalho extends Section {
    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        //--table.addCell(MetodosPdf.imagemPdf(contexto.getResources(), LOGOTIPO_VIVAMAIS));
        table.addEmptyCell();

        CellConfiguration cellConfiguration = new CellConfiguration();


        //--cellConfiguration.addFrame(7, 7, 21f, 21f);
        table.addCell(obterTabelaReferencia(), cellConfiguration);
    }

    /**
     * Metodo que permite obter a tabela de referencia
     * @return uma tabela
     */
    private Table obterTabelaReferencia() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        Table tabela = new Table();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.backgroundColor = BaseColor.BLACK;

        Phrase frase = new Phrase(Pdf.Texto.RELATORIO_VISITA, fontConfiguration.getFont(Pdf.Fontes.FONTE_CABECALHO, true, BaseColor.WHITE));
        tabela.addCell(frase, cellConfiguration);

        frase = new Phrase(Pdf.Texto.PEDIDO_INFORMACAO, fontConfiguration.getFont(Pdf.Fontes.FONTE_CABECALHO, true, BaseColor.WHITE));
        tabela.addCell(frase, cellConfiguration);

        return tabela;
    }
}
