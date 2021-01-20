package com.vvm.sh.documentos.certificadoTratamento.capitulos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.documentos.certificadoTratamento.DadosCertificadoTratamento;
import com.vvm.sh.documentos.certificadoTratamento.seccoes.Certificado;
import com.vvm.sh.documentos.informacaoSst.seccoes.Assinatura;
import com.vvm.sh.documentos.informacaoSst.seccoes.IdentificacaoCliente;
import com.vvm.sh.documentos.informacaoSst.seccoes.ObrigacoesLegais;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_CertificadoTratamento extends Chapter {

    private Context contexto;
    private DadosCertificadoTratamento dados;


    public Capitulo_CertificadoTratamento(Context contexto, DadosCertificadoTratamento dados) {
        super(1);
        this.contexto = contexto;
        this.dados = dados;
    }

    @Override
    protected List<Index> getChapterIndexes() {
        List<Index> indexList = new ArrayList<>();

        indexList.add(Pdf.Seccoes.CLIENTE);
        indexList.add(Pdf.Seccoes.CERTIFICADO_TRATAMENTO);
        indexList.add(Pdf.Seccoes.RUBRICA);

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {

        Section section = null;

        switch (index.getId()) {



            case Pdf.Seccoes.ID_CLIENTE:

                section = new IdentificacaoCliente(contexto, dados.cliente);
                break;

            case Pdf.Seccoes.ID_CERTIFICADO_TRATAMENTO:

                section = new Certificado(contexto, dados.certificadoTratamento);
                break;


            case Pdf.Seccoes.ID_RUBRICA:

                section = new Assinatura(contexto, dados.rubrica);
                break;


            default:
                break;

        }

        return section;
    }
}
