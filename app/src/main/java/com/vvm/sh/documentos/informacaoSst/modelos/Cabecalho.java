package com.vvm.sh.documentos.informacaoSst.modelos;

import android.content.Context;

import com.itextpdf.text.Element;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.util.constantes.Pdf;

import static com.vvm.sh.util.constantes.Pdf.Imagens.LOGOTIPO_VIVAMAIS;
import static com.vvm.sh.util.constantes.Pdf.Imagens.LOGOTIPO_VIVAMAIS_NEGATIVO;

public class Cabecalho extends Section {

    private Context contexto;

    public Cabecalho(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{100f}, 595, false);
    }

    @Override
    protected void populateSection() {

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.height = 40;
        cellConfiguration.backgroundColor = Pdf.Cores.VERMELHO_VIVAMAIS;

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.height = 5;
        cellConfiguration_1.backgroundColor = Pdf.Cores.VERMELHO_VIVAMAIS;


        table.addEmptyLine(cellConfiguration_1);
        table.addCell(contexto.getResources(), LOGOTIPO_VIVAMAIS_NEGATIVO, cellConfiguration);
        table.addEmptyLine(cellConfiguration_1);
        table.removeBorder();
    }
}
