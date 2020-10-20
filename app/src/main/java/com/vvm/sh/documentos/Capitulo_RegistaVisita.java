package com.vvm.sh.documentos;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_RegistaVisita extends Chapter {

    public Capitulo_RegistaVisita() {
        super(1);
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


//        indexList.add(new Index(1, "Main Section"));
//        indexList.add(new Index(2, "No border Section"));
//        indexList.add(new Index(3, "Uneven Section"));
//        indexList.add(new Index(4, "Empty cells Section"));
//        indexList.add(new Index(5, "Uneven table Section"));
//        indexList.add(new Index(6, "Font Section"));
        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {


            case Pdf.Seccoes.ID_TRABALHOS_REALIZADOS:

                //--section = new MainSection();
                break;


            default:
                break;

        }

        return section;
    }
}
