package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.api.modelos.pedido.IContagemTipoMaquina;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.metodos.ConversorUtil;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;

import static com.vvm.sh.util.constantes.Identificadores.CodigosWs.Codigo_101;

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

                        Sessao sessao = new Sessao();
                        sessao.iContagemTipoMaquina = contagemTipoMaquina;

//                        if(iSessaoSA.codigo != Codigo_101){
//                            sessao.iSessaoSA = iSessaoSA;
//                        }
//
//                        if(iSessaoST.codigo != Codigo_101){
//                            sessao.iSessaoSHT = iSessaoST;
//                        }
//
//                        if(iSessaoSA.codigo == Codigo_101 && iSessaoST.codigo == Codigo_101){
//                            throw new RespostaWsInvalidaException(gson.toJson(Codigo_101, Codigo.class));
//                        }

                        return sessao;
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

                        Sessao sessao = new Sessao();
                        sessao.iContagemTipoMaquina = contagemTipoMaquina;

//                        if(iSessaoSA.codigo != Codigo_101){
//                            sessao.iSessaoSA = iSessaoSA;
//                        }
//
//                        if(iSessaoST.codigo != Codigo_101){
//                            sessao.iSessaoSHT = iSessaoST;
//                        }
//
//                        if(iSessaoSA.codigo == Codigo_101 && iSessaoST.codigo == Codigo_101){
//                            throw new RespostaWsInvalidaException(gson.toJson(Codigo_101, Codigo.class));
//                        }

                        return sessao;
                    }
                }
        );

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



}
