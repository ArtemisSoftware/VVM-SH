package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.ui.cliente.extintores.modelos.ExtintorRegisto;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class ParqueExtintorDao implements BaseDao<ParqueExtintorResultado> {

    @Transaction
    @Query("SELECT *,  endereco || ' ' || cp4 || ' ' || localidade AS morada, extintor " +
            "FROM parqueExtintores as prq_ext " +
            "LEFT JOIN (SELECT idTarefa, id, endereco, cp4, localidade  FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA_EXTINTOR + ") as mrd ON prq_ext.idTarefa = mrd.idTarefa AND prq_ext.idMorada = mrd.id " +
            "LEFT JOIN (SELECT idTarefa, id, descricao as extintor  FROM tiposExtintor) as ext ON prq_ext.idTarefa = ext.idTarefa AND prq_ext.idExtintor = ext.id " +
            "WHERE  idTarefa = :idTarefa ")
    abstract public Flowable<List<ExtintorRegisto>> obterExtintores(int idTarefa);
}
