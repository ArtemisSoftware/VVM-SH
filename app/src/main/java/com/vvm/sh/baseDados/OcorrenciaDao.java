package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class OcorrenciaDao implements BaseDao<Ocorrencia> {


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);


    @Query("SELECT * FROM ocorrencias WHERE idTarefa = :idTarefa")
    abstract public Flowable<List<Ocorrencia>> obterOcorrencias(int idTarefa);


    @Query("SELECT tp.id as id, descricao, codigo, detalhe " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idTarefa, id FROM ocorrenciaResultado WHERE idTarefa = :idTarefa) as ocr_res ON tp.id = ocr_res.id " +
            "WHERE  tipo = :tipo AND idPai =:idOcorrencia ")
    abstract public Flowable<List<OcorrenciaRegisto>> obterOcorrencias(int idTarefa, String tipo, int idOcorrencia);
}
