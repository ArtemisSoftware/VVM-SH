package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Utilizador;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
abstract public class UtilizadorDao implements BaseDao<Utilizador> {

    @Query("DELETE FROM utilizadores WHERE id = :idUtilizador")
    abstract public Completable remover(String idUtilizador);


    @Query("SELECT * FROM utilizadores WHERE id = :idUtilizador")
    abstract public Single<Utilizador> obterUtilizador(String idUtilizador);

}
