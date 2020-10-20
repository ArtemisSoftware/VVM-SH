package com.vvm.sh.documentos;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_RegistaVisita extends Chapter {

    private RegistoVisita registoVisita;


    public Capitulo_RegistaVisita(RegistoVisita registoVisita) {
        super(1);
        this.registoVisita = registoVisita;
    }


    @Override
    protected List<Index> getChapterIndexes() {

        List<Index> indexList = new ArrayList<>();

//        seccoes.add(SECCAO_CABECALHO);
//
//        seccoes.add(SECCAO_CLIENTE);
        indexList.add(Pdf.Seccoes.TRABALHOS_REALIZADOS);
//        seccoes.add(SECCAO_HOMOLOGACAO);
//
//        seccoes.add(SECCAO_OBSERVACAO);
//        seccoes.add(SECCAO_RUBRICA);

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {


            case Pdf.Seccoes.ID_TRABALHOS_REALIZADOS:

                section = new TrabalhosRealizados(registoVisita.trabalhoRealizados);
                break;


            default:
                break;

        }

        return section;
    }
}
