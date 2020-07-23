package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaHistorico;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class OcorrenciaHistoricoDao implements BaseDao<OcorrenciaHistorico> {


    @Insert
    abstract public List<Long> inserir(List<OcorrenciaHistorico> registos);


    @Query("SELECT * FROM ocorrenciasHistorico WHERE idOcorrencia = :idOcorrencia")
    abstract public Flowable<List<OcorrenciaHistorico>> obterHistorico(int idOcorrencia);


}
