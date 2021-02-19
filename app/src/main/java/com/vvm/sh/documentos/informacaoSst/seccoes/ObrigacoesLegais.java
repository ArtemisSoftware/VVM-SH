package com.vvm.sh.documentos.informacaoSst.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.util.PdfUtil;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.metodos.ImagemUtil;

import java.util.List;

public class ObrigacoesLegais extends Section {

    private List<ObrigacaoLegal> registos;
    private Context contexto;

    public ObrigacoesLegais(Context contexto, List<ObrigacaoLegal> registos) {
        this.registos = registos;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{94f, 6f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        CellConfiguration formato_Texto = new CellConfiguration();
        formato_Texto.verticalAlign  = Element.ALIGN_MIDDLE;

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.horizontalAlign = Element.ALIGN_CENTER;
        cellConfiguration_1.height = 23;

        CellConfiguration cellConfiguration_3 = new CellConfiguration();
        cellConfiguration_3.height = 12;

        Image checkbox = PdfUtil.createPdfImage(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA);
        checkbox.scaleToFit(14f, 14f);

        Image checkboxVazia = PdfUtil.createPdfImage(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA);
        checkboxVazia.scaleToFit(14f, 14f);

        for (ObrigacaoLegal registo : registos) {

            Phrase frase = new Phrase(registo.tipo.descricao, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO));
            table.addCell(frase, formato_Texto);

            if(registo.selecionado() == true){
                table.addCell(checkbox, cellConfiguration_1);
            }
            else{
                table.addCell(checkboxVazia, cellConfiguration_1);
            }

            table.addEmptyLine(cellConfiguration_3);
        }

        table.removeBorder();

        //-------------------


//        CellConfiguration formato_tabela = new CellConfiguration();
//        formato_tabela.border = 0;
//        table.formatCells(formato_tabela);

    }
}
