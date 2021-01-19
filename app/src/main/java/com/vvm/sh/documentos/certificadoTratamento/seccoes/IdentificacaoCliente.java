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
import com.vvm.sh.documentos.modelos.DadosCliente;
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
        return new Table(new float[]{15f, 10f, 37f, 8f, 30f});
    }

    @Override
    protected void populateSection() {

        try {
            table.addEmptyCell(5);

            //titulo

            FontConfiguration fontConfiguration = new FontConfiguration();

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.horizontalAlign = Element.ALIGN_CENTER;
            cellConfiguration.verticalAlign = Element.ALIGN_CENTER;
            cellConfiguration.height = 20;
            cellConfiguration.colSpan = 5;

            Phrase titulo = new Phrase(contexto.getString(R.string.certificado_tratamento).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_CABECALHO, true));


            table.addCell(titulo, cellConfiguration);


            table.addEmptyCell(5);


            //nome + numero cliente

            CellConfiguration cellConfiguration_1 = new CellConfiguration();
            cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;

            CellConfiguration cellConfiguration_2 = new CellConfiguration();
            cellConfiguration_2.horizontalAlign = Element.ALIGN_LEFT;
            cellConfiguration_2.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.colSpan = 2;


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
            cellConfiguration_3.colSpan = 5;


            Phrase frase = new Phrase(contexto.getString(R.string.servico_integrado_seguranca_alimentar), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO));
            table.addLine(frase, cellConfiguration_3);


            table.addEmptyCell(5);

            //empresa

            CellConfiguration cellConfiguration_l2 [] = { cellConfiguration_2, cellConfiguration_1};

            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.morada_estabelecimento_), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true)),
                    new Phrase(dadosCliente.cliente.morada, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };


            table.addLine(linha, cellConfiguration_l2);



            table.removeBorder();

        } catch (PdfLineException e) {
            e.printStackTrace();
        }

    }
}
