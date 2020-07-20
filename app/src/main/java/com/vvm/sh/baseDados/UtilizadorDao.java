package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.autenticacao.modelos.Utilizador;

import io.reactivex.Completable;

@Dao
abstract public class UtilizadorDao implements BaseDao<Utilizador>{

    @Query("DELETE FROM utilizadores WHERE id = :idUtilizador")
    abstract public Completable remover(String idUtilizador);

}
