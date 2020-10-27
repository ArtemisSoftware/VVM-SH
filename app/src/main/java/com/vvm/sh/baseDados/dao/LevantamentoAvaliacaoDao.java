package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Completable;

@Dao
abstract public class LevantamentoAvaliacaoDao {

    @Query("INSERT INTO categoriasProfissionaisResultado(idRegisto, origem, idCategoriaProfissional, homens, mulheres) " +
            "SELECT :idLevantamentoNovo  as idRegisto, origem, idCategoriaProfissional, homens, mulheres " +
            "FROM categoriasProfissionaisResultado " +
            "WHERE idRegisto = :idLevantamentoOriginal " +
            "AND origem = " + Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS +" ")
    abstract public void duplicarCategorias(int idLevantamentoOriginal, int idLevantamentoNovo);


    @Query("INSERT INTO riscosResultado (idLevantamento, idRisco, idRiscoEspecifico, consequencias, origem, nd, ne, nc, ni, idTipoRisco) " +
            "SELECT :idLevantamentoNovo as idLevantamento, idRisco, idRiscoEspecifico, consequencias, " + Identificadores.Origens.LEVANTAMENTO_DUPLICACADO + " as origem, nd, ne, nc, ni, idTipoRisco " +
            "FROM riscosResultado WHERE idLevantamento = :idLevantamentoOriginal ")
    abstract public void duplicarRiscos(int idLevantamentoOriginal, int idLevantamentoNovo);


    @Query("SELECT * FROM medidasResultado WHERE id IN (SELECT id FROM  riscosResultado WHERE idLevantamento = :idLevantamentoOriginal) AND origem = :origem")
    abstract public List<MedidaResultado> obterMedidas(int idLevantamentoOriginal, int origem);


    @Query("SELECT * FROM  riscosResultado WHERE idLevantamento = :idLevantamentoNovo")
    abstract public List<RiscoResultado> obterRiscos(int idLevantamentoNovo);



    @Query("INSERT INTO medidasResultado (id,origem,idMedida) " +
            "SELECT :idNovo as id, origem, idMedida FROM  medidasResultado WHERE id =:id AND origem =:origem")
    abstract public void duplicarMedidas(int id, int idNovo, int origem);


    @Query(" INSERT INTO propostaPlanoAcaoResultado (idAtividade, idQuestao, origem, idMedida) " +
            "SELECT :idAtividade as idAtividade,  rsc_res.id as idQuestaoChecklis, " + Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO + " as origem, idMedida " +
            "FROM riscosResultado as rsc_res " +
            "LEFT JOIN (SELECT id, idMedida FROM medidasResultado WHERE origem = :origem) med_res ON rsc_res.id = med_res.id " +
            "WHERE  rsc_res.idLevantamento = :idLevantamentoNovo AND idMedida IS NOT NULL")
    abstract public void duplicarPlanoAcao(int idAtividade, int idLevantamentoNovo, int origem);
}
