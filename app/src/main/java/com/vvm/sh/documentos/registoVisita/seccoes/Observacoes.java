package com.vvm.sh.documentos.registoVisita.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.R;
import com.vvm.sh.documentos.eventos.TituloBorda;
import com.vvm.sh.util.constantes.Pdf;

public class Observacoes extends Section {



    private final int tipo;
    private String observacao;
    private Context contexto;

    public Observacoes(Context contexto, String observacao, int tipo) {
        this.tipo = tipo;
        this.observacao = observacao;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        if(observacao == null)
            return;

        if(observacao.equals("") == true)
            return;

        switch (tipo) {

            case Pdf.TipoObservacao.TIPO_FRASE:

                table = obterFrase(observacao);
                break;

            case Pdf.TipoObservacao.TIPO_QUADRO:

                table = obterQuadro(observacao);
                break;


            default:

                //--tabela = obterTabelaErro("Tipo de observacao inexistente");
                break;
        }

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

        Phrase frase = new Phrase(contexto.getString(R.string.observacoes), fontConfiguration.getFont(Pdf.Fontes.FONTE_8));
        tabela.addLine(frase, cellConfiguration);

        //observacao

        frase = new Phrase(observacao, fontConfiguration.getFont(Pdf.Fontes.FONTE_8));
        tabela.addLine(frase, cellConfiguration);


        tabela.breakTable(false);
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
        cellConfiguration.alignTop = 10;
        cellConfiguration.alignLeft = 8;
        cellConfiguration.event = new TituloBorda(contexto.getString(R.string.observacoes), 17f, 3f);

        Phrase frase = new Phrase(observacao, fontConfiguration.getFont(Pdf.Fontes.FONTE_8));

        tabela.addCell(frase, cellConfiguration);
        tabela.setBorderColor(Pdf.Cores.TINTA_BORDA_CELULA);
        tabela.breakTable(false);
        return tabela;
    }


}
