package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.dao.UtilizadorDao;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

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
