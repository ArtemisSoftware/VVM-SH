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
import com.vvm.sh.documentos.modelos.DadosCliente;
import com.vvm.sh.util.constantes.Pdf;

public class Homologacao  extends Section {

    private Context contexto;
    private DadosCliente dadosCliente;

    public Homologacao(Context contexto, DadosCliente dadosCliente) {
        this.contexto = contexto;
        this.dadosCliente = dadosCliente;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{5f, 95f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        //imagem

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_TOP;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.alignLeft = 8;
        cellConfiguration.border = (Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
        cellConfiguration.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_HOMOLOGACAO;


        int homologado = Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA;

        if(dadosCliente.registo.homologado == false){
            homologado = Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA;
        }


        table.addCell(contexto.getResources(), homologado, cellConfiguration);

        //texto

        CellConfiguration cellConfiguration_2 = new CellConfiguration();
        cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_2.alignLeft = 8;
        cellConfiguration_2.border = (Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
        cellConfiguration_2.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_HOMOLOGACAO;


        Phrase frase = new Phrase(contexto.getString(R.string.homologacao_registo_visita), fontConfiguration.getFont(Pdf.Fontes.FONTE_HOMOLOGACAO));

        table.addCell(frase, cellConfiguration_2);
        table.setBorderColor(Pdf.Cores.TINTA_BORDA_CELULA);
        table.breakTable(false);
    }


}
