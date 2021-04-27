package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.baseDados.dao.TransferenciasDao_v2;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.ui.transferencias.modelos.AtualizacaoTipos;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

import static com.vvm.sh.util.constantes.Identificadores.CodigosWs.Codigo_101;
import static com.vvm.sh.util.constantes.Identificadores.CodigosWs.ID_101;


public class RedeRepositorio {

    private final SegurancaAlimentarApi apiSA;
    private final SegurancaTrabalhoApi apiST;
    private final TransferenciasDao_v2 transferenciasDao;
    private final Gson gson;

    public RedeRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST,
                           @NonNull TransferenciasDao_v2 transferenciasDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.transferenciasDao = transferenciasDao;
        this.gson = new Gson();
    }

    //---------------------
    //Download
    //---------------------


    public Single<Sessao> obterTrabalho(String idUtilizador) {

        Single<Sessao> single = null;

        switch (AppConfig.APP_MODO){


            case AppConfig.APP_SA:

                single = apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador)
                         .map(new Function<ISessao, Sessao>() {

                            @Override
                            public Sessao apply(ISessao iSessao) throws Exception {
                                Sessao sessao = new Sessao();
                                sessao.iSessaoSA = iSessao;
                                return sessao;
                            }
                         }
                );
                break;



            case AppConfig.APP_SHT:

                single = Single.zip(
                        apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador),
                        apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER_EQUIPAMENTO, idUtilizador),
                        new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                            @Override
                            public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                                Sessao sessao = new Sessao();
                                sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                                sessao.iSessaoSHT = iSessao;
                                return sessao;
                            }
                        }
                );
                break;



//            case AppConfig.APP_SA_SHT:
//                break;


            default:
                single = obterTrabalho__Final(idUtilizador);
                break;


        }

        return single;
    }






    /**
     * Metodo que permite obter o trabalho de um dia especifico para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param data da data do dia (YYYY-mm-dd)
     * @return o trabalho do dia
     */
    public Single<Sessao> obterTrabalho(String idUtilizador, String data, int api) {

        Single<Sessao> single = null;

        switch (api){


            case AppConfig.APP_SA:

                single = apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data)
                        .map(new Function<ISessao, Sessao>() {

                                 @Override
                                 public Sessao apply(ISessao iSessao) throws Exception {
                                     Sessao sessao = new Sessao();
                                     sessao.iSessaoSA = iSessao;
                                     return sessao;
                                 }
                             }
                        );
                break;



            case AppConfig.APP_SHT:

                single = Single.zip(
                        apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador, data),
                        apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER_EQUIPAMENTO, idUtilizador),
                        new BiFunction<ISessao, IContagemTipoMaquina, Sessao>() {
                            @Override
                            public Sessao apply(ISessao iSessao, IContagemTipoMaquina iContagemTipoMaquina) throws Exception {

                                Sessao sessao = new Sessao();
                                sessao.iContagemTipoMaquina = iContagemTipoMaquina;
                                sessao.iSessaoSHT = iSessao;
                                return sessao;
                            }
                        }
                );
                break;



