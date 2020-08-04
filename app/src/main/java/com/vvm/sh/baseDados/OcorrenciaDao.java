package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
abstract public class OcorrenciaDao implements BaseDao<Ocorrencia> {





    @Query("SELECT tp.id as id, descricao, codigo, detalhe, observacao, fiscalizado, IFNULL(ultimoRegisto, 0) as ultimoRegisto, ocr_res.id as idResultado " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idPai, COUNT(id) as ultimoRegisto FROM tipos WHERE  tipo = :tipo AND ativo = 1 GROUP BY idPai) as tp_seguinte ON tp.id = tp_seguinte.idPai " +
            "LEFT JOIN (SELECT idTarefa, id, observacao, fiscalizado FROM ocorrenciaResultado WHERE idTarefa = :idTarefa) as ocr_res ON tp.id = ocr_res.id " +
            "WHERE  tipo = :tipo AND tp.idPai =:idOcorrencia AND ativo = 1 ")
    abstract public Flowable<List<OcorrenciaRegisto>> obterOcorrencias(int idTarefa, String tipo, int idOcorrencia);


    @Query("SELECT tp.id as id, descricao, codigo, detalhe, observacao, fiscalizado, IFNULL(ultimoRegisto, 0) as ultimoRegisto, ocr_res.id as idResultado " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idPai, COUNT(id) as ultimoRegisto FROM tipos WHERE  tipo = :tipo GROUP BY idPai AND ativo = 1) as tp_seguinte ON tp.id = tp_seguinte.idPai " +
            "LEFT JOIN (SELECT idTarefa, id, observacao, fiscalizado FROM ocorrenciaResultado WHERE idTarefa = :idTarefa) as ocr_res ON tp.id = ocr_res.id " +
            "WHERE  tipo = :tipo AND tp.id =:id AND ativo = 1 ")
    abstract public Single<OcorrenciaRegisto> obterResultadoOcorrencia(int idTarefa, String tipo, int id);


    @Query("SELECT tp.id as id, descricao, codigo, detalhe, observacao, fiscalizado, 0 as ultimoRegisto, ocr_res.id as idResultado " +
            "FROM ocorrenciaResultado as ocr_res " +
            "LEFT JOIN (SELECT id, descricao, codigo, detalhe  FROM tipos WHERE  tipo = :tipo AND ativo = 1) as tp ON ocr_res.id = tp.id " +
            "WHERE  idTarefa = :idTarefa ")
    abstract public Flowable<List<OcorrenciaRegisto>> obterOcorrenciasRegistadas(int idTarefa, String tipo);


}
