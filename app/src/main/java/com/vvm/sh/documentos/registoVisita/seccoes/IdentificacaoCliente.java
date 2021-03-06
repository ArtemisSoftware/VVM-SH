package com.vvm.sh.documentos.registoVisita.seccoes;

import android.content.Context;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.vvm.sh.R;
import com.vvm.sh.documentos.eventos.EspacoPreenchimento;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.util.constantes.Pdf;

public class IdentificacaoCliente extends Section {

    private Context contexto;
    private DadosCliente dadosCliente;

    public IdentificacaoCliente(Context contexto, DadosCliente dadosCliente) {
        this.dadosCliente = dadosCliente;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{0.4f, 1.3f, 4.8f, 1.3f, 4.8f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        //titulo

        Phrase titulo = new Phrase(contexto.getString(R.string.identificacao_cliente), fontConfiguration.getFont(Pdf.Fontes.FONTE_7, true, BaseColor.WHITE));

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.rowspan = 3;
        cellConfiguration.backgroundColor = BaseColor.BLACK;
        cellConfiguration.rotation = 90;

        table.addCell(titulo, cellConfiguration);

        //numero cliente + numero ordem

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_1.height = 25;

        try {
            CellConfiguration cellConfiguration_11 = new CellConfiguration();
            cellConfiguration_11.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_11.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_11.height = 25;
            cellConfiguration_11.event = new EspacoPreenchimento();

            CellConfiguration cellConfiguration_l1 [] = {cellConfiguration_1, cellConfiguration_11, cellConfiguration_1, cellConfiguration_11};

            Phrase [] linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.n_cliente), fontConfiguration.getFont(Pdf.Fontes.FONTE_7, false)),
                    new Phrase(dadosCliente.cliente.numeroCliente, fontConfiguration.getFont(Pdf.Fontes.FONTE_9) ),

                    new Phrase(contexto.getString(R.string.n_ordem), fontConfiguration.getFont(Pdf.Fontes.FONTE_7, false)),
                    new Phrase(dadosCliente.tarefa.ordem, fontConfiguration.getFont(Pdf.Fontes.FONTE_9))
            };



            table.addLine(linha, cellConfiguration_l1, 1);


            //empresa

            CellConfiguration cellConfiguration_2 = new CellConfiguration();
            cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_2.colSpan = 3;
            cellConfiguration_2.event = new EspacoPreenchimento();

            CellConfiguration dimensoes [] = {cellConfiguration_1, cellConfiguration_2};


            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.empresa), fontConfiguration.getFont(Pdf.Fontes.FONTE_7)),
                    new Phrase(dadosCliente.cliente.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_9))
            };

            table.addLine(linha, dimensoes, 1);



            //recebido por + funcao

            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.recebidoPor), fontConfiguration.getFont(Pdf.Fontes.FONTE_7)),
                    new Phrase(dadosCliente.registo.recebidoPor, fontConfiguration.getFont(Pdf.Fontes.FONTE_9)),

                    new Phrase(contexto.getString(R.string.funcao), fontConfiguration.getFont(Pdf.Fontes.FONTE_7)),
                    new Phrase(dadosCliente.registo.funcao, fontConfiguration.getFont(Pdf.Fontes.FONTE_9))
            };

            table.addLine(linha, cellConfiguration_l1, 1);


            CellConfiguration cellConfiguration_3 = new CellConfiguration();
            cellConfiguration_3.border = 0;
            cellConfiguration_3.setOverLapColor(Pdf.Cores.TINTA_CINZENTA, false);
            table.formatCells(cellConfiguration_3);

        }
        catch (PdfLineException  e) {
            e.printStackTrace();
        }
    }


}
