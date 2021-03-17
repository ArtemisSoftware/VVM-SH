package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.ConversorUtil;

import java.util.ArrayList;
import java.util.List;

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
    private final TransferenciasDao transferenciasDao;
    private final Gson gson;

    public RedeRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST,
                           @NonNull TransferenciasDao transferenciasDao) {
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
                                sessao.iSessao = iSessao;
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
                                sessao.iSessao = iSessao;
                                return sessao;
                            }
                        }
                );
                break;



            case AppConfig.APP_SA_SHT:

                single = obterTrabalho__Final(idUtilizador);
                break;


            default:
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
    public Single<Sessao> obterTrabalho(String idUtilizador, String data) {

        Single<Sessao> single = null;

        switch (AppConfig.APP_MODO){


            case AppConfig.APP_SA:

                single = apiSA.obterTrabalho(SegurancaAlimentarApi.HEADER, idUtilizador, data)
                        .map(new Function<ISessao, Sessao>() {

                                 @Override
                                 public Sessao apply(ISessao iSessao) throws Exception {
                                     Sessao sessao = new Sessao();
                                     sessao.iSessao = iSessao;
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
                                sessao.iSessao = iSessao;
                                return sessao;
                            }
                        }
                );
                break;



            case AppConfig.APP_SA_SHT:

                single = obterTrabalho__Final(idUtilizador, data);
                break;


            default:
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
}