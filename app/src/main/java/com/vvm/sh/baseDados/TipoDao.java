package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class TipoDao implements BaseDao<Atualizacao>{


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

/*
    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Maybe<List<Colecao>> obterTipos();
*/

    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Flowable<List<Colecao>> obterTipos();
}
