package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.documentos.modelos.Rubrica;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.email.CredenciaisEmail;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function5;

public class InformacaoSstRepositorio implements Repositorio<ObrigacaoLegalResultado>{

    private final InformacaoSstDao informacaoSstDao;
    private final ImagemDao imagemDao;
    private final PdfDao pdfDao;
    public final ResultadoDao resultadoDao;
    private final int api;
    public final ResultadoId resultadoId;

    public InformacaoSstRepositorio(int api, @NonNull InformacaoSstDao informacaoSstDao,
                                    @NonNull ImagemDao imagemDao, @NonNull PdfDao pdfDao,
                                    @NonNull ResultadoDao resultadoDao, ResultadoId resultadoId) {

        this.informacaoSstDao = informacaoSstDao;
        this.resultadoDao = resultadoDao;
        this.imagemDao = imagemDao;
        this.pdfDao = pdfDao;
        this.api = api;
        this.resultadoId = resultadoId;
    }

    @Override
    public Single<Long> inserir(ObrigacaoLegalResultado item) {
        return informacaoSstDao.inserir(item);
    }

    public Single<Integer> remover(int idTarefa, int id){
        return informacaoSstDao.remover(idTarefa, id);
    }


    public Observable<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa){
        return informacaoSstDao.obterObrigacoesLegais(idTarefa, api);
    }


    public Observable<RelatorioInformacaoSst> obterRelatorioInformacaoSst(int idTarefa){
        return informacaoSstDao.obterRelatorioInformacaoSst(idTarefa);
    }


    public Flowable<? extends Number> gravarAssinatura(ImagemResultado imagemResultado) {
        return Single.concat(imagemDao.remover(imagemResultado.id, imagemResultado.origem), imagemDao.inserir(imagemResultado));
    }



    /**
     * Metodo que permite obter os dados do pdf
     * @param idTarefa o identificador da tarefa
     * @return os dados do pdf
     */
    public Maybe<DadosInformacaoSst> obtePdf(int idTarefa, String idUtilizador) {

        return Maybe.zip(
                pdfDao.obterDadosCliente(idTarefa),
                pdfDao.obterObrigacoesLegais(idTarefa, api),
                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_INFORMACAO_SST, idUtilizador),
                new Function3<DadosCliente, List<ObrigacaoLegal>, Rubrica, DadosInformacaoSst>() {
                    @Override
                    public DadosInformacaoSst apply(DadosCliente cliente, List<ObrigacaoLegal> obrigacaoLegals, Rubrica rubrica) throws Exception {
                        DadosInformacaoSst dados = new DadosInformacaoSst();
                        dados.cliente = cliente;
                        dados.obrigacaoLegal = obrigacaoLegals;
                        dados.rubrica = rubrica;
                        return dados;
                    }
                });
    }



    @Override
    public Single<Integer> atualizar(ObrigacaoLegalResultado item) {
        return null;
    }

    @Override
    public Single<Integer> remover(ObrigacaoLegalResultado item) {
        return null;
    }
}
