package com.vvm.sh.documentos.informacaoSst.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.R;
import com.vvm.sh.util.constantes.Pdf;

public class Cliente extends Section {

    private Context contexto;

    public Cliente(Context contexto) {
        this.contexto = contexto;
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

        Phrase frase = new Phrase(contexto.getString(R.string.obrigatoriedades_legais).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA, true));
        table.addCell(frase, cellConfiguration);


        //descricao

        String texto = "Considera-se que, após a apresentação do presente documento, a empresa __________________________________________________ através do seu representante _____________________________________, se encontra informada da necessidade de dar cumprimento às obrigatoriedades legais abaixo descritas e que constam do Regime Jurídico da Promoção da Segurança e Saúde no Trabalho.";

        frase = new Phrase(texto, fontConfiguration.getFont(Pdf.Fontes.FONTE_ASSINATURA, true));
        table.addCell(frase, cellConfiguration);


    }
}
