package com.vvm.sh.documentos.registoVisita.capitulos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Chapter;
import com.titan.pdfdocumentlibrary.bundle.Section;
import com.titan.pdfdocumentlibrary.models.Index;
import com.vvm.sh.documentos.registoVisita.seccoes.TrabalhosRealizados;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.documentos.registoVisita.seccoes.Assinatura;
import com.vvm.sh.documentos.registoVisita.modelos.Cabecalho;
import com.vvm.sh.documentos.registoVisita.seccoes.Homologacao;
import com.vvm.sh.documentos.registoVisita.seccoes.IdentificacaoCliente;
import com.vvm.sh.documentos.registoVisita.seccoes.Observacoes;
import com.vvm.sh.util.constantes.Pdf;

import java.util.ArrayList;
import java.util.List;

public class Capitulo_RegistaVisita extends Chapter {

    private Context contexto;
    private DadosRegistoVisita registoVisita;


    public Capitulo_RegistaVisita(Context contexto, DadosRegistoVisita registoVisita) {
        super(1);
        this.contexto = contexto;
        this.registoVisita = registoVisita;
    }


    @Override
    protected List<Index> getChapterIndexes() {

        List<Index> indexList = new ArrayList<>();

        //indexList.add(Pdf.Seccoes.CABECALHO);
        indexList.add(Pdf.Seccoes.CLIENTE);

        indexList.add(Pdf.Seccoes.TRABALHOS_REALIZADOS);
        indexList.add(Pdf.Seccoes.HOMOLOGACAO);

       indexList.add(Pdf.Seccoes.OBSERVACAO);
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

                section = new IdentificacaoCliente(contexto, registoVisita.cliente);
                break;

            case Pdf.Seccoes.ID_TRABALHOS_REALIZADOS:

                section = new TrabalhosRealizados(contexto, registoVisita.trabalhoRealizados);
                break;

            case Pdf.Seccoes.ID_HOMOLOGACAO:

                section = new Homologacao(contexto, registoVisita.cliente);
                break;


            case Pdf.Seccoes.ID_OBSERVACAO:

                section = new Observacoes(contexto, registoVisita.cliente.registo.observacao, Pdf.TipoObservacao.TIPO_QUADRO);
                break;

            case Pdf.Seccoes.ID_RUBRICA:

                section = new Assinatura(contexto, registoVisita.rubrica);
                break;


            default:
                break;

        }

        return section;
    }
}
