package com.vvm.sh.documentos.informacaoSst.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.R;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.util.constantes.Pdf;

public class IdentificacaoCliente extends Section {

    private Context contexto;
    private DadosCliente dadosCliente;

    public IdentificacaoCliente(Context contexto, DadosCliente dadosCliente) {
        this.contexto = contexto;
        this.dadosCliente = dadosCliente;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;


        //titulo

        Phrase frase = new Phrase(contexto.getString(R.string.obrigatoriedades_legais).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_CABECALHO, true));
        table.addCell(frase, cellConfiguration);


        CellConfiguration cellConfiguration_3 = new CellConfiguration();
        cellConfiguration_3.height = 10;
        table.addEmptyLine(cellConfiguration_3);

        //descricao

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;

        String texto = "Considera-se que, após a apresentação do presente documento, a empresa " + dadosCliente.cliente.nome + " através do seu representante " + dadosCliente.informacaoSst.responsavel + ", se encontra informada da necessidade de dar cumprimento às obrigatoriedades legais abaixo descritas e que constam do Regime Jurídico da Promoção da Segurança e Saúde no Trabalho.";

        frase = new Phrase(texto, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA));
        table.addCell(frase, cellConfiguration_1);

        table.removeBorder();
    }
}
