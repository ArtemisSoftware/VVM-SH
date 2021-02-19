package com.vvm.sh.documentos.certificadoTratamento.seccoes;

import android.content.Context;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.elements.CellConfiguration;
import com.titan.pdfdocumentlibrary.elements.FontConfiguration;
import com.titan.pdfdocumentlibrary.elements.Table;
import com.titan.pdfdocumentlibrary.exception.PdfLineException;
import com.titan.pdfdocumentlibrary.util.PdfUtil;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.util.constantes.Pdf;
import com.vvm.sh.util.constantes.Sintaxe;

import static com.vvm.sh.util.constantes.Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA;
import static com.vvm.sh.util.constantes.Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA;
import static com.vvm.sh.util.constantes.TiposConstantes.*;

public class Certificado extends Section {

    private Context contexto;
    private CertificadoTratamentoResultado certificadoTratamento;
    private FontConfiguration fontConfiguration = new FontConfiguration();

    public Certificado(Context contexto, CertificadoTratamentoResultado certificadoTratamento) {
        this.certificadoTratamento = certificadoTratamento;
        this.contexto = contexto;
    }

    @Override
    protected Table getMainTable() {
        return new Table();
    }

    @Override
    protected void populateSection() {

        table.addEmptyLine();
        table.addCell(obterPraga());

        table.addEmptyLine();
        table.addCell(obterProdutoAplicado());

        table.addEmptyLine();
        table.addCell(obterAvaliacaoInfraestruturas());

        table.addEmptyLine();
        table.addCell(obterObservacoes());

        table.addEmptyLine();
        table.addCell(obterRecomendacoes());

        table.removeBorder();
    }




