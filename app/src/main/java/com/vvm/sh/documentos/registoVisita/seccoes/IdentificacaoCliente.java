package com.vvm.sh.documentos.registoVisita.seccoes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.vvm.sh.documentos.eventos.EspacoPreenchimento;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.util.constantes.Pdf;

public class IdentificacaoCliente extends Section {

    private DadosCliente registo;

    public IdentificacaoCliente(DadosCliente dadosCliente) {
        this.registo = dadosCliente;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{0.4f, 1.3f, 4.8f, 1.3f, 4.8f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();

        //titulo

        Phrase titulo = new Phrase(Pdf.Texto.TITULO_IDENTIFICACAO_CLIENTE, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO, true, BaseColor.WHITE));

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
        cellConfiguration_1.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_IDENTIFICACAO_CLIENTE;

        try {
            CellConfiguration cellConfiguration_11 = new CellConfiguration();
            cellConfiguration_11.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_11.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_11.height = Pdf.RegistoVisita.ALTURA_LINHA___TABELA_IDENTIFICACAO_CLIENTE;
            cellConfiguration_11.event = new EspacoPreenchimento();

            CellConfiguration cellConfiguration_l1 [] = {cellConfiguration_1, cellConfiguration_11, cellConfiguration_1, cellConfiguration_11};

            Phrase [] linha = new Phrase []{
                    new Phrase(Pdf.Texto.N_CLIENTE, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO, false)),
                    new Phrase(registo.cliente.numeroCliente, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE) ),

                    new Phrase(Pdf.Texto.N_ORDEM, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO, false)),
                    new Phrase(registo.tarefa.ordem, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };



            table.addLine(linha, cellConfiguration_11, 1);


            //empresa

            CellConfiguration cellConfiguration_2 = new CellConfiguration();
            cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_2.colSpan = 3;
            cellConfiguration_2.event = new EspacoPreenchimento();

            CellConfiguration dimensoes [] = {cellConfiguration_1, cellConfiguration_2};


            linha = new Phrase []{
                    new Phrase(Pdf.Texto.EMPRESA, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO)),
                    new Phrase(registo.cliente.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };

            table.addLine(linha, dimensoes, 1);



            //recebido por + funcao

            linha = new Phrase []{
                    new Phrase(Pdf.Texto.RECEBIDO_POR, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO)),
                    new Phrase(registo.registo.recebidoPor, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),

                    new Phrase(Pdf.Texto.FUNCAO, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO)),
                    new Phrase(registo.registo.funcao, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
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
