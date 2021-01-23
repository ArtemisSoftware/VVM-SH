package com.vvm.sh.repositorios;

import com.vvm.sh.baseDados.dao.CertificadoTratamentoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.documentos.templates.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.modelos.RelatorioCertificadoTratamento;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.email.CredenciaisEmail;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class CertificadoTratamentoRepositorio implements Repositorio<CertificadoTratamentoResultado> {

    private final CertificadoTratamentoDao certificadoTratamentoDao;
    private final ImagemDao imagemDao;
    private final PdfDao pdfDao;
    public final ResultadoDao resultadoDao;
    private final int api;

    public CertificadoTratamentoRepositorio(int api, CertificadoTratamentoDao certificadoTratamentoDao, ImagemDao imagemDao, PdfDao pdfDao, ResultadoDao resultadoDao) {
        this.certificadoTratamentoDao = certificadoTratamentoDao;
        this.imagemDao = imagemDao;
        this.pdfDao = pdfDao;
        this.resultadoDao = resultadoDao;
        this.api = api;
    }


    @Override
    public Single<Long> inserir(CertificadoTratamentoResultado item) {
        return certificadoTratamentoDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(CertificadoTratamentoResultado item) {
        return  certificadoTratamentoDao.atualizar(item);
    }

    public Maybe<CertificadoTratamentoResultado> obterCertificado(int idAtividade){
        return certificadoTratamentoDao.obterCertificadoTratamento(idAtividade);
    }

    public Observable<RelatorioCertificadoTratamento> obterRelatorioCertificadoTratamento(int idAtividade){
        return certificadoTratamentoDao.obterRelatorioCertificadoTratamento(idAtividade);
    }



    public Flowable<? extends Number> gravarAssinatura(ImagemResultado imagemResultado) {
        return Single.concat(imagemDao.remover(imagemResultado.id, imagemResultado.origem), imagemDao.inserir(imagemResultado));
    }


    /**
     * Metodo que permite obter os dados do pdf
     * @param idTarefa o identificador da tarefa
     * @return os dados do pdf
     */
//    public Maybe<DadosCertificadoTratamento> obtePdf(int idTarefa, String idUtilizador) {
//
//        return Maybe.zip(
//                registoVisitaDao.obterDadosEmail(idTarefa, api),
//                pdfDao.obterDadosCliente(idTarefa),
//                pdfDao.obterTrabalhosRealizadosRegistados(idTarefa, api),
//                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_CERTIFICADO_TRATAMENTO, idUtilizador),
//                pdfDao.obterFraseApoio(Identificadores.ID_FRASE_APOIO_REGISTO_VISITA, api),
//                new Function5<CredenciaisEmail, DadosCliente, Cert, Rubrica, String, DadosCertificadoTratamento>() {
//                    @Override
//                    public DadosCertificadoTratamento apply(CredenciaisEmail credenciaisEmail, DadosCliente dadosCliente, List<TrabalhoRealizado> trabalhoRealizados, Rubrica rubrica, String referencia) throws Exception {
//
//                        DadosCertificadoTratamento registoVisita = new DadosCertificadoTratamento();
//                        registoVisita.dadosCliente = dadosCliente;
//                        registoVisita.trabalhoRealizados = trabalhoRealizados;
//                        registoVisita.rubrica = rubrica;
//                        registoVisita.credenciaisEmail = credenciaisEmail;
//                        return registoVisita;
//                    }
//                });
//
//    }

    /**
     * Metodo que permite sincronizar o envio do email
     * @param idAtividade o identificador da atividade
     */
    public void sincronizar(int idAtividade) {
        certificadoTratamentoDao.sincronizar(idAtividade);
    }






    @Override
    public Single<Integer> remover(CertificadoTratamentoResultado item) {
        return null;
    }
}
