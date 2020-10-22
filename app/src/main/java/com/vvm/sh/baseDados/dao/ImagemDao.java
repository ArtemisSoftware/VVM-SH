package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class ImagemDao implements BaseDao<ImagemResultado> {


    @Query("DELETE FROM imagensResultado WHERE id = :id AND origem = :origem")
    abstract public Single<Integer> remover(int id, int origem);


    @Query("SELECT * FROM imagensResultado WHERE id = :id AND origem = :origem")
    abstract public Maybe<ImagemResultado> obterImagem(int id, int origem);

    @Query("SELECT * FROM imagensResultado")
    abstract public Observable<List<ImagemResultado>> obterImagem();

}
