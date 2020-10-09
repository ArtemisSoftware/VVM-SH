package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Resultado;

import io.reactivex.Single;

@Dao
abstract public class ResultadoDao implements BaseDao<Resultado> {


    @Insert
    abstract public long inserirRegisto(Resultado registo);


    @Update
    abstract public void atualizarRegisto(Resultado registo);


    @Query("DELETE FROM atividadesPendentesResultado WHERE id = :idAtividade")
    abstract public int remover(int idAtividade);



    @Transaction
    public void upsert(Resultado registo) {

        long id = inserirRegisto(registo);

        if (id == -1) {
            atualizarRegisto(registo);
        }
    }


    @Transaction
    public void upsert(Resultado registo, int idAtividade) {

        remover(registo);
        remover(idAtividade);
    }
}
