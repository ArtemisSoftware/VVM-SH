package com.vvm.sh.documentos.registoVisita.modelos;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Pdf;

public class Cabecalho extends Section {

    private Context contexto;

    public Cabecalho(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(3);
    }

    @Override
    protected void populateSection() {

        table.addCell(contexto.getResources(), Pdf.Imagens.LOGOTIPO_VIVAMAIS);
        table.addEmptyCell();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.addFrame(7, 7, 21f, 21f);
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
