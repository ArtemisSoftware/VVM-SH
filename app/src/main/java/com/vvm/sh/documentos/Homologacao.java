package com.vvm.sh.documentos;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;

public class Homologacao  extends Section {

    private Context contexto;

    public Homologacao(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {
        table.addCell(obterTabelaAutorizacao());
        //table.pintarRebordo(TINTA_BORDA_CELULA, 1.8f);
        //table.quebrarTabela(false);
    }


    /**
     * Metodo que permite obter a tabela de autorizacao
     */
    private Table obterTabelaAutorizacao() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        Table tabela = new Table(new float[]{5f, 95f});

        //imagem

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_TOP;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.alignLeft = 8;
        cellConfiguration.border = (Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
        cellConfiguration.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_HOMOLOGACAO;

//        Image imagem = MetodosPdf.imagemPdf(contexto.getResources(), IMAGEM_CHECKBOX_CINZENTA);
//        imagem.scaleToFit(15, 15);

        table.addCell(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA, cellConfiguration);

        //texto
        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.alignLeft = 8;
        cellConfiguration_2.border = (Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
        cellConfiguration_2.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_HOMOLOGACAO;


        Phrase frase = new Phrase(Pdf.Texto.HOMOLOGACAO_REGISTO_VISITA, fontConfiguration.getFont(Pdf.Fontes.FONTE_HOMOLOGACAO));

        tabela.addCell(frase, cellConfiguration_2);

        //--tabela.pintarRebordo(TINTA_BORDA_CELULA);
        return tabela;
    }

}
