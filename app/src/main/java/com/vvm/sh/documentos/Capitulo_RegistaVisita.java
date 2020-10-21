package com.vvm.sh.documentos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_RegistaVisita extends Chapter {

    private Context contexto;
    private RegistoVisita registoVisita;


    public Capitulo_RegistaVisita(Context contexto, RegistoVisita registoVisita) {
        super(1);
        this.contexto = contexto;
        this.registoVisita = registoVisita;
    }


    @Override
    protected List<Index> getChapterIndexes() {

        List<Index> indexList = new ArrayList<>();

        indexList.add(Pdf.Seccoes.CABECALHO);

        indexList.add(Pdf.Seccoes.CLIENTE);
        indexList.add(Pdf.Seccoes.TRABALHOS_REALIZADOS);
        indexList.add(Pdf.Seccoes.HOMOLOGACAO);
//
//        seccoes.add(SECCAO_OBSERVACAO);
        indexList.add(Pdf.Seccoes.RUBRICA);

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {

            case Pdf.Seccoes.ID_CABECALHO:

                section = new Cabecalho(contexto);
                break;

            case Pdf.Seccoes.ID_CLIENTE:

                section = new IdentificacaoCliente(registoVisita.dadosCliente);
                break;

            case Pdf.Seccoes.ID_TRABALHOS_REALIZADOS:

                section = new TrabalhosRealizados(contexto, registoVisita.trabalhoRealizados);
                break;

            case Pdf.Seccoes.ID_HOMOLOGACAO:

                section = new Homologacao(contexto);
                break;

            case Pdf.Seccoes.ID_RUBRICA:

                section = new Assinatura(registoVisita.imagem);
                break;


            default:
                break;

        }

        return section;
    }
}
