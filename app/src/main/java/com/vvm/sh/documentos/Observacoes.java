package com.vvm.sh.documentos;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;

public class Observacoes extends Section {



    private final int tipo;
    private String observacao;

    public Observacoes(String observacao, int tipo) {
        this.tipo = tipo;
        this.observacao = observacao;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {
        table.addCell(obterTabelaObservacoes());
        //--table.quebrarTabela(false);
    }




    /**
     * Metodo que permite obter a tabela de observacoes
     * @return  a tabela de observacoes
     */
    private Table obterTabelaObservacoes(){

        Table tabela = null;

        try{

            switch (tipo) {

                case Pdf.TipoObservacao.TIPO_FRASE:

                    tabela = obterFrase(observacao);
                    break;

                case Pdf.TipoObservacao.TIPO_QUADRO:

                    tabela = obterQuadro(observacao);
                    break;


                default:

                    //--tabela = obterTabelaErro("Tipo de observacao inexistente");
                    break;
            }
        }
        catch(NullPointerException e){
            tabela = new Table();
            //--tabela.removerRebordo();
        }

        return tabela;
    }


    /**
     * Metodo que permite obter a observacao como um frase
     * @param observacao a observacao a adicionar
     * @return a tabela com a observacao
     */
    private Table obterFrase(String observacao){

        FontConfiguration fontConfiguration = new FontConfiguration();
        Table tabela = new Table();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_JUSTIFIED;
        cellConfiguration.verticalAlign = Element.ALIGN_CENTER;

        //titulo

        Phrase frase = new Phrase(Pdf.Texto.OBSERVACOES, fontConfiguration.getFont(Pdf.Fontes.FONTE_ONSERVACAO));
        tabela.addLine(frase, cellConfiguration);

        //observacao

        frase = new Phrase(observacao, fontConfiguration.getFont(Pdf.Fontes.FONTE_ONSERVACAO));
        tabela.addLine(frase, cellConfiguration);


        //--tabela.quebrarTabela(false);
        return tabela;
    }



    /**
     * Metodo que permite obter a observacao como um quadro
     * @param observacao a observacao a adicionar
     * @return a tabela com a observacao
     */
    private Table obterQuadro(String observacao) {

        FontConfiguration fontConfiguration = new FontConfiguration();
        Table tabela = new Table();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_JUSTIFIED;
        cellConfiguration.verticalAlign = Element.ALIGN_CENTER;
        cellConfiguration.border = (Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
        cellConfiguration.alignTop = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_OBSERVACAO;
        cellConfiguration.alignLeft = 8;
//        dimensao.adicionar_Evento(new TituloBorda(SintaxeIF.OBSERVACOES, fontes, 17f, 3f));

        Phrase frase = new Phrase(observacao, fontConfiguration.getFont(Pdf.Fontes.FONTE_ONSERVACAO));

        tabela.addCell(frase, cellConfiguration);
//        tabela.pintarRebordo(TINTA_BORDA_CELULA);
        return tabela;
    }


}
