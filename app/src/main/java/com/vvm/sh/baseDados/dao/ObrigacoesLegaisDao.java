package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class ObrigacoesLegaisDao implements BaseDao<ObrigacaoLegalResultado> {


    @Query("SELECT *, obrigacao_legal_res.id as idRegisto " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id FROM obrigacaoLegalResultado WHERE idTarefa = :idTarefa) as obrigacao_legal_res " +
            "ON tp.id = obrigacao_legal_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.OBRIGACOES_LEGAIS + "' ")
    abstract public Observable<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa, int api);


    @Query("DELETE FROM obrigacaoLegalResultado WHERE idTarefa =:idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);
}
