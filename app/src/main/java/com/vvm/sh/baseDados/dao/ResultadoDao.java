package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Transaction;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Resultado;

@Dao
abstract public class ResultadoDao implements BaseDao<Resultado> {


    @Insert
    abstract public long inserirRegisto(Resultado registo);


    @Update
    abstract public void atualizarRegisto(Resultado registo);


    @Transaction
    public void upsert(Resultado registo) {

        long id = inserirRegisto(registo);

        if (id == -1) {
            atualizarRegisto(registo);
        }
    }
}
