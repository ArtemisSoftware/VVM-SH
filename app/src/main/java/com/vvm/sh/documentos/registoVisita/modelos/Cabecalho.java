package com.vvm.sh.documentos.registoVisita.modelos;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.util.PdfUtil;
import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Pdf;

public class Cabecalho extends Section {

    private Context contexto;

    public Cabecalho(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{30f, 40f, 30f}, 3* 175, false);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;

        Image logo = PdfUtil.createPdfImage(contexto.getResources(), Pdf.Imagens.LOGOTIPO_VIVAMAIS);
        logo.scaleToFit(160f, 80f);


        table.addCell(logo, cellConfiguration_1);
        table.addEmptyCell();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.addFrame(7, 7, /*21f*/7, 21f);
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

        table.addCell(obterTabelaReferencia(), cellConfiguration);
        table.removeBorder();
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

        Phrase frase = new Phrase(contexto.getString(R.string.relatorio_visita_e), fontConfiguration.getFont(Pdf.Fontes.FONTE_11, true, BaseColor.WHITE));
        tabela.addCell(frase, cellConfiguration);

        frase = new Phrase(contexto.getString(R.string.pedido_informacao), fontConfiguration.getFont(Pdf.Fontes.FONTE_11, true, BaseColor.WHITE));
        tabela.addCell(frase, cellConfiguration);

        return tabela;
    }
}
