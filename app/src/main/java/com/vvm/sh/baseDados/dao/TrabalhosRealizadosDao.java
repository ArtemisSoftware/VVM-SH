package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class TrabalhosRealizadosDao implements BaseDao<TrabalhoRealizadoResultado> {


    @Query("SELECT *, tb_rel_res.id as idRegisto, informacao " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id, informacao FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa) as tb_rel_res " +
            "ON tp.id = tb_rel_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.ATIVIDADES_RELATORIO_VISITA + "' ")
    abstract public Observable<List<TrabalhoRealizado>> obterTrabalhosRealizados(int idTarefa, int api);


    @Query("SELECT *, tb_rel_res.id as idRegisto, informacao " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id, informacao FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa) as tb_rel_res " +
            "ON tp.id = tb_rel_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.ATIVIDADES_RELATORIO_VISITA + "' AND tb_rel_res.id != 0 ")
    abstract public Maybe<List<TrabalhoRealizado>> obterTrabalhosRealizadosRegistados(int idTarefa, int api);




    @Query("DELETE FROM trabalhoRealizadoResultado WHERE idTarefa =:idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);
}
