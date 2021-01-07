package com.vvm.sh.documentos.informacaoSst.capitulos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.documentos.informacaoSst.seccoes.Assinatura;
import com.vvm.sh.documentos.informacaoSst.seccoes.Cliente;
import com.vvm.sh.documentos.informacaoSst.seccoes.ObrigacoesLegais;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_InformacaoSst extends Chapter {

    private Context contexto;

    public Capitulo_InformacaoSst(Context contexto) {
        super(1);
        this.contexto = contexto;
    }

    @Override
    protected List<Index> getChapterIndexes() {
        List<Index> indexList = new ArrayList<>();

        indexList.add(Pdf.Seccoes.CLIENTE);
        indexList.add(Pdf.Seccoes.OBRIGACOES_LEGAIS);
        indexList.add(Pdf.Seccoes.RUBRICA);

        return indexList;
    }

    @Override
    protected Section getSection(Index index) {
        Section section = null;

        switch (index.getId()) {



            case Pdf.Seccoes.ID_CLIENTE:

                //section = new Cliente(contexto, registoVisita.dadosCliente);
                break;

            case Pdf.Seccoes.ID_OBRIGACOES_LEGAIS:

                //section = new ObrigacoesLegais(contexto, registoVisita.trabalhoRealizados);
                break;


            case Pdf.Seccoes.ID_RUBRICA:

                //section = new Assinatura(contexto, registoVisita.rubrica);
                break;


            default:
                break;

        }

        return section;
    }
}
