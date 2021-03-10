package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaAveriguacao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.modelos.Formando;
import com.vvm.sh.ui.transferencias.modelos.DadosPendencia;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class TransferenciasRepositorio {


    private final SegurancaAlimentarApi apiSA;
    private final SegurancaTrabalhoApi apiST;

    private final TransferenciasDao transferenciasDao;


    public TransferenciasRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST,
                                     @NonNull TransferenciasDao transferenciasDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.transferenciasDao = transferenciasDao;
    }


    //---------------------------
    //API
    //---------------------------


    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<Sessao> obterTrabalho(String idUtilizador) {

        if(AppConfig.APP_MODO == AppConfig.APP_SA) {

            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador)
                    .map(new Function<ISessao, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao) throws Exception {
                            Sessao sessao = new Sessao();
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    });

        }
        else {
            return Single.zip(
                    apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                            Sessao sessao = new Sessao();
                            sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    }
            );
        }
    }


    /**
     * Metodo que permite obter o trabalho de um dia especifico para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param data da data do dia (YYYY-mm-dd)
     * @return o trabalho do dia
     */
    public Single<Sessao> obterTrabalho(String idUtilizador, String data) {

        if(AppConfig.APP_MODO == AppConfig.APP_SA) {

            return apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data)
                    .map(new Function<ISessao, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao) throws Exception {
                            Sessao sessao = new Sessao();
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    });

        }
        else {
            return Single.zip(
                    apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador, data),
                    apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER, idUtilizador),
                    new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                        @Override
                        public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                            Sessao sessao = new Sessao();
                            sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                            sessao.iSessao = iSessao;
                            return sessao;
                        }
                    }
            );
        }

    }


    /**
     * Metodo que permite submeter os dados para o web service
     * @param dados os dados a submeter
     * @param idUtilizador o identificador do utilizador
     * @param id o identificador unico do bloco de imagens
     * @param messageDigest o messageDigest
     * @return um codigo com o resultado da submissao
     */
    public Single<Codigo> submeterDados(String dados, String idUtilizador, String id, String messageDigest) {

        if(AppConfig.APP_MODO == AppConfig.APP_SA) {
            return apiSA.submeterDados(SegurancaAlimentarApi.HEADER, dados, idUtilizador, id, messageDigest);
        }
        else{
            return apiST.submeterDados(SegurancaTrabalhoApi.HEADER, dados, idUtilizador, id, messageDigest);
        }
    }



    public Observable<Codigo> upload(DadosUpload dadosUpload, List<Upload> uploads){

        Gson gson = new Gson();

        List<Observable<Codigo>> observables = new ArrayList<>();

        if(dadosUpload.numeroFicheirosImagens != 0){


            Observable<Codigo> observable__Imagens = uploadImagens(dadosUpload);

//            dadosUpload.formatarImagens();
//
//            for (DadosUpload.DadosImagem dadosImagem: dadosUpload.dadosImagems) {
//                observables.add(submeterImagens(dadosUpload, dadosImagem).toObservable());
//            }
//
//            Observable<Codigo> observable__Imagens = Observable.zip(observables, new Function<Object[], Codigo>() {
//                @Override
//                public Codigo apply(Object[] codigos) throws Exception {
//
//                    if(validarResultadoUpload(codigos) == true){
//                        return Identificadores.CodigosWs.Codigo_100;
//                    }
//                    else{
//                        throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
//                    }
//                }
//            });

            if(AppConfig.APP_MODO == AppConfig.APP_SHT) {
                return submeterDados(dadosUpload).toObservable()
                        .flatMap(new Function<Codigo, ObservableSource<Codigo>>() {
                            @Override
                            public ObservableSource<Codigo> apply(Codigo codigo) throws Exception {

                                if(validarResultadoUpload(codigo) == true){
                                    return observable__Imagens;
                                }
                                else{
                                    throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                                }
                            }
                        })
                        .flatMap(new Function<Codigo, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Codigo codigo) throws Exception {
                                return sincronizar(uploads).toObservable();
                            }
                        })
                        .map(new Function<Object, Codigo>() {
                            @Override
                            public Codigo apply(Object o) throws Exception {

                                if(((int) o) > 0) {
                                    return Identificadores.CodigosWs.Codigo_100;
                                }
                                else{
                                    return Identificadores.CodigosWs.Codigo_600;
                                }
                            }
                        })
                        ;

            }
            else{
                return observable__Imagens
                        .flatMap(new Function<Codigo, ObservableSource<Codigo>>() {
                            @Override
                            public ObservableSource<Codigo> apply(Codigo codigo) throws Exception {
                                return submeterDados(dadosUpload).toObservable();
                            }
                        })
                        .map(new Function<Codigo, Codigo>() {
                            @Override
                            public Codigo apply(Codigo o) throws Exception {

                                if(((Codigo) o).codigo == Identificadores.CodigosWs.ID_100) {
                                    return Identificadores.CodigosWs.Codigo_100;
                                }
                                else{
                                    throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                                }
                            }
                        })
                        .flatMap(new Function<Codigo, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Codigo codigo) throws Exception {
                                return sincronizar(uploads).toObservable();
                            }
                        })
                        .map(new Function<Object, Codigo>() {
                            @Override
                            public Codigo apply(Object o) throws Exception {

                                if(((int) o) > 0) {
                                    return Identificadores.CodigosWs.Codigo_100;
                                }
                                else{
                                    return Identificadores.CodigosWs.Codigo_600;
                                }
                            }
                        });
            }
        }
        else{
            return submeterDados(dadosUpload).toObservable()
                    .map(new Function<Codigo, Codigo>() {
                        @Override
                        public Codigo apply(Codigo o) throws Exception {

                            if(((Codigo) o).codigo == Identificadores.CodigosWs.ID_100) {
                                return Identificadores.CodigosWs.Codigo_100;
                            }
                            else{
                                throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                            }
                        }
                    })
                    .flatMap(new Function<Codigo, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Codigo codigo) throws Exception {
                            return sincronizar(uploads).toObservable();
                        }
                    })
                    .map(new Function<Object, Codigo>() {
                        @Override
                        public Codigo apply(Object o) throws Exception {

                            if(((int) o) > 0) {
                                return Identificadores.CodigosWs.Codigo_100;
                            }
                            else{
                                return Identificadores.CodigosWs.Codigo_600;
                            }
                        }
                    });
        }
    }

    private boolean validarResultadoUpload(Codigo codigo){
        return (codigo.codigo == Identificadores.CodigosWs.ID_100);
    }

    private boolean validarResultadoUpload(Object[] codigos){

        boolean valido = true;

        for (Object item : codigos) {

            if(((Codigo) item).codigo != Identificadores.CodigosWs.ID_100){
                valido = false;
                break;
            }
        }

        return valido;
    }


    private Single<Codigo> submeterDados(DadosUpload dadosUpload) {

        dadosUpload.formatarDados();

        if(AppConfig.APP_MODO == AppConfig.APP_SA) {
            return apiSA.submeterDados(SegurancaAlimentarApi.HEADER,dadosUpload.obterDados(), dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.messageDigest);
        }
        else{
            return apiST.submeterDados(SegurancaTrabalhoApi.HEADER, dadosUpload.obterDados(), dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.messageDigest);
        }
    }


    /**
     * Metodo que permite submeter as imagens para o web service
     * @return um codigo com o resultado da submissao
     */
    private Single<Codigo> submeterImagens(DadosUpload dadosUpload, DadosUpload.DadosImagem dadosImagem) {
        if(AppConfig.APP_MODO == AppConfig.APP_SA) {
            return apiSA.submeterImagens(SegurancaAlimentarApi.HEADER, dadosImagem.blocoImagem, dadosUpload.idUtilizador, dadosUpload.idUpload, dadosImagem.numeroFicheiro + "", dadosImagem.messageDigest);
        }
        else{
            return apiST.submeterImagens(SegurancaTrabalhoApi.HEADER, dadosImagem.blocoImagem, dadosUpload.idUtilizador, dadosUpload.idUpload, dadosImagem.numeroFicheiro + "", dadosImagem.messageDigest);
        }
    }




    /**
     * Metodo que permite obter todas as pendencias
     * @param idUtilizador o identificador do utilizador
     * @return uma lista de pendencias
     */
    public Maybe<DadosPendencia> obterPendencias(String idUtilizador){

        return Maybe.zip(transferenciasDao.obterPendencias(idUtilizador), transferenciasDao.existemDadosUpload(idUtilizador),
                new BiFunction<List<Pendencia>, Boolean, DadosPendencia>() {
                    @Override
                    public DadosPendencia apply(List<Pendencia> pendencias, Boolean aBoolean) throws Exception {

                        DadosPendencia dadosPendencia = new DadosPendencia();
                        dadosPendencia.dadosUpload = aBoolean;
                        dadosPendencia.pendencias = pendencias;
                        return dadosPendencia;
                    }
                });

    }

    public Maybe<DadosPendencia> obterPendencias(String idUtilizador, long data){

        return Maybe.zip(transferenciasDao.obterPendencias(idUtilizador, data), transferenciasDao.existemDadosUpload(idUtilizador),
                new BiFunction<List<Pendencia>, Boolean, DadosPendencia>() {
                    @Override
                    public DadosPendencia apply(List<Pendencia> pendencias, Boolean aBoolean) throws Exception {

                        DadosPendencia dadosPendencia = new DadosPendencia();
                        dadosPendencia.dadosUpload = aBoolean;
                        dadosPendencia.pendencias = pendencias;
                        return dadosPendencia;
                    }
                });
    }


    /**
     * Metodo que permite obter os dados para upload
     * @param idUtilizador o identificador do utilizador
     * @return uma lista de uploads
     */
    public Maybe<List<Upload>> obterUploads(String idUtilizador){
        return transferenciasDao.obterUploads(idUtilizador, false);
    }


    /**
     * Metodo que permite obter os dados para upload de um dia especifico.
     * Serão selecionados os dados já sincronizados e não sincronizados
     * @param idUtilizador o identificador do utilizador
     * @param data a data dos dados
     * @return uma lista de uploads
     */
    public Maybe<List<Upload>> obterUploads(String idUtilizador, long data){
        return transferenciasDao.obterUploads(idUtilizador, data);
    }




    public Observable<Codigo> uploadImagens(DadosUpload dadosUpload){

        dadosUpload.formatarImagens();

        Gson gson = new Gson();
        List<Observable<Codigo>> observables = new ArrayList<>();

        for (DadosUpload.DadosImagem dadosImagem: dadosUpload.dadosImagems) {
            observables.add(submeterImagens(dadosUpload, dadosImagem).toObservable());
        }

        Observable<Codigo> observable = Observable.zip(observables, new Function<Object[], Codigo>() {
            @Override
            public Codigo apply(Object[] codigos) throws Exception {


                if(validarResultadoUpload(codigos) == true){
                    return Identificadores.CodigosWs.Codigo_100;
                }
                else{
                    throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                }

//                boolean valido = true;
//
//                for (Object item : codigos) {
//
//                    if(((Codigo) item).codigo != Identificadores.CodigosWs.ID_100){
//                        valido = false;
//                        break;
//                    }
//                }
//
//                if(valido == true){
//                    return new Codigo(Identificadores.CodigosWs.ID_100, Identificadores.CodigosWs.MSG_100);
//                }
//                else{
//                    Codigo codigo = new Codigo(Identificadores.CodigosWs.ID_600, Identificadores.CodigosWs.MSG_600);
//                    throw new RespostaWsInvalidaException(gson.toJson(codigo, Codigo.class));
//                }
            }
        });

        return observable;
    }




    public Single<Integer> sincronizar(List<Upload> uploads){

        List<Resultado> resultados = new ArrayList<>();

        for(int index = 0; index < uploads.size(); ++index){

            for(int posicao = 0; posicao < uploads.get(index).resultados.size(); ++posicao){

                Resultado item = uploads.get(index).resultados.get(posicao);
                item.sincronizado = true;
                resultados.add(item);
            }
        }

        Resultado registos[] = new Resultado[resultados.size()];

        for(int index = 0; index < resultados.size(); ++index){
            registos[index] = resultados.get(index);
        }


        return transferenciasDao.atualizar(registos);
    }



    //---

    public Single<Codigo> uploadCertificadoTratamento(int idTarefa, String caminhoPdf){

        return transferenciasDao.obterTarefa(idTarefa)
                .flatMap(new Function<Tarefa, SingleSource<Codigo>>() {
                    @Override
                    public SingleSource<Codigo> apply(Tarefa tarefa) throws Exception {
                        return apiSA.submeterCertificadoTratamento(SegurancaAlimentarApi.HEADER,  ConversorUtil.convertPdf__StringBase64(caminhoPdf), tarefa.ordem, tarefa.prefixoCt);
                    }
                });

    }


    public Single<Codigo> uploadInformacaoSst(int idTarefa, String caminhoPdf){

        return transferenciasDao.obterTarefa(idTarefa)
                .flatMap(new Function<Tarefa, SingleSource<Codigo>>() {
                    @Override
                    public SingleSource<Codigo> apply(Tarefa tarefa) throws Exception {
                        return apiST.submeterInfoSST(SegurancaTrabalhoApi.HEADER,  ConversorUtil.convertPdf__StringBase64(caminhoPdf), tarefa.ordem, tarefa.prefixoCt);
                    }
                });

    }


    //---------------------------
    //DOWNLOAD
    //---------------------------

    public boolean existeTarefa(String idUtilizador, IDados dados, long data){
        return transferenciasDao.existeTarefa(idUtilizador, dados.prefixoCt, dados.ordem, data);
    }


    /**
     * Metodo que permite inserir uma tarefa
     * @param tarefa os dados da tarefa
     * @return o resultado da insercao
     */
    public long inserirTarefa(Tarefa tarefa){
        return transferenciasDao.inserirRegisto(tarefa);
    }

    public void inserirAtividadesExecutadas(List<AtividadeExecutada> atividades){
        transferenciasDao.inserirAtividadesExecutadas(atividades);
    }

    public void inserirCliente(Cliente cliente){
        transferenciasDao.inserirRegisto(cliente);
    }

    public void inserirAnomalias(List<Anomalia> anomalias){
        transferenciasDao.inserirAnomalias(anomalias);
    }

    public Long inserirAtividadePendente(AtividadePendente atividade){
        return transferenciasDao.inserirAtividadePendente(atividade);
    }

    public void inserirAtividadesPendentes(List<AtividadePendente> atividades){
        transferenciasDao.inserirAtividadesPendentes(atividades);
    }


    /**
     * Metodo que permite inserir o historico de ocorrencias
     * @param ocorrencia os dados da ocorrencia
     * @return o identificador da ocorrencia
     */
    public long inserirOcorrencia(Ocorrencia ocorrencia){
        return transferenciasDao.inserirRegisto(ocorrencia);
    }


    /**
     * Metodo que permite inserir o historico de ocorrencias
     * @param historico uma lista de historicos
     */
    public void inserirHistoricoOcorrencias(List<OcorrenciaHistorico> historico){
        transferenciasDao.inserir(historico);
    }


    /**
     * Metodo que permite inserir as moradas
     * @param registos os dados a inserir
     */
    public void inserirMoradas(List<Morada> registos) {
        transferenciasDao.inserirMoradas(registos);
    }


    /**
     * Metodo que permite inserir os tipos extintores
     * @param registos os dados a inserir
     */
    public void inserirTipoExtintor(List<TipoExtintor> registos) {
        transferenciasDao.inserirTipoExtintor(registos);
    }

    /**
     * Metodo que permite inserir o parque extintor
     * @param registos os dados a inserir
     */
    public void inserirParqueExtintores(List<ParqueExtintor> registos) {
        transferenciasDao.inserirParqueExtintores(registos);
    }


    /**
     * Metodo que permite inserir o quadro pessoal
     * @param registos os dados a inserir
     */
    public void inserirQuadroPessoal(List<Colaborador> registos) {
        transferenciasDao.inserirQuadroPessoal(registos);
    }

    /**
     * Metodo que permite inserir o plano de acao
     * @param registo os dados a inserir
     */
    public void inserirPlanoAcao(PlanoAcao registo) {
        transferenciasDao.inserirPlanoAcao(registo);
    }

    /**
     * Metodo que permite inserir as atividades do plano de acao
     * @param registos os dados a inserir
     */
    public void inserirPlanoAtividades(List<PlanoAcaoAtividade> registos) {
        transferenciasDao.inserirPlanoAtividades(registos);
    }



    /**
     * Metodo que permite eliminar uma tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void eliminarTarefa(int idTarefa) {
        transferenciasDao.removerTarefa(idTarefa);
    }

    /**
     * Metodo que permite eliminar o trabalho
     * @param idUtilizador o identificador do utilizador
     * @param data a data do trabalho
     */
    public void eliminarTrabalho(String idUtilizador, long data) {
        transferenciasDao.removerTrabalho(idUtilizador, data);
    }


    public Long inserirRelatorioAveriguacao(RelatorioAveriguacao relatorio) {
        return transferenciasDao.inserirRelatorioAveriguacao(relatorio);
    }

    public void inserirMedidasAveriguacao(List<MedidaAveriguacao> registos) {
        transferenciasDao.inserirMedidasAveriguacao(registos);
    }

    public void inserirProcessoProdutivo(ProcessoProdutivoResultado processoProdutivo) {
        transferenciasDao.inserirProcessoProdutivo(processoProdutivo);
    }

    public void inserirEquipamentos(List<VerificacaoEquipamentoResultado> equipamentos) {
        transferenciasDao.inserirEquipamentos(equipamentos);
    }

    public long inserirTrabalhadorVulneravel(TrabalhadorVulneravelResultado trabalhadorVulneravel) {
        return transferenciasDao.inserirTrabalhadorVulneravel(trabalhadorVulneravel);
    }

    public void inserirCategoriasProfissionais(List<CategoriaProfissionalResultado> categorias) {
        transferenciasDao.inserirCategoriasProfissionais(categorias);
    }

    public long inserirLevantamento(LevantamentoRiscoResultado levantamento) {
        return transferenciasDao.inserirLevantamento(levantamento);
    }

    public long inserirRisco(RiscoResultado risco) {
        return transferenciasDao.inserirRisco(risco);
    }

    public void inserirMedidas(List<MedidaResultado> medidas) {
        transferenciasDao.inserirMedidas(medidas);
    }

    public List<Tipo> obterNi() {
        return transferenciasDao.obterNi();
    }

    public boolean validarMedida(int idMedida, String tipo) {
        return transferenciasDao.validarMedida(idMedida, tipo);
    }

    public boolean verificarItemChecklist(String idChecklist, String idArea, String idSeccao, String idItem) {
        return transferenciasDao.verificarItemChecklist(Integer.parseInt(idChecklist), Integer.parseInt(idArea), idSeccao, idItem);
    }

    public String obterTipoItemChecklist(String idChecklist, String idArea, String idSeccao, String idItem) {
        return transferenciasDao.obterTipoItemChecklist(Integer.parseInt(idChecklist), Integer.parseInt(idArea), idSeccao, idItem);
    }

    public void inserirQuestoes(List<QuestionarioChecklistResultado> questoes) {
        transferenciasDao.inserirQuestoes(questoes);
    }

    public long inserirQuestao(QuestionarioChecklistResultado questao) {
        return transferenciasDao.inserirQuestao(questao);
    }

    public long inserirAreaChecklist(AreaChecklistResultado area) {
        return transferenciasDao.inserirQuestoes(area);
    }

    public void inserirPropostaPlanoAcao(List<PropostaPlanoAcaoResultado> medidasChecklist) {
        transferenciasDao.inserirPropostaPlanoAcao(medidasChecklist);
    }

    public List<Tipo> obterUts() {
        return transferenciasDao.obterUts();
    }



    public void inserirFormandos(List<FormandoResultado> formandos) {
        transferenciasDao.inserirFormandos(formandos);
    }

}
