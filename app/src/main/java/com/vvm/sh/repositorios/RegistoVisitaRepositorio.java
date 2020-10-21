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
import com.vvm.sh.documentos.RegistoVisita;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function3;

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


    public Observable<RelatorioRegistoVisita> obterValidadeRegistoVisita(int idTarefa){
        return registoVisitaDao.obterValidadeRegistoVisita(idTarefa);
    }


    public Flowable<? extends Number> gravarAssinatura(ImagemResultado imagemResultado) {

        return Single.concat(imagemDao.remover(imagemResultado.id, imagemResultado.origem), imagemDao.inserir(imagemResultado));

    }


    /**
     * Metodo que permite obter os dados do pdf
     * @param idTarefa o identificador da tarefa
     * @return os dados do pdf
     */
    public Maybe<RegistoVisita> obtePdf(int idTarefa, int idUtilizador) {

        return Maybe.zip(
                pdfDao.obterDadosCliente(idTarefa),
                pdfDao.obterTrabalhosRealizadosRegistados(idTarefa, api),
                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_REGISTO_VISITA, idUtilizador),
                new Function3<DadosCliente, List<TrabalhoRealizado>, Rubrica, RegistoVisita>() {
                    @Override
                    public RegistoVisita apply(DadosCliente dadosCliente, List<TrabalhoRealizado> trabalhoRealizados, Rubrica rubrica) throws Exception {

                        RegistoVisita registoVisita = new RegistoVisita();
                        registoVisita.dadosCliente = dadosCliente;
                        registoVisita.trabalhoRealizados = trabalhoRealizados;
                        registoVisita.rubrica = rubrica;

                        return registoVisita;
                    }
                });

    }
}
