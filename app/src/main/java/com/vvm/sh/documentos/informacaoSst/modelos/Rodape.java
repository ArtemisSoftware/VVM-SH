package com.vvm.sh.documentos.informacaoSst.modelos;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.List;

import static com.vvm.sh.util.constantes.Pdf.Cores.TINTA_CINZENTA;

public class Rodape extends Section {

    private List<String> rodapes;

    public Rodape(List<String> rodapes) {
        this.rodapes = rodapes;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

        for (String texto : rodapes) {

            Phrase frase = new Phrase(texto, fontConfiguration.getFont(Pdf.Fontes.FONTE_HOMOLOGACAO, false, TINTA_CINZENTA));
            table.addCell(frase, cellConfiguration);
        }

    }
}
