package com.vvm.sh.documentos.templates.informacaoSst.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.util.constantes.Pdf;

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
        return new Table(new float[]{0.4f, 0.5f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        CellConfiguration formato_Texto = new CellConfiguration();
        formato_Texto.verticalAlign  = Element.ALIGN_MIDDLE;


        CellConfiguration cellConfiguration_imagem = new CellConfiguration();
        cellConfiguration_imagem.horizontalAlign = Element. ALIGN_MIDDLE;
        cellConfiguration_imagem.verticalAlign = Element.ALIGN_MIDDLE;


        for (ObrigacaoLegal registo : registos) {

            Phrase frase = new Phrase(registo.tipo.descricao, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO));
            table.addCell(frase, formato_Texto);

            if(registo.selecionado() == true){
                table.addCell(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA, cellConfiguration_imagem);
            }
            else{
                table.addCell(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA, cellConfiguration_imagem);
            }
        }


        //-------------------


//        CellConfiguration formato_tabela = new CellConfiguration();
//        formato_tabela.border = 0;
//        table.formatCells(formato_tabela);

    }
}
