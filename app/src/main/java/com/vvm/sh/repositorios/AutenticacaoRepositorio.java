package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.IUtilizador;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.UtilizadorException;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class AutenticacaoRepositorio {


    private final SegurancaTrabalhoApi apiST;
    private final SegurancaAlimentarApi apiSA;
    private final UtilizadorDao utilizadorDao;


    public AutenticacaoRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST, UtilizadorDao utilizadorDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.utilizadorDao = utilizadorDao;
    }

    //---------------------
    //OBTER
    //---------------------

    /**
     * Metodo que permite pedir os utilizadores aos web services
     * @return uma listagem de utilizadores
     */
    public Observable<IUtilizadorListagem> obterUtilizadores() {

        return Observable.zip(apiST.obterUtilizadores(apiST.HEADER).toObservable(), apiSA.obterUtilizadores(apiSA.HEADER).toObservable(),
                new BiFunction<IUtilizadorListagem, IUtilizadorListagem, IUtilizadorListagem>() {
                @Override
                public IUtilizadorListagem apply(IUtilizadorListagem iUtilizadorListagem, IUtilizadorListagem iUtilizadorListagem2) throws Exception {

                    IUtilizadorListagem utilizadores = new IUtilizadorListagem();

                    utilizadores.dadosNovos = iUtilizadorListagem.dadosNovos;
                    utilizadores.dadosNovos.addAll(iUtilizadorListagem2.dadosNovos);

                    return utilizadores;
                }
        });
    }


    /**
     * Metodo que permite autenticar um utilizador
     * @param idUtilizador o identificador do utilizador
     * @param palavraChave a palavra chave
     * @return
     */
    public Single<Object> autenticarUtilizador_(String idUtilizador, String palavraChave){

        Single<Object> single = Single.zip(apiST.obterUtilizadores(apiST.HEADER), apiSA.obterUtilizadores(apiSA.HEADER),
                new BiFunction<IUtilizadorListagem, IUtilizadorListagem, IUtilizadorListagem>() {
                    @Override
                    public IUtilizadorListagem apply(IUtilizadorListagem listagemUtilizadoresST, IUtilizadorListagem listagemUtilizadoresSA) throws Exception {

                        IUtilizadorListagem utilizadores = new IUtilizadorListagem();

                        utilizadores.dadosNovos = listagemUtilizadoresST.dadosNovos;
                        utilizadores.dadosNovos.addAll(listagemUtilizadoresSA.dadosNovos);
                        return utilizadores;

                    }
                })
                .toObservable()
                .map(new Function<IUtilizadorListagem, Observable<IUtilizador>>() {
                    @Override
                    public Observable<IUtilizador> apply(IUtilizadorListagem iUtilizadorListagem) throws Exception {
                        return Observable.fromIterable(iUtilizadorListagem.dadosNovos);
                    }
                })
                .flatMap(new Function<Observable<IUtilizador>, ObservableSource<IUtilizador>>() {
                    @Override
                    public ObservableSource<IUtilizador> apply(Observable<IUtilizador> iUtilizadorObservable) throws Exception {
                        return iUtilizadorObservable;
                    }
                })
                .filter(new Predicate<IUtilizador>() {
                    @Override
                    public boolean test(IUtilizador iUtilizador) throws Exception {
                        if (iUtilizador.id.equals(idUtilizador)) {
                            return true;
                        }

                        return false;
                    }
                })
                .toList()
                .map(new Function<List<IUtilizador>, IUtilizador>() {
                    @Override
                    public IUtilizador apply(List<IUtilizador> resposta) throws Exception {

                        IUtilizador resultado = autenticarUtilizador(resposta, palavraChave);

                        if (resultado == null) {
                            throw new UtilizadorException(Sintaxe.Alertas.ERRO_AUTENTICACAO);
                        }

                        return resultado;
                    }
                })
                .flatMap(new Function<IUtilizador, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(IUtilizador iUtilizador) throws Exception {
                        Utilizador utilizador = DownloadMapping.INSTANCE.map((IUtilizador) iUtilizador);
                        return inserir(utilizador);
                    }
                });

        return single;
    }


    /**
     * Metodo que permite verificar as credenciais do utilizador
     * @param resposta os dados da api
     * @param palavraChave a palavra chave do utilizador
     * @return  um utilizador
     */
    private IUtilizador autenticarUtilizador(List<IUtilizador> resposta, String palavraChave){

        IUtilizador utilizador = null;
        String cap = null, email = null;

        for (IUtilizador registo : resposta) {

            String messageDigest = Hasher.Companion.hash(palavraChave, HashType.MD5);

            if(registo.ativo == true & registo.palavraChave.equals(messageDigest) == true){

                if(registo.email != null){
                    email = registo.email;
                }
                if(registo.cap != null){
                    cap = registo.cap;
                }
            }
            else{
                return null;
            }
        }

        if(resposta.size() != 0){
            utilizador = resposta.get(0);
            utilizador.api = 0;
            utilizador.email = email;
            utilizador.cap = cap;
        }


        return utilizador;
    }







    /**
     * Metodo que permite obter o utilizador
     * @param idUtilizador o identificador do utilizador
     * @return os dados do utilizador
     */
    public Single<Utilizador> obterUtilizador(String idUtilizador) {
        return utilizadorDao.obterUtilizador(idUtilizador);
    }



    /**
     * Metodo que permite inserir um utilizador
     * @param utilizador os dados do utilizador
     * @return o resultado da inserção
     */
    public Single<Long> inserir(Utilizador utilizador) {
        return utilizadorDao.inserir(utilizador);
    }


}
