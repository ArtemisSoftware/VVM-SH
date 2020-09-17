package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.ui.parqueExtintores.modelos.ExtintorRegisto;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
abstract public class ParqueExtintorDao implements BaseDao<ParqueExtintorResultado> {

    @Transaction
    @Query("SELECT *,  endereco || ' ' || cp4 || ' ' || localidade AS morada, extintor " +
            "FROM parqueExtintores as prq_ext " +
            "LEFT JOIN (SELECT idTarefa, id, endereco, cp4, localidade  FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA_EXTINTOR + ") as mrd " +
            "ON prq_ext.idTarefa = mrd.idTarefa AND prq_ext.idMorada = mrd.id " +
            "LEFT JOIN (SELECT idTarefa, id, descricao as extintor  FROM tiposExtintor) as ext " +
            "ON prq_ext.idTarefa = ext.idTarefa AND prq_ext.idExtintor = ext.id " +
            "WHERE  prq_ext.idTarefa = :idTarefa ")
    abstract public Flowable<List<ExtintorRegisto>> obterExtintores(int idTarefa);


    @Transaction
    @Query("SELECT IFNULL(valido ,0) as ct_valido " +
            "FROM parqueExtintores as prq_ext " +
            "LEFT JOIN (SELECT id, valido FROM parqueExtintoresResultado) as prq_ext_res ON prq_ext.id = prq_ext_res.id " +
            "WHERE  idTarefa = :idTarefa AND valido = 1 " +
            "GROUP BY idTarefa ")
    abstract public Observable<Integer> obterNumeroValidados(int idTarefa);



/*
    @Transaction
    @Query("INSERT INTO parqueExtintoresResultado (id, valido)    " +
            "SELECT pq_ext.id, 1 as valido   " +
            "FROM parqueExtintores as pq_ext    " +
            "LEFT JOIN (SELECT id FROM  parqueExtintoresResultado) as pq_ext_res ON  pq_ext.id = pq_ext_res.id   " +
            "WHERE  pq_ext_res.id IS NULL AND idTarefa = :idTarefa  ")
    abstract public Observable<List<Long>> inserirValicao(int idTarefa);


    @Transaction
    @Query("UPDATE parqueExtintoresResultado SET valido = 1 " +
            "WHERE id IN ( " +
            "SELECT pq_ext_res.id FROM parqueExtintoresResultado as pq_ext_res " +
            "LEFT JOIN (SELECT id FROM  parqueExtintores WHERE idTarefa = :idTarefa) as pq_ext ON pq_ext_res.id = pq_ext.id" +
            ")")
    abstract public Observable<List<Integer>> atualizarValidacao(int idTarefa);

*/



}
