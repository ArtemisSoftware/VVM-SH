package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.ObrigacoesLegaisDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.InformacaoSstResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.documentos.templates.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

public class InformacaoSstRepositorio{

    private final InformacaoSstDao informacaoSstDao;
    private final ObrigacoesLegaisDao obrigacoesLegaisDao;
    private final ImagemDao imagemDao;
    private final PdfDao pdfDao;
    public final ResultadoDao resultadoDao;
    private final int api;
    public final ResultadoId resultadoId;

    public InformacaoSstRepositorio(int api, @NonNull InformacaoSstDao informacaoSstDao, @NonNull ObrigacoesLegaisDao obrigacoesLegaisDao,
                                    @NonNull ImagemDao imagemDao, @NonNull PdfDao pdfDao,
                                    @NonNull ResultadoDao resultadoDao, ResultadoId resultadoId) {

        this.informacaoSstDao = informacaoSstDao;
        this.obrigacoesLegaisDao = obrigacoesLegaisDao;
        this.resultadoDao = resultadoDao;
        this.imagemDao = imagemDao;
        this.pdfDao = pdfDao;
        this.api = api;
        this.resultadoId = resultadoId;
    }


    public Single<Long> inserir(ObrigacaoLegalResultado item) {
        return obrigacoesLegaisDao.inserir(item);
    }

    public Single<Long> inserir(InformacaoSstResultado item) {
        return informacaoSstDao.inserir(item);
    }

    public Single<Integer> atualizar(InformacaoSstResultado item) {
        return informacaoSstDao.atualizar(item);
    }

    public Single<Integer> remover(int idTarefa, int id){
        return obrigacoesLegaisDao.remover(idTarefa, id);
    }


    public Observable<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa){
        return obrigacoesLegaisDao.obterObrigacoesLegais(idTarefa, api);
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


}
