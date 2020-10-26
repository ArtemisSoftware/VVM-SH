package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.documentos.modelos.Rubrica;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function5;

public class RegistoVisitaRepositorio {


    private final RegistoVisitaDao registoVisitaDao;
    private final TrabalhosRealizadosDao trabalhosRealizadosDao;
    private final ImagemDao imagemDao;
    private final PdfDao pdfDao;
    public final ResultadoDao resultadoDao;
    private final int api;

    public RegistoVisitaRepositorio(int api, @NonNull RegistoVisitaDao registoVisitaDao, @NonNull TrabalhosRealizadosDao trabalhosRealizadosDao,
                                    @NonNull ImagemDao imagemDao, @NonNull PdfDao pdfDao,
                                    @NonNull ResultadoDao resultadoDao) {

        this.registoVisitaDao = registoVisitaDao;
        this.trabalhosRealizadosDao = trabalhosRealizadosDao;
        this.resultadoDao = resultadoDao;
        this.imagemDao = imagemDao;
        this.pdfDao = pdfDao;
        this.api = api;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(RegistoVisitaResultado registo){
        return registoVisitaDao.inserir(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(RegistoVisitaResultado registo){
        return registoVisitaDao.atualizar(registo);
    }


    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(TrabalhoRealizadoResultado registo){
        return trabalhosRealizadosDao.inserir(registo);
    }


    public Single<Integer> remover(int idTarefa, int id){
        return trabalhosRealizadosDao.remover(idTarefa, id);
    }


    public Observable<List<TrabalhoRealizado>> obterTrabalhosRealizados(int idTarefa){
        return trabalhosRealizadosDao.obterTrabalhosRealizados(idTarefa, api);
    }


    public Maybe<DadosCliente> obterDadosCliente(int idTarefa){
        return registoVisitaDao.obterDadosCliente(idTarefa);
    }


    public Observable<RelatorioRegistoVisita> obterRelatorioRegistoVisita(int idTarefa){
        return registoVisitaDao.obterRelatorioRegistoVisita(idTarefa);
    }


    public Flowable<? extends Number> gravarAssinatura(ImagemResultado imagemResultado) {

        return Single.concat(imagemDao.remover(imagemResultado.id, imagemResultado.origem), imagemDao.inserir(imagemResultado));

    }


    /**
     * Metodo que permite obter os dados do pdf
     * @param idTarefa o identificador da tarefa
     * @return os dados do pdf
     */
    public Maybe<DadosRegistoVisita> obtePdf(int idTarefa, String idUtilizador) {

        return Maybe.zip(
                registoVisitaDao.obterDadosEmail(idTarefa, api),
                pdfDao.obterDadosCliente(idTarefa),
                pdfDao.obterTrabalhosRealizadosRegistados(idTarefa, api),
                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_REGISTO_VISITA, idUtilizador),
                pdfDao.obterFraseApoio(Identificadores.ID_FRASE_APOIO_REGISTO_VISITA, api),
                new Function5<CredenciaisEmail, DadosCliente, List<TrabalhoRealizado>, Rubrica, String, DadosRegistoVisita>() {
                    @Override
                    public DadosRegistoVisita apply(CredenciaisEmail credenciaisEmail, DadosCliente dadosCliente, List<TrabalhoRealizado> trabalhoRealizados, Rubrica rubrica, String referencia) throws Exception {

                        DadosRegistoVisita registoVisita = new DadosRegistoVisita();
                        registoVisita.dadosCliente = dadosCliente;
                        registoVisita.trabalhoRealizados = trabalhoRealizados;
                        registoVisita.rubrica = rubrica;
                        registoVisita.referencia = referencia;
                        registoVisita.credenciaisEmail = credenciaisEmail;
                        return registoVisita;
                    }
                });

    }

    /**
     * Metodo que permite sincronizar o envio do email
     * @param idTarefa o identificador da tarefa
     */
    public void sincronizar(int idTarefa) {
        registoVisitaDao.sincronizar(idTarefa);
    }
}
