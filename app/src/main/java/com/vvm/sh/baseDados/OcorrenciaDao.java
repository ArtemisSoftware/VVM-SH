package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class OcorrenciaDao implements BaseDao<Ocorrencia> {


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);


    @Query("SELECT * FROM ocorrencias WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<Ocorrencia>> obterOcorrencias(int idTarefa);


}
