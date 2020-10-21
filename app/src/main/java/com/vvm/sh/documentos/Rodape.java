package com.vvm.sh.documentos;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.metodos.DatasUtil;

public class Rodape extends Section {


    private Table tabelaRodape;
    private String referencia;

    public Rodape(String referencia) {
        this.referencia = referencia;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{24, 24, 2}, 3* 175, false);
    }

    @Override
    protected void populateSection() {
        this.table = tabelaRodape;
    }


    /**
     * Metodo que cria a tabela com o numero da página
     * @param pageNumber o numero da página
     * @param pageTotal o total de páginas
     */
    public void adicionarNumeroPagina(int pageNumber, Image pageTotal){

        FontConfiguration font = new FontConfiguration();

        tabelaRodape = new Table(new float[]{24, 24, 2}, 3* 175, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.height = 13;


        tabelaRodape.addLine( new Phrase(Pdf.RegistoVisita.RODAPE_TITULO_SM_v2, font.getFont(8)), cellConfiguration);
        tabelaRodape.addLine( new Phrase(Pdf.RegistoVisita.RODAPE_TEXTO_1_SM, font.getFont(8)), cellConfiguration);
        tabelaRodape.addLine( new Phrase(Pdf.RegistoVisita.RODAPE_TEXTO_2_SM, font.getFont(8)), cellConfiguration);
        tabelaRodape.addLine( new Phrase(Pdf.RegistoVisita.RODAPE_TEXTO_3_SM, font.getFont(8)), cellConfiguration);

        CellConfiguration cellConfiguration1 = new CellConfiguration();
        cellConfiguration1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration1.height = 12;

        tabelaRodape.addCell(new Phrase(DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM), font.getFont(7)), cellConfiguration1);

        CellConfiguration cellConfiguration2 = null;
        try {
            cellConfiguration2 = (CellConfiguration) cellConfiguration1.clone();
            cellConfiguration2.horizontalAlign = Element.ALIGN_RIGHT;

            tabelaRodape.addCell(new Phrase(String.format("Página | %d de", pageNumber), font.getFont(7, true, BaseColor.GRAY)), cellConfiguration2);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        tabelaRodape.addCell(pageTotal);
        tabelaRodape.removeBorder(0,1,2,3);
        tabelaRodape.setBorder(Rectangle.TOP, 4);

    }


    /**
     * Metodo que permite obter a tabela de referencia
     * @return uma tabela
     */
    public Table obterTabelaReferencia(){

        FontConfiguration fontConfiguration = new FontConfiguration();

        Table tabela = new Table(new float[]{/*48/4,*/ 1}, /*2*/20 /*0.2f* 175*/, false);

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.rotation = 90;
        tabela.addCell(new Phrase(referencia, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO, false, BaseColor.GRAY)), cellConfiguration);
        tabela.removeBorder();
        return tabela;
    }

}