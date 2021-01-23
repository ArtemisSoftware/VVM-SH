package com.vvm.sh.documentos.templates.certificadoTratamento.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;

import static com.vvm.sh.util.constantes.Pdf.Imagens.LOGOTIPO_VIVAMAIS;

public class Cabecalho extends Section {

    private Context contexto;

    public Cabecalho(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration.verticalAlign = Element.ALIGN_CENTER;
        cellConfiguration.height = 40;

        table.addCell(contexto.getResources(), LOGOTIPO_VIVAMAIS, cellConfiguration);

    }
}
