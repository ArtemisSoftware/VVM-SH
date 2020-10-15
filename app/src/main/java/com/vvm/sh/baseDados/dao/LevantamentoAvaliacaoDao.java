package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.util.constantes.Identificadores;

import io.reactivex.Completable;

@Dao
abstract public class LevantamentoAvaliacaoDao {

    @Query("INSERT INTO categoriasProfissionaisResultado(id, origem, idCategoriaProfissional, homens, mulheres) " +
            "SELECT :idLevantamentoNovo  as id, origem, idCategoriaProfissional, homens, mulheres " +
            "FROM categoriasProfissionaisResultado WHERE id = :idLevantamentoOriginal ")
    abstract public void duplicarCategorias(int idLevantamentoOriginal, int idLevantamentoNovo);


    @Query("INSERT INTO riscosResultado (idLevantamento, idRisco, idRiscoEspecifico, consequencias, origem, nd, ne, nc) " +
            "SELECT :idLevantamentoNovo as idLevantamento, idRisco, idRiscoEspecifico, consequencias, " + Identificadores.Origens.LEVANTAMENTO_DUPLICACADO + " as origem, nd, ne, nc " +
            "FROM riscosResultado WHERE idLevantamento = :idLevantamentoOriginal ")
    abstract public void duplicarRiscos(int idLevantamentoOriginal, int idLevantamentoNovo);
}
