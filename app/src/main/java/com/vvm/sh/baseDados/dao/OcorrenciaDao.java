package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaBase;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class OcorrenciaDao implements BaseDao<OcorrenciaResultado> {

    @Query("SELECT * FROM ocorrencias WHERE idTarefa = :idTarefa")
    abstract public Single<List<Ocorrencia>> obterOcorrencias(int idTarefa);


    @Transaction
    @Query("SELECT *, 0 as ultimoRegisto " +
            "FROM ocorrenciaResultado as ocr_res " +
            "LEFT JOIN ( " +
            "SELECT id, descricao, codigo, detalhe  " +
            "FROM tipos WHERE  tipo = '" + TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA + "' AND ativo = 1 AND api = :api) as tp " +
            "ON ocr_res.id = tp.id " +
            "WHERE  idTarefa = :idTarefa ")
    abstract public Observable<List<OcorrenciaRegisto>> obterOcorrenciasRegistadas(int idTarefa, int api);


    @Transaction
    @Query("SELECT tp.id as id, descricao, codigo, detalhe, idTarefa, ocr_res.id as idResultado, observacao, fiscalizado,  IFNULL(ultimoRegisto, 0) as ultimoRegisto " + //,  IFNULL(ultimoRegisto, 0) as ultimoRegisto
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idTarefa, id, observacao, fiscalizado FROM ocorrenciaResultado WHERE idTarefa = :idTarefa) as ocr_res ON tp.id = ocr_res.id " +
            "LEFT JOIN (SELECT idPai, COUNT(id) as ultimoRegisto FROM tipos WHERE  tipo = '" + TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA + "' AND ativo = 1 GROUP BY idPai) as tp_seguinte ON tp.id = tp_seguinte.idPai " +
            "WHERE   tipo = '" + TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA + "' AND ativo = 1 AND api = :api AND tp.idPai =:idOcorrencia ")
    abstract public Observable<List<OcorrenciaBase>> obterOcorrencias(int idTarefa, int idOcorrencia, int api);



    @Transaction
    @Query("SELECT tp.id as id, descricao, codigo, detalhe, idTarefa, ocr_res.id as idResultado, observacao, fiscalizado,  IFNULL(ultimoRegisto, 0) as ultimoRegisto " + //,  IFNULL(ultimoRegisto, 0) as ultimoRegisto
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT idTarefa, id, observacao, fiscalizado FROM ocorrenciaResultado WHERE idTarefa = :idTarefa AND id =:idOcorrencia) as ocr_res ON tp.id = ocr_res.id " +
            "LEFT JOIN (SELECT idPai, COUNT(id) as ultimoRegisto FROM tipos WHERE  tipo = '" + TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA + "' AND ativo = 1 GROUP BY idPai) as tp_seguinte ON tp.id = tp_seguinte.idPai " +
            "WHERE  tipo = '" + TiposUtil.MetodosTipos.TIPIFICACAO_OCORRENCIA + "' AND tp.id = :idTipo AND ativo = 1 AND api = :api")
    abstract public Single<OcorrenciaBase> obterOcorrenciaRegistada(int idTarefa, int idOcorrencia, int idTipo, int api);




    @Query("SELECT * FROM ocorrenciasHistorico WHERE idOcorrencia = :idOcorrencia")
    abstract public Single<List<OcorrenciaHistorico>> obterHistorico(int idOcorrencia);


    @Query("DELETE FROM ocorrenciaResultado WHERE idTarefa = :idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);
}
