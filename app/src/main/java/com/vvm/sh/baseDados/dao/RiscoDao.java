package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class RiscoDao implements BaseDao<RiscoResultado> {


    @Query("SELECT *, risco, riscoEspecifico, numeroImagens, " +
            "CASE WHEN nd IS NULL OR nd = '' THEN 0 "+
            "WHEN numeroMedidasExistentes IS NULL AND numeroMedidasRecomendadas IS NULL THEN 0 "+
            "WHEN numeroMedidasExistentes = 0 OR numeroMedidasRecomendadas = 0 THEN 0 "+
            "ELSE 1 END as valido			  "+

            "FROM riscosResultado as risco_res " +
            "LEFT JOIN (SELECT id, descricao as risco FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.RISCOS + "' AND api = :api ) as tp_riscos " +
            "ON risco_res.idRisco = tp_riscos.id " +
            "LEFT JOIN (SELECT id, descricao as riscoEspecifico FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.RISCOS_ESPECIFICOS + "' AND api = :api ) as tp_riscos_especifico " +
            "ON risco_res.idRiscoEspecifico = tp_riscos_especifico.id " +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasExistentes  FROM medidasResultado " +
            "WHERE origem = " + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS +" GROUP BY id) as med_existentes " +
            "ON  risco_res.id = med_existentes.id " +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidasRecomendadas  FROM medidasResultado " +
            "WHERE origem =  " + Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS +" GROUP BY id) as med_recomendadas " +
            "ON  risco_res.id = med_recomendadas.id	"+

            "LEFT JOIN (SELECT id, COUNT(idImagem) as numeroImagens  FROM imagensResultado " +
            "WHERE origem = " + Identificadores.Imagens.IMAGEM_RISCO +" GROUP BY id) as img " +
            "ON  risco_res.id = img.id " +


            "WHERE idLevantamento = :idLevantamento")
    abstract public Observable<List<Risco>> obterRiscos(int idLevantamento, int api);



    @Query("SELECT * FROM riscosResultado WHERE id = :id")
    abstract public Maybe<RiscoResultado> obterRisco(int id);


    @Query("DELETE FROM riscosResultado WHERE idLevantamento = :idLevantamento ")
    abstract public Single<Integer> removerRiscos(int idLevantamento);

    @Query("DELETE FROM imagensResultado WHERE id IN (SELECT id FROM riscosResultado WHERE idLevantamento = :idLevantamento) " +
            "AND origem =" + Identificadores.Imagens.IMAGEM_RISCO + " ")
    abstract public Single<Integer> removerImagemLevantamento(int idLevantamento);


    @Query("DELETE FROM imagensResultado WHERE id IN (" +
            "SELECT id FROM levantamentosRiscoResultado WHERE idAtividade =:idAtividade AND idModelo =:idModelo" +
            ") " +
            "AND origem =" + Identificadores.Imagens.IMAGEM_RISCO + " ")
    abstract public Completable removerImagemModelo(int idAtividade, int idModelo);


//    query = "INSERT INTO riscos_resultado (idLevantamento, idRisco, idRiscoEspecifico, consequencias, origem, idTipoRisco )   ";
//
//    query += "SELECT lvr_res.idLevantamento, rsc_modelo.idRisco, rsc_modelo.idRiscoEspecifico, rsc_modelo.consequencias, " + IdentificadoresIF.ORIGEM_MODELO_LEVANTAMENTO + " as origem, rsc_modelo.id ";
//
//    query += "FROM levantamentosRisco_resultado as lvr_res  ";
//
//    query += "OUTER LEFT JOIN (  ";
//    query += "SELECT id, idRisco, idRiscoEspecifico, consequencias, idLevantamento  FROM riscos_modelo  WHERE activo = 1 ";
//    query += ") ";
//    query += "as rsc_modelo ON lvr_res.idTipoLevantamento = rsc_modelo.idLevantamento  ";
//    query += "WHERE idModelo= ? AND lvr_res.id = ?  ";
//
//    argumentos = new String []{
//        /*AtualizacaoIF.CODIGO_TIPO_ATIVO, */idModelo, idAtividade
//    };

    @Query("INSERT INTO riscosResultado (idLevantamento, idRisco, idRiscoEspecifico, consequencias, origem, idTipoRisco, nd, ne, nc, ni) " +
            "SELECT lvr_res.id as idLevantamento, rsc_modelo.idRisco, rsc_modelo.idRiscoEspecifico, rsc_modelo.consequencias, " + Identificadores.Origens.LEVANTAMENTO_MODELO + " as origem, rsc_modelo.id," +
            " '' as nd, '' as ne, '' as nc, '' as ni " +
            "FROM levantamentosRiscoResultado as lvr_res " +
            "LEFT JOIN ( " +
            "SELECT id, idRisco, idRiscoEspecifico, consequencias, idLevantamento  FROM tiposTemplateAvrRiscos  WHERE ativo = 1" +
            ") " +
            "as rsc_modelo ON lvr_res.idTipoLevantamento = rsc_modelo.idLevantamento " +
            "WHERE idModelo= :idModelo AND lvr_res.idAtividade = :idAtividade  ")
    public abstract Completable inserirModelo(int idAtividade, int idModelo);
}
