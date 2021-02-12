package com.vvm.sh.repositorios;

import com.vvm.sh.baseDados.dao.CertificadoTratamentoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.documentos.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.modelos.RelatorioCertificadoTratamento;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.email.CredenciaisEmail;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function4;

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
    public Maybe<DadosTemplate> obtePdf(int idTarefa, int idAtividade, String idUtilizador) {

        return Maybe.zip(
                pdfDao.obterDadosEmail(idTarefa, Sintaxe.Email.TITULO_EMAIL_CERTIFICADO_TRATAMENTO, Identificadores.FrasesApoio.ID_FRASE_APOIO_CORPO_EMAIL_CERTIFICIADO_TRATAMENTO, api),
                pdfDao.obterDadosCliente(idTarefa),
                pdfDao.obterCertificado(idAtividade),
                pdfDao.obterRubrica(idTarefa, Identificadores.Imagens.IMAGEM_ASSINATURA_CERTIFICADO_TRATAMENTO, idUtilizador),

                new Function4<CredenciaisEmail, DadosCliente, CertificadoTratamentoResultado, Rubrica, DadosTemplate>() {
                    @Override
                    public DadosTemplate apply(CredenciaisEmail credenciaisEmail, DadosCliente dadosCliente, CertificadoTratamentoResultado certificadoTratamentoResultado, Rubrica rubrica) throws Exception {

                        DadosCertificadoTratamento dados = new DadosCertificadoTratamento();
                        dados.cliente = dadosCliente;
                        dados.certificadoTratamento = certificadoTratamentoResultado;
                        dados.rubrica = rubrica;
                        dados.credenciaisEmail = credenciaisEmail;
                        return dados;
                    }
                });

    }

    /**
     * Metodo que permite sincronizar o certificado de tratamento
     * @param idAtividade o identificador da atividade
     * @return
     */
    public Single<Integer> sincronizar(int idAtividade) {
        return certificadoTratamentoDao.sincronizar(idAtividade);
    }






    @Override
    public Single<Integer> remover(CertificadoTratamentoResultado item) {
        return null;
    }
}
