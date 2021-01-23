package com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.templates.certificadoTratamento.CertificadoTratamento;
import com.vvm.sh.documentos.templates.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.repositorios.CertificadoTratamentoRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.modelos.RelatorioCertificadoTratamento;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CertificadoTratamentoViewModel extends BaseViewModel {

    private final CertificadoTratamentoRepositorio certificadoTratamentoRepositorio;

    public MutableLiveData<RelatorioCertificadoTratamento> relatorio;
    public MutableLiveData<CertificadoTratamentoResultado> certificado;


    @Inject
    public CertificadoTratamentoViewModel(CertificadoTratamentoRepositorio certificadoTratamentoRepositorio){

        this.certificadoTratamentoRepositorio = certificadoTratamentoRepositorio;
        relatorio = new MutableLiveData<>();
        certificado = new MutableLiveData<>();
    }


    /**
     * Metodo que permite gravar um registo
     * @param registo os dados do registo
     */
    public void gravar(CertificadoTratamentoResultado registo){

        if(certificado.getValue() == null){

            certificadoTratamentoRepositorio.inserir(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Long>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Long aLong) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{
            certificadoTratamentoRepositorio.atualizar(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }

                    );
        }
    }




    /**
     * Metodo que permite gravar um registo
     * @param idTarefa o identificador da tarefa
     * @param idAtividade o identificador da atividade
     * @param imagem os dados da imagem
     */
    public void gravar(int idTarefa, int idAtividade, byte[] imagem) {

        ImagemResultado imagemResultado = new ImagemResultado(idTarefa, idAtividade, Identificadores.Imagens.IMAGEM_ASSINATURA_CERTIFICADO_TRATAMENTO, imagem);

        Disposable d = certificadoTratamentoRepositorio.gravarAssinatura(imagemResultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(

                        new Consumer<List<? extends Number>>() {
                            @Override
                            public void accept(List<? extends Number> numbers) throws Exception {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }
                        }
                );

        disposables.add(d);
    }




    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter o relatorio do cetificado de tratamento
     * @param idAtividade  identificador da atividade pendente
     */
    public void obterRelatorio(int idAtividade){

        //existeRelatorio(idTarefa);

        certificadoTratamentoRepositorio.obterRelatorioCertificadoTratamento(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<RelatorioCertificadoTratamento>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(RelatorioCertificadoTratamento registo) {
                                relatorio.setValue(registo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }

                );
    }



    /**
     * Metodo que permite obter o certificado
     * @param idAtividade  identificador da atividade pendente
     */
    public void obterCertificado(int idAtividade){

        showProgressBar(true);

        certificadoTratamentoRepositorio.obterCertificado(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<CertificadoTratamentoResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(CertificadoTratamentoResultado resultado) {
                                certificado.setValue(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );
    }




    /**
     * Metodo que permite pré-visualizar o pdf
     * @param contexto
     * @param idTarefa
     * @param idUtilizador
     */
//    private void gerarPdf(Context contexto, int idTarefa, String idUtilizador, int acao) {
//
//        showProgressBar(true);
//
//        certificadoTratamentoRepositorio.obtePdf(idTarefa, idUtilizador)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                        new MaybeObserver<DadosRegistoVisita>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposables.add(d);
//                            }
//
//                            @Override
//                            public void onSuccess(DadosRegistoVisita registo) {
//                                if(acao == PRE_VISUALIZAR_PDF) {
//                                    preVisualizarPdf(contexto, idTarefa, registo);
//                                }
//                                else{
//                                    enviarPdf(contexto, idTarefa, registo);
//                                }
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                showProgressBar(false);
//                            }
//                        }
//
//                );
//    }
//
//



    @Override
    public Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate registo) {
        return new CertificadoTratamento(contexto, idTarefa, idAtividade, (DadosCertificadoTratamento) registo);
    }

}
