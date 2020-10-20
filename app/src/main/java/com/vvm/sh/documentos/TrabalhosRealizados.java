package com.vvm.sh.documentos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;

import java.util.List;

public class TrabalhosRealizados extends Section {

    private List<TrabalhoRealizado> registos;

    public TrabalhosRealizados(List<TrabalhoRealizado> registos) {
        this.registos = registos;
    }

    @Override
    protected Table getMainTable() {
        return new Table(new float[]{0.4f, 0.5f, 5.6f, 0.5f, 5.6f});
    }

    @Override
    protected void populateSection() {

        FontConfiguration fontConfiguration = new FontConfiguration();


        Phrase titulo = new Phrase("SintaxeIF.TITULO_TRABALHO_REALIZADOS", fontConfiguration.getFont(7f, true, BaseColor.WHITE));
        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.horizontalAlign = Element. ALIGN_TOP;
        cellConfiguration.verticalAlign = Element.ALIGN_TOP;
        cellConfiguration.rowspan = (int) Math.ceil(registos.size() / 2.0);
        cellConfiguration.backgroundColor = BaseColor.BLACK;
        //--dimensao.adicionar_Rotacao(90);

        table.addCell(titulo, cellConfiguration);
        table.addCell("checkbox");
        table.addCell("texto");
        table.addCell("checkbox");
        table.addCell("texto");

//        Phrase titulo = new Phrase(SintaxeIF.TITULO_TRABALHO_REALIZADOS, fontes.obterFonte_bold(FONTE_7, BaseColor.WHITE));
//        DimensoesCelula dimensao = new DimensoesCelula();
//        dimensao.adicionar_Alinhamento(Element.ALIGN_TOP, Element.ALIGN_TOP);
//        dimensao.adicionar_Rotacao(90);
//        dimensao.adicionar_RowSpan((int) Math.ceil(registos.size() / 2.0));
//        dimensao.adicionar_CorFundo(BaseColor.BLACK);
//
//        tabela.adicionarCelula(titulo, dimensao);
//
//
//        //imagem
//
//        DimensoesCelula formato_imagem = new DimensoesCelula();
//        formato_imagem.adicionar_Alinhamento(Element.ALIGN_MIDDLE, Element.ALIGN_MIDDLE);
//        formato_imagem.adicionar_AlinhamentoEsquerda(2);
//        formato_imagem.adicionar_Altura(ALTURA_LINHA___TABELA_TRABALHOS_REALIZADOS);

//
        //texto

        CellConfiguration formato_Texto = new CellConfiguration();
        formato_Texto.verticalAlign  = Element.ALIGN_MIDDLE;
        formato_Texto.height  = 15; //--ALTURA_LINHA___TABELA_TRABALHOS_REALIZADOS);
//
//        Image preenchido = MetodosPdf.imagemPdf(contexto.getResources(), IMAGEM_CHECKBOX_CINZENTA);
//        preenchido.scaleToFit(15, 15);
//
//        Image naoPreenchido = MetodosPdf.imagemPdf(contexto.getResources(), IMAGEM_CHECKBOX_VAZIA_CINZENTA);
//        naoPreenchido.scaleToFit(15, 15);
//
        for (TrabalhoRealizado registo : registos) {

            if(registo.selecionado() == true){
                table.addCell(/*preenchido, formato_imagem*/"selecionado");
            }
            else{
                table.addCell(/*naoPreenchido, formato_imagem*/"Não selecionado");
            }

            Phrase frase = new Phrase(registo.tipo.descricao, fontConfiguration.getFont(7f));
            table.addCell(frase, formato_Texto);
        }

        if ((registos.size() % 2) == 1) {

            table.addCell("SEM_TEXTO", formato_Texto);
            table.addCell("SEM_TEXTO", formato_Texto);
        }
//
//        DimensoesCelula formato = new DimensoesCelula();
//        formato.adicionar_CorFundo(BaseColor.WHITE, false);
//        formato.adicionar_Borda(0);
//        tabela.formatarTabela(formato);
//        return tabela;

    }
}