//            case AppConfig.APP_SA_SHT:
//                break;


            default:
                single = obterTrabalho__Final(idUtilizador, data);
                break;

        }

        return single;
    }







    /**
     * Metodo que permite obter o trabalho do dia para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @return o trabalho
     */
    public Single<Sessao> obterTrabalho__Final(String idUtilizador) {

        return Single.zip(
                apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador),
                apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador),
                apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER_EQUIPAMENTO, idUtilizador),
                new Function3<ISessao, ISessao, IContagemTipoMaquina, Sessao>() {
                    @Override
                    public Sessao apply(ISessao iSessaoSA, ISessao iSessaoST, IContagemTipoMaquina contagemTipoMaquina) throws Exception {
                        return obterTrabalho(iSessaoSA, iSessaoST, contagemTipoMaquina);
                    }
                }
        );

    }





    /**
     * Metodo que permite obter o trabalho de um dia especifico para um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param data da data do dia (YYYY-mm-dd)
     * @return o trabalho do dia
     */
    public Single<Sessao> obterTrabalho__Final(String idUtilizador, String data) {

        return Single.zip(
                apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data),
                apiST.obterTrabalho(SegurancaTrabalhoApi.HEADER, idUtilizador, data),
                apiST.obterContagemTiposMaquinas(SegurancaTrabalhoApi.HEADER_EQUIPAMENTO, idUtilizador),
                new Function3<ISessao, ISessao, IContagemTipoMaquina, Sessao>() {
                    @Override
                    public Sessao apply(ISessao iSessaoSA, ISessao iSessaoST, IContagemTipoMaquina contagemTipoMaquina) throws Exception {
                        return obterTrabalho(iSessaoSA, iSessaoST, contagemTipoMaquina);
                    }
                }
        );

    }




    //---------------------
    //Upload - dados
    //---------------------


    private Single<Codigo> uploadDados(DadosUpload dadosUpload){

        return submeterDados(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return sincronizar(dadosUpload.uploads);
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


    private Single<Codigo> uploadDados(DadosUpload dadosUpload, List<Upload> uploads){

        return submeterDados(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return sincronizar(uploads);
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


    private Single<Codigo> submeterDados(DadosUpload dadosUpload) {

        dadosUpload.formatarDados();

        if(dadosUpload.api == Identificadores.App.APP_SA) {
            return apiSA.submeterDados(SegurancaAlimentarApi.HEADER,dadosUpload.obterDados(), dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.messageDigest);
        }
        else{
            return apiST.submeterDados(SegurancaTrabalhoApi.HEADER, dadosUpload.obterDados(), dadosUpload.idUtilizador, dadosUpload.idUpload, dadosUpload.messageDigest);
        }
    }



    //---------------------
    //Upload - imagens
    //---------------------


    private Single<Codigo> uploadImagens(DadosUpload dadosUpload){

        dadosUpload.formatarImagens();

        List<Single<Codigo>> observables = new ArrayList<>();

        for (DadosUpload.DadosImagem dadosImagem: dadosUpload.dadosImagems) {
            observables.add(submeterImagens(dadosUpload, dadosImagem));
        }

        Single<Codigo> observable = Single.zip(observables, new Function<Object[], Codigo>() {
            @Override
            public Codigo apply(Object[] codigos) throws Exception {

                if(validarResultadoUpload(codigos) == true){
                    return Identificadores.CodigosWs.Codigo_100;
                }
                else{
                    throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                }
            }
        });

        return observable;

//        List<Observable<Codigo>> observables = new ArrayList<>();
//
//        for (DadosUpload.DadosImagem dadosImagem: dadosUpload.dadosImagems) {
//            observables.add(uploadImagens(dadosUpload, dadosImagem).toObservable());
//        }
//
//        Observable<Codigo> observable = Observable.zip(observables, new Function<Object[], Codigo>() {
//            @Override
//            public Codigo apply(Object[] codigos) throws Exception {
//
//                if(validarResultadoUpload(codigos) == true){
//                    return Identificadores.CodigosWs.Codigo_100;
//                }
//                else{
//                    throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
//                }
//            }
//        });
//
//        return observable;
    }


    /**
     * Metodo que permite submeter as imagens para o web service
     * @return um codigo com o resultado da submissao
     */
    private Single<Codigo> submeterImagens(DadosUpload dadosUpload, DadosUpload.DadosImagem dadosImagem) {

        if(dadosUpload.api == Identificadores.App.APP_SA) {
            return apiSA.submeterImagens(SegurancaAlimentarApi.HEADER, dadosImagem.blocoImagem, dadosUpload.idUtilizador, dadosUpload.idUpload, dadosImagem.numeroFicheiro + "", dadosImagem.messageDigest);
        }
        else{
            return apiST.submeterImagens(SegurancaTrabalhoApi.HEADER, dadosImagem.blocoImagem, dadosUpload.idUtilizador, dadosUpload.idUpload, dadosImagem.numeroFicheiro + "", dadosImagem.messageDigest);
        }
    }


    //---------------------
    //Upload - dados + imagens
    //---------------------


    private Single<Codigo> uploadSA_(DadosUpload dadosUpload){

        return uploadImagens(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return submeterDados(dadosUpload);
                    }
                })
                .map(new Function<Object, Codigo>() {
                    @Override
                    public Codigo apply(Object o) throws Exception {
                        if(((Codigo) o).codigo == Identificadores.CodigosWs.ID_100) {
                            return Identificadores.CodigosWs.Codigo_100;
                        }
                        else{
                            throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                        }
                    }
                })
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return sincronizar(dadosUpload.uploads);
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

    private Single<Codigo> uploadST_(DadosUpload dadosUpload){

        return uploadImagens(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return submeterDados(dadosUpload);
                    }
                })
                .map(new Function<Object, Codigo>() {
                    @Override
                    public Codigo apply(Object o) throws Exception {
                        if(((Codigo) o).codigo == Identificadores.CodigosWs.ID_100) {
                            return Identificadores.CodigosWs.Codigo_100;
                        }
                        else{
                            throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                        }
                    }
                })
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return sincronizar(dadosUpload.uploads);
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



//        return submeterDados(dadosUpload)
//                .flatMap(new Function<Codigo, SingleSource<?>>() {
//                    @Override
//                    public SingleSource<?> apply(Codigo codigo) throws Exception {
//
//                        if(validarResultadoUpload(codigo) == true){
//                            return uploadImagens(dadosUpload);
//                        }
//                        else{
//                            throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
//                        }
//                    }
//                })
//                .flatMap(new Function<Object, SingleSource<?>>() {
//                    @Override
//                    public SingleSource<?> apply(Object o) throws Exception {
//                        return sincronizar(dadosUpload.uploads);
//                    }
//                })
//                .map(new Function<Object, Codigo>() {
//                    @Override
//                    public Codigo apply(Object o) throws Exception {
//                        if(((int) o) > 0) {
//                            return Identificadores.CodigosWs.Codigo_100;
//                        }
//                        else{
//                            return Identificadores.CodigosWs.Codigo_600;
//                        }
//                    }
//                });

    }





    private Single<Codigo> uploadSA(DadosUpload dadosUpload, List<Upload> uploads){

        return uploadImagens(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return submeterDados(dadosUpload);
                    }
                })
                .map(new Function<Object, Codigo>() {
                    @Override
                    public Codigo apply(Object o) throws Exception {
                        if(((Codigo) o).codigo == Identificadores.CodigosWs.ID_100) {
                            return Identificadores.CodigosWs.Codigo_100;
                        }
                        else{
                            throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                        }
                    }
                })
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {
                        return sincronizar(uploads);
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


    private Single<Codigo> uploadST(DadosUpload dadosUpload, List<Upload> uploads){

        return submeterDados(dadosUpload)
                .flatMap(new Function<Codigo, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Codigo codigo) throws Exception {

                        if(validarResultadoUpload(codigo) == true){
                            return uploadImagens(dadosUpload);
                        }
                        else{
                            throw new RespostaWsInvalidaException(Identificadores.CodigosWs.Codigo_600);
                        }
                    }
                })
                .flatMap(new Function<Object, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Object o) throws Exception {
                        return sincronizar(uploads);
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



    public Single<Codigo> upload(DadosUpload dadosUpload, List<Upload> uploads) {

        if (dadosUpload.numeroFicheirosImagens != 0) {

            if(dadosUpload.api == Identificadores.App.APP_SA) {
                return uploadSA(dadosUpload, uploads);
            }
            else{
                return uploadST(dadosUpload, uploads);
            }
        }
        else{
            return uploadDados(dadosUpload, uploads);
        }
    }

    //---------------------
    //Upload
    //---------------------



//    public Single<Codigo> upload(DadosUpload dadosUploadSA, DadosUpload dadosUploadSH){
//
//        if(dadosUploadSA.dados.size() > 0 && dadosUploadSH.dados.size() == 0){
//            return uploadSA(dadosUploadSA);
//        }
//        else if(dadosUploadSH.dados.size() > 0 && dadosUploadSA.dados.size() == 0){
//            return uploadSH(dadosUploadSH);
//        }
//        else{
//
//            return null
//
//        }
//    }


    public Single<Codigo> uploadSA(DadosUpload dadosUpload){

        if(dadosUpload.numeroFicheirosImagens == 0){
            return uploadDados(dadosUpload);
        }
        else{
            return uploadSA_(dadosUpload);
        }
    }


    public Single<Codigo> uploadSH(DadosUpload dadosUpload){

        if(dadosUpload.numeroFicheirosImagens == 0){
            return uploadDados(dadosUpload);
        }
        else{
            return uploadST_(dadosUpload);
        }
    }





    /**
     * Metodo que permite fazer o upload do pdf do certificado de tratamento
     * @param idTarefa
     * @param caminhoPdf
     * @return
     */
    public Single<Codigo> uploadCertificadoTratamento(int idTarefa, String caminhoPdf){

        return transferenciasDao.obterTarefa(idTarefa)
                .flatMap(new Function<Tarefa, SingleSource<Codigo>>() {
                    @Override
                    public SingleSource<Codigo> apply(Tarefa tarefa) throws Exception {
                        return apiSA.submeterCertificadoTratamento(SegurancaAlimentarApi.HEADER,  ConversorUtil.convertPdf__StringBase64(caminhoPdf), tarefa.ordem, tarefa.prefixoCt);
                    }
                });

    }


    /**
     * Metodo que permite fazer o upload do pdf da informacao sst
     * @param idTarefa
     * @param caminhoPdf
     * @return
     */
    public Single<Codigo> uploadInformacaoSst(int idTarefa, String caminhoPdf){

        return transferenciasDao.obterTarefa(idTarefa)
                .flatMap(new Function<Tarefa, SingleSource<Codigo>>() {
                    @Override
                    public SingleSource<Codigo> apply(Tarefa tarefa) throws Exception {
                        return apiST.submeterInfoSST(SegurancaTrabalhoApi.HEADER,  ConversorUtil.convertPdf__StringBase64(caminhoPdf), tarefa.ordem, tarefa.prefixoCt);
                    }
                });
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


    private Sessao obterTrabalho(ISessao iSessaoSA, ISessao iSessaoST, IContagemTipoMaquina contagemTipoMaquina) throws RespostaWsInvalidaException {

        Sessao sessao = new Sessao();
        sessao.iContagemTipoMaquina = contagemTipoMaquina;

        if(iSessaoSA.codigo != ID_101){
            sessao.iSessaoSA = iSessaoSA;
        }

        if(iSessaoST.codigo != ID_101){
            sessao.iSessaoSHT = iSessaoST;
        }

        if(iSessaoSA.codigo == ID_101 && iSessaoST.codigo == ID_101){
            throw new RespostaWsInvalidaException(gson.toJson(Codigo_101, Codigo.class));
        }

        return sessao;
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




    //---------------------------
    //Tipos
    //---------------------------


    public Single<List<Object>> obterTipos(){
        return obterSingleSource(obterInvocacoesTipos(new LinkedList(Arrays.asList(TiposUtil.MetodosTipos.Tipos.TIPOS))));
    }



    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param resumo os dados da atualizacao do tipo
     * @return os dados de um tipo
     */
    public Single<List<ITipoListagem>> obterTipo(ResumoTipo resumo) throws TipoInexistenteException {

        TiposUtil.MetodoApi metodo = TiposUtil.obterMetodos(resumo.descricao);
        List<SingleSource> tipos = obterInvocacoesTipos(metodo);

        SingleSource[] source = new SingleSource[tipos.size()];

        for(int index = 0; index < tipos.size(); ++index){
            source[index] = tipos.get(index);
        }

        return Single.concatArray(source).toList();
    }


    //---------------------------
    //Checklist
    //---------------------------


    /**
     * Metodo que permite obter as checklists do web service
     * @return
     */
    public Single<List<Object>> obterChecklists() {

        List<SingleSource> pedidos = new ArrayList<>();

        for (int id: TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__) {
            pedidos.add(apiST.obterChecklist(SegurancaTrabalhoApi.HEADER_TIPO, id +""));
        }

        return obterSingleSource(pedidos);
    }


    public Single<ITipoChecklist> obterChecklist(ResumoChecklist resumo)  {
        return apiST.obterChecklist(SegurancaTrabalhoApi.HEADER_TIPO, resumo.checkList.id + "");
    }

    //---------------------------
    //Templates avr
    //---------------------------


    public Single<List<Object>> obterTemplateAvr()  {

        SingleSource[] source = new SingleSource[2];

        source[0] = apiST.obterTipoTemplatesAVR_Levantamentos(SegurancaTrabalhoApi.HEADER_TIPO);
        source[1] = apiST.obterTipoTemplatesAVR_Riscos(SegurancaTrabalhoApi.HEADER_TIPO);

        return Single.concatArray(source).toList();

    }


    //---------------------------
    //Atividades planeaveis
    //---------------------------


    public Single<List<Object>> obterAtividadesPlaneaveis() {

        SingleSource[] source = new SingleSource[1];
        source[0] = apiST.obterTipoAtividadesPlaneaveis(SegurancaTrabalhoApi.HEADER_TIPO);
        return Single.concatArray(source).toList();
    }




    private Single<List<Object>> obterSingleSource(List<SingleSource> lista){

        SingleSource[] source = new SingleSource[lista.size()];

        for(int index = 0; index < lista.size(); ++index){
            source[index] = lista.get(index);
        }

        return Single.concatArray(source).toList();
    }

























































    public Single<List<Object>> obterDados(AtualizacaoTipos atualizacoes) {

        List<SingleSource> tipos = new ArrayList<>();

        tipos.addAll(obterInvocacoesTipos(atualizacoes.atualizacoes));



        for(TiposUtil.MetodoApi metodo : atualizacoes.atualizacoes){

            //--tipos.addAll(obterInvocacoesTipos(metodo));



            if(metodo.tipo == Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS) {

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.AtividadesPlaneaveis.ATIVIDADES_PLANEAVEIS) == true){
                    tipos.add(apiST.obterTipoAtividadesPlaneaveis(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSHT));
                }
            }

            if(metodo.tipo == Identificadores.Atualizacoes.TEMPLATE) {

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS) == true){
                    tipos.add(apiST.obterTipoTemplatesAVR_Levantamentos(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSHT));
                }

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_RISCOS) == true){
                    tipos.add(apiST.obterTipoTemplatesAVR_Riscos(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSHT));
                }

            }
        }

        for(TipoNovo tipo : atualizacoes.tiposNovos){

            Map<String, String> cabecalho = SegurancaTrabalhoApi.HEADER_EQUIPAMENTO;
            cabecalho.put(Sintaxe.API.ID_EQUIPAMENTO_PROVISORIO, tipo.idProvisorio + "");

            tipos.add(apiST.obterEstadoEquipamento(cabecalho, tipo.descricao));
        }

        for (Integer id : atualizacoes.obterIdChecklists()) {
            tipos.add(apiST.obterChecklist(SegurancaTrabalhoApi.HEADER_TIPO, id + ""));
        }

        return obterSingleSource(tipos);
    }




    private List<SingleSource> obterInvocacoesTipos(TiposUtil.MetodoApi metodo){

        List<SingleSource> tipos = new ArrayList<>();

        if(metodo.tipo == Identificadores.Atualizacoes.TIPO) {

            if (metodo.sa != null) {
                tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporalSA));
            }

            if (metodo.sht != null) {
                tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporalSHT));
            }
        }

        return tipos;
    }



    private List<SingleSource> obterInvocacoesTipos(List<TiposUtil.MetodoApi> atualizacoes){

        List<SingleSource> tipos = new ArrayList<>();


        for(TiposUtil.MetodoApi metodo : atualizacoes){

            if(metodo.tipo == Identificadores.Atualizacoes.TIPO) {

                if (metodo.sa != null) {
                    tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporalSA));
                }

                if (metodo.sht != null) {
                    tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporalSHT));
                }
            }
        }

        return tipos;
    }




}
