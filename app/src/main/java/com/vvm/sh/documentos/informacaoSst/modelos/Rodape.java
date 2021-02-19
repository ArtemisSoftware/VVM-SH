package com.vvm.sh.documentos.informacaoSst.modelos;

import com.itextpdf.text.BaseColor;
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
        return new Table(new float[]{100f}, 509, false);
    }

    @Override
    protected void populateSection() {

    }

    public Table gerarPrimeiroRodape(){

        Table tabela = new Table(new float[]{100f}, 509, false);

        FontConfiguration fontConfiguration = new FontConfiguration();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

        for (String texto : rodapes) {

            Phrase frase = new Phrase(texto, fontConfiguration.getFont(Pdf.Fontes.FONTE_8, false, BaseColor.GRAY));
            tabela.addCell(frase, cellConfiguration);
        }

        tabela.removeBorder();
        return tabela;
    }



    public Table gerarSegundoRodape(){

        Table tabela = new Table(new float[]{100f}, 609, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.height = 33;
        cellConfiguration.backgroundColor = Pdf.Cores.VERMELHO_VIVAMAIS;

        tabela.addEmptyLine(cellConfiguration);

        tabela.removeBorder();
        return tabela;
    }
}
