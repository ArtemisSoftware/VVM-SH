package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.UtilizadorDao;

import io.reactivex.Single;

public class AutenticacaoRepositorio {


    private final SegurancaAlimentarApi api;
    private final UtilizadorDao utilizadorDao;


    public AutenticacaoRepositorio(@NonNull SegurancaAlimentarApi api, UtilizadorDao utilizadorDao) {
        this.api = api;
        this.utilizadorDao = utilizadorDao;
    }


    /**
     * Metodo que permite obter os utilizadores existentes no ws
     * @return os utilizadores
     */
    public Single<UtilizadorResposta> obterUtilizadores() {
        return api.obterUtilizadores();
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
