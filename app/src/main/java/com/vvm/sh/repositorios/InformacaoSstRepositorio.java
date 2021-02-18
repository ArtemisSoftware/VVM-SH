package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.InformacaoSstDao;
import com.vvm.sh.baseDados.dao.ObrigacoesLegaisDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.InformacaoSstResultado;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.metodos.ConversorUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Function6;

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
    public Maybe<DadosTemplate> obtePdf(int idTarefa, String idUtilizador) {

        int api = 2;
        return Maybe.zip(
                pdfDao.obterDadosEmail(idTarefa, Sintaxe.Email.TITULO_INFORMACAO_SST, Identificadores.FrasesApoio.ID_FRASE_APOIO_CORPO_EMAIL_INFORMACAO_SST, api),
                pdfDao.obterInfoSst_ClienteFrasesApoio(Identificadores.FrasesApoio.ID_FRASE_APOIO_INFORMACAO_SST__CLIENTE, api),
                pdfDao.obterDadosCliente(idTarefa),
                pdfDao.obterObrigacoesLegais(idTarefa, api),
                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_INFORMACAO_SST, idUtilizador),
                pdfDao.obterInfoSst_RodapeFrasesApoio(Identificadores.FrasesApoio.ID_FRASE_APOIO_INFORMACAO_SST__RODAPE, api),
                new Function6<CredenciaisEmail, List<String>, DadosCliente, List<ObrigacaoLegal>, Rubrica, List<String>, DadosTemplate>() {
                    @Override
                    public DadosTemplate apply(CredenciaisEmail credenciaisEmail, List<String> frasesApoio, DadosCliente dadosCliente,
                                               List<ObrigacaoLegal> obrigacaoLegals, Rubrica rubrica, List<String> rodapes) throws Exception {

                        DadosInformacaoSst dados = new DadosInformacaoSst();
                        dados.cliente = dadosCliente;
                        dados.obrigacaoLegal = obrigacaoLegals;
                        dados.rubrica = rubrica;
                        dados.credenciaisEmail = credenciaisEmail;
                        dados.frasesApoio = frasesApoio;
                        dados.rodapes = rodapes;
                        return dados;
                    }
                }
        );
    }



    /**
     * Metodo que permite sincronizar a informação do sst
     * @param idTarefa
     * @return
     */
    public Single<Integer> sincronizar(int idTarefa) {
        return informacaoSstDao.sincronizar(idTarefa);
    }



}
