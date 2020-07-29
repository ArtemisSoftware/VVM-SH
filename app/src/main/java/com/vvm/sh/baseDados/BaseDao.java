package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;
import androidx.room.Update;

import io.reactivex.Single;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> inserir(T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void inserir(T... entity);



    @Update(onConflict = OnConflictStrategy.IGNORE)
    Single<Integer> atualizar(T entity);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void atualizar(T... entity);


    @Delete
    Single<Integer> remover(T entity);

}