    private Table obterPraga() {

        Table tabela = new Table(new float[]{40f, 60f});

        try {

            //titulo

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.colSpan = tabela.getNumberCells();

            Phrase titulo = new Phrase(contexto.getString(R.string.tipo_praga), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));
            tabela.addCell(titulo, cellConfiguration);


            //dados

            CellConfiguration cellConfiguration_1 = new CellConfiguration();
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;

            CellConfiguration cellConfiguration_l1 [] = {cellConfiguration_1, cellConfiguration_1};

            Phrase [] linha = new Phrase []{
                    new Phrase(formatarTipo(certificadoTratamento.idPraga, CertificadoTratamento.Pragas.PRAGAS).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
                    new Phrase(formatarTipo(certificadoTratamento.idVisita, CertificadoTratamento.Visitas.VISITAS), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
            };

            tabela.addLine(linha, cellConfiguration_l1);

        } catch (PdfLineException e) {
            e.printStackTrace();
        }


        tabela.removeBorder();
        return tabela;

    }


    private Table obterProdutoAplicado() {

        Table tabela = new Table(new float[]{40f, 60f});

        try {

            //titulo

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.colSpan = tabela.getNumberCells();

            Phrase titulo = new Phrase(contexto.getString(R.string.produto_aplicado), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));
            tabela.addCell(titulo, cellConfiguration);


            //dados

            CellConfiguration cellConfiguration_1 = new CellConfiguration();
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
            cellConfiguration.horizontalAlign = Element.ALIGN_LEFT;

            CellConfiguration cellConfiguration_l1 [] = {cellConfiguration_1, cellConfiguration_1};

            Phrase [] linha = new Phrase []{
                    new Phrase(Sintaxe.SEM_TEXTO, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
                    new Phrase(formatarTipo(certificadoTratamento.idProdutoAplicado, CertificadoTratamento.Produtos.PRODUTOS), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };

            tabela.addLine(linha, cellConfiguration_l1, 0);

        } catch (PdfLineException e) {
            e.printStackTrace();
        }


        tabela.removeBorder();
        return tabela;

    }


    private Table obterAvaliacaoInfraestruturas() {


        Table tabela = new Table(new float[]{45f, 55f});

        //titulo

        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.colSpan = tabela.getNumberCells();

        Phrase titulo = new Phrase(contexto.getString(R.string.avaliacao_infraestruturas), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));
        tabela.addCell(titulo, cellConfiguration);


        //dados

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_1.height = 17;


        CellConfiguration dimensoes_l1 [] = {cellConfiguration_1, cellConfiguration_1};

        try {

            Phrase [] linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.condicoes_higiene), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
                    new Phrase(formatarTipo(certificadoTratamento.avaliacaoCondicoesHigiene, CertificadoTratamento.Avaliacoes.AVALIACOES), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };

            tabela.addLine(linha, dimensoes_l1, 0);


            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.manutencao_instalacoes), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
                    new Phrase(formatarTipo(certificadoTratamento.avaliacaoManutencaoInstalacoes, CertificadoTratamento.Avaliacoes.AVALIACOES), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))
            };

            tabela.addLine(linha, dimensoes_l1, 0);


            linha = new Phrase []{
                    new Phrase(contexto.getString(R.string.condicoes_armazenamento), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE)),
                    new Phrase(formatarTipo(certificadoTratamento.avaliacaoCondicoesArmazenamento, CertificadoTratamento.Avaliacoes.AVALIACOES), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE))

            };

            tabela.addLine(linha, dimensoes_l1, 0);

        } catch (PdfLineException e) {
            e.printStackTrace();
        }

        tabela.removeBorder();
        return tabela;

    }


    private Table obterObservacoes() {


        Table tabela = new Table(new float[]{80f, 20f});

        //titulo


        CellConfiguration cellConfiguration = new CellConfiguration();
        cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration.colSpan = tabela.getNumberCells();

        Phrase titulo = new Phrase(contexto.getString(R.string.observacoes).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));
        tabela.addCell(titulo, cellConfiguration);


        //dados

        CellConfiguration cellConfiguration_1 = new CellConfiguration();
        cellConfiguration_1.verticalAlign = Element.ALIGN_MIDDLE;
        cellConfiguration_1.horizontalAlign = Element.ALIGN_LEFT;
        cellConfiguration_1.height = 23;


        Phrase obs1 = new Phrase(contexto.getString(R.string.obs_certificado_tratamento_1), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE));
        Phrase obs2 = new Phrase(contexto.getString(R.string.obs_certificado_tratamento_2), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE));


        Image checkbox = PdfUtil.createPdfImage(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_CINZENTA);
        checkbox.scaleToFit(14f, 14f);

        Image checkboxVazia = PdfUtil.createPdfImage(contexto.getResources(), Pdf.Imagens.IMAGEM_CHECKBOX_VAZIA_CINZENTA);
        checkboxVazia.scaleToFit(14f, 14f);



        Image imagemObs1 = checkbox;

        if(certificadoTratamento.observacaoVestigiosPragas == false){
            imagemObs1 = checkboxVazia;
        }

        Image imagemObs2 = checkbox;

        if(certificadoTratamento.observacaoProdutosEmGel == false){
            imagemObs2 = checkboxVazia;
        }


        tabela.addCell(obs1, cellConfiguration_1);
        tabela.addCell(imagemObs1, cellConfiguration_1);

        tabela.addCell(obs2, cellConfiguration_1);
        tabela.addCell(imagemObs2, cellConfiguration_1);


        tabela.removeBorder();
        return tabela;
    }


    private Table obterRecomendacoes() {

        Table tabela = new Table();

        if(certificadoTratamento.observacao.equals("") == false) {

            //titulo

            CellConfiguration cellConfiguration = new CellConfiguration();
            cellConfiguration.verticalAlign = Element.ALIGN_MIDDLE;

            Phrase titulo = new Phrase(contexto.getString(R.string.recomendacoes_observacoes).toUpperCase(), fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE, true));

            tabela.addCell(titulo, cellConfiguration);

            //dados
            Phrase frase = new Phrase(certificadoTratamento.observacao, fontConfiguration.getFont(Pdf.Fontes.FONTE_TEXTO_GRANDE));
            tabela.addCell(frase, cellConfiguration);
        }

        tabela.removeBorder();
        return tabela;

    }

}
