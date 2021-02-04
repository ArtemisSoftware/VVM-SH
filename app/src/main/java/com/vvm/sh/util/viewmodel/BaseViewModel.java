package com.vvm.sh.util.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.OnDocumentoListener;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.email.Email;
import com.vvm.sh.util.excepcoes.DocumentoPdfException;
import com.vvm.sh.util.excepcoes.MetodoWsInvalidoException;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.PdfUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.mail.Transport;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseViewModel extends ViewModel {


    protected final CompositeDisposable disposables;
    protected MutableLiveData<Recurso> messagemLiveData;


    public MutableLiveData<Integer> loading;



    public MutableLiveData<List<Tipo>> estados;



    @Inject
    protected VvmshBaseDados vvmshBaseDados;

    protected int limite = AppConfig.NUMERO_RESULTADOS_QUERY;



    public BaseViewModel() {

        this.disposables = new CompositeDisposable();
        messagemLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        estados = new MutableLiveData<>();

        showProgressBar(false);
    }



    public MutableLiveData<Recurso> observarMessagem(){
        return messagemLiveData;
    }


    /**
     * Metodo que permite gravar o resultado de forma aos dados poderem ser enviados para o ws
     * @param resultadoDao
     * @param idTarefa o identificador da tarefa
     * @param idResultado o identificador do resultado
     */
    protected void gravarResultado(ResultadoDao resultadoDao, int idTarefa, ResultadoId idResultado){

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, resultadoDao);
        servico.execute(new Resultado(idTarefa, idResultado));
    }


    protected void abaterAtividadePendente(ResultadoDao resultadoDao, int idTarefa, int idAtividade){

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, resultadoDao, idAtividade);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }





    /**
     * Metodo que permite obter os generos
     */
    protected void obterGeneros(MutableLiveData<List<Tipo>> generos){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.GENERO_MASCULINO);
        estado.add(TiposConstantes.GENERO_FEMININO);
        generos.setValue(estado);

    }

    /**
     * Metodo que permite obter as opcoes dos registos
     */
    protected void obterOpcoesRegistos() {
        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.OpcoesRegistos.CONSULTAR);
        estado.add(TiposConstantes.OpcoesRegistos.NOVOS_REGISTOS);
        estados.setValue(estado);
    }


    protected void obterOpcoes(MutableLiveData<List<Tipo>> liveData, Tipo [] opcoes){

        List<Tipo> estado = new ArrayList<>();

        for (Tipo tipo : opcoes) {
            estado.add(tipo);
        }

        liveData.setValue(estado);
    }



    public ArrayList<Integer> obterRegistosSelecionados(List<Tipo> registos){

        ArrayList<Integer> resultado = new ArrayList<>();

        if(registos != null) {

            for (Tipo item : registos) {
                resultado.add(item.id);
            }
        }

        return resultado;
    }







    protected List<CategoriaProfissionalResultado> ObterCategoriasProfissionais(List<Tipo> registo, int id, int origem){

        List<CategoriaProfissionalResultado> categorias = new ArrayList<>();
        for (Tipo categoria : registo) {
            categorias.add(new CategoriaProfissionalResultado(id, categoria.id, origem));
        }

        return categorias;
    }



    protected void showProgressBar(boolean visible) {
        loading.setValue(visible ? View.VISIBLE : View.INVISIBLE);
    }


    /**
     * Metodo que permite formatar um erro
     * @param e
     */
    protected void formatarErro(Throwable e){

        Gson gson = new GsonBuilder().create();

        if (e instanceof RespostaWsInvalidaException){

            Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

            messagemLiveData.setValue(Recurso.erro(codigo, "Upload/Download"));
        }
        else if (e instanceof MetodoWsInvalidoException){

            Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);
            messagemLiveData.setValue(Recurso.erro(codigo, "Comunicação"));
        }
        else if (e instanceof TipoInexistenteException){

            messagemLiveData.setValue(Recurso.erro(e.getMessage(), "Atualizacoes"));
        }
        else {
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }


    //-------------------
    //Pdf
    //-------------------


    protected void gerarPdf(Context contexto, int idTarefa, int idAtividade, String idUtilizador, OnDocumentoListener.OnVisualizar listener, OnDocumentoListener.AcaoDocumento acao) {

        showProgressBar(true);

        listener.obterPdf(idTarefa, idAtividade, idUtilizador)

                .map(new Function<DadosTemplate, DadosPdf>() {
                    @Override
                    public DadosPdf apply(DadosTemplate dadosTemplate) throws Exception {
                        Template template = PdfUtil.obterTemplate(contexto, idTarefa, idAtividade, dadosTemplate);

                        if(template != null) {
                            template.createFile();
                            return new DadosPdf(dadosTemplate.credenciaisEmail, template);
                        }
                        else throw new DocumentoPdfException("Template do pdf indisponível");

                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<DadosPdf>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(DadosPdf registo) {

                                executarPdf(contexto, idTarefa, idAtividade, registo, listener, acao);

                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                                formatarErro(e);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );
    }


    private void executarPdf(Context contexto, int idTarefa, int idAtividade, DadosPdf dadosPdf, OnDocumentoListener.OnVisualizar listener, OnDocumentoListener.AcaoDocumento acao){

        switch (acao){

            case PRE_VISUALIZAR_PDF:

                dadosPdf.template.openPdf();
                showProgressBar(false);
                break;

            case ENVIAR_PDF:
            case ENVIAR_PDF__DADOS_FTP:

                enviarPdf(idTarefa, idAtividade, listener, dadosPdf, acao);
                break;


            default:
                showProgressBar(false);
                messagemLiveData.setValue(Recurso.erro(Sintaxe.Alertas.INSTRUCAO_PDF_DESCONHECIDA));
                break;

        }

    }


    /**
     * Metodo que permite enviar um pdf
     * @param idTarefa
     * @param idAtividade
     * @param listener
     * @param dadosPdf
     * @param acao
     */
    private void enviarPdf(int idTarefa, int idAtividade, OnDocumentoListener.OnVisualizar listener, DadosPdf dadosPdf, OnDocumentoListener.AcaoDocumento acao){

        Single<Codigo> envioPdf = null;

        Single<Codigo> envioEmail = Single.fromCallable(new Callable<Codigo>() {
            @Override
            public Codigo call() throws Exception {
                // Doing something long in AysnTask doInBackGround off UI thread.

                Email email = new Email(dadosPdf.credenciaisEmail);
                email.adicionarAnexo(dadosPdf.template.getPdfFile().getAbsolutePath());
                email.configurar();

                Transport.send(email.mensagem);

                return Identificadores.CodigosWs.Codigo_110;
            }
        });


        switch (acao){

            case ENVIAR_PDF:

                envioPdf = envioEmail;
                break;


            case ENVIAR_PDF__DADOS_FTP:

                envioPdf = envioEmail.flatMap(new Function<Codigo, SingleSource<Codigo>>() {
                    @Override
                    public SingleSource<Codigo> apply(Codigo codigo) throws Exception {
                        return listener.uploadRelatorio(idTarefa, dadosPdf.template.getPdfFile().getAbsolutePath());
                    }
                });
                break;
        }

        if(envioPdf == null){
            showProgressBar(false);
            messagemLiveData.setValue(Recurso.erro(Sintaxe.Alertas.INSTRUCAO_PDF_DESCONHECIDA));
        }
        else {

            disposables.add(

                envioPdf
                    .flatMap(new Function<Codigo, SingleSource<Integer>>() {
                        @Override
                        public SingleSource<Integer> apply(Codigo s) throws Exception {
                            return listener.sincronizar(idTarefa, idAtividade);
                        }
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<Integer>() {
                                @Override
                                public void accept(Integer s) throws Exception {
                                    showProgressBar(false);
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.EMAIL_ENVIADO_COM_SUCESSO));
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    showProgressBar(false);
                                    formatarErro(throwable);
                                }
                            }
                    )
            );
        }

    }





    private class DadosPdf{


        public CredenciaisEmail credenciaisEmail;
        public Template template;

        public DadosPdf(CredenciaisEmail credenciaisEmail, Template template) {
            this.credenciaisEmail = credenciaisEmail;
            this.template = template;
        }
    }

}



