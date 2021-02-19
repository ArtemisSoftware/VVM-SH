package com.vvm.sh.documentos.certificadoTratamento.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
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
        return new Table(new float[]{8f, 14f, 27f, 8f, 30f});
    }

    @Override
    protected void populateSection() {

        try {

            //titulo

            FontConfiguration fontConfiguration = new FontConfiguration();

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.height = 38;
            cellConfiguration.colSpan = table.getNumberCells();

            Phrase titulo = new Phrase(contexto.getString(R.string.certificado_tratamento).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_CABECALHO, true));

            table.addCell(titulo, cellConfiguration);
            table.addEmptyLine();


            //nome + numero cliente

            CellConfiguration cellConfiguration_1 = new CellConfiguration();
            cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;

            CellConfiguration cellConfiguration_2 = new CellConfiguration();
            cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_2.colSpan = 2;


            CellConfiguration cellConfiguration_l1 [] = {cellConfiguration_1, cellConfiguration_2, cellConfiguration_1, cellConfiguration_1};

            Phrase [] linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.cliente_), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true)),
                    new Phrase(dadosCliente.cliente.nome, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),

                    new Phrase(contexto.getString(R.string.numero_ordinal), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true)),
                    new Phrase(dadosCliente.cliente.numeroCliente, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };

            table.addLine(linha, cellConfiguration_l1);


            //nota

            CellConfiguration cellConfiguration_3 = new CellConfiguration();
            cellConfiguration_3.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_3.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration_3.colSpan = table.getNumberCells();


            Phrase frase = new Phrase(contexto.getString(R.string.servico_integrado_seguranca_alimentar), fontConfiguration.getFont(Pdf.Fontes.FONTE_8));
            table.addLine(frase, cellConfiguration_3);


            CellConfiguration cellConfiguration_espaco = new CellConfiguration();
            cellConfiguration_espaco.height = 10;
            table.addEmptyLine(cellConfiguration_espaco);

            //empresa

            CellConfiguration cellConfiguration_l2 [] = { cellConfiguration_2, cellConfiguration_1};

            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.morada_estabelecimento_), fontConfiguration.getFont(Pdf.Fontes.FONTE_8, true)),
                    new Phrase(dadosCliente.cliente.morada, fontConfiguration.getFont(Pdf.Fontes.FONTE_8))
            };


            table.addLine(linha, cellConfiguration_l2);



            table.removeBorder();

        } catch (PdfLineException e) {
            e.printStackTrace();
        }

    }
}
