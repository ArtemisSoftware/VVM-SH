package com.vvm.sh.documentos.registoVisita.seccoes;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.R;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.constantes.Pdf;


import java.util.List;

public class TrabalhosRealizados extends Section {

    private List<TrabalhoRealizado> registos;
    private Context contexto;

    public TrabalhosRealizados(Context contexto, List<TrabalhoRealizado> registos) {
        this.registos = registos;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{0.4f, 0.5f, 5.6f, 0.5f, 5.6f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        Phrase titulo = new Phrase(contexto.getString(R.string.trabalhos_realizados), fontConfiguration.getFont(Pdf.Fontes.FONTE_7, true, BaseColor.WHITE));
        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element. ALIGN_TOP;
        cellConfiguration.verticalAlign = Element.ALIGN_TOP;
        cellConfiguration.rowspan = (int) Math.ceil(registos.size() / 2.0);
        cellConfiguration.backgroundColor = BaseColor.BLACK;
        cellConfiguration.rotation = 90;

        table.addCell(titulo, cellConfiguration);


        //imagem

        CellConfiguration cellConfiguration_imagem = new CellConfiguration();
        cellConfiguration_imagem.horizontalAlign = Element. ALIGN_MIDDLE;
        cellConfiguration_imagem.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_imagem.alignLeft = 2;
        cellConfiguration_imagem.height = 18;


        //texto

        CellConfiguration formato_Texto = new CellConfiguration();
        formato_Texto.verticalAlign  = Element.ALIGN_MIDDLE;
        formato_Texto.height = 18;


        for (TrabalhoRealizado registo : registos) {

            if(registo.selecionado() == true){
                table.addCell(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA, cellConfiguration_imagem);
            }
            else{
                table.addCell(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA, cellConfiguration_imagem);
            }

            Phrase frase = new Phrase(registo.tipo.descricao, fontConfiguration.getFont(Pdf.Fontes.FONTE_7));
            table.addCell(frase, formato_Texto);
        }

        if ((registos.size() % 2) == 1) {

            table.addEmptyCell(formato_Texto);
            table.addEmptyCell(formato_Texto);
        }

        CellConfiguration formato_tabela = new CellConfiguration();
        formato_tabela.border = 0;
        formato_tabela.setOverLapColor(BaseColor.WHITE, false);
        table.formatCells(formato_tabela);

    }
}
