package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.modelos.Formando;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class FormandoDao implements BaseDao<FormandoResultado> {


    @Query("SELECT *," +
            "CASE WHEN idRegisto > 0 THEN 1 ELSE 0 END as completo " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idRegisto, imagem as assinatura FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO  + ") as img " +
            "ON frm_res.id = img.idRegisto " +
            " WHERE idAtividade = :idAtividade")
    abstract public Flowable<List<Formando>> obterFormandos(int idAtividade);


    @Query("SELECT *, " +
            "CASE WHEN idRegisto > 0 THEN 1 ELSE 0 END as completo " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idRegisto, imagem as assinatura FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO  + ") as img " +
            "ON frm_res.id = img.idRegisto " +
            "WHERE id = :id")
    abstract public Maybe<Formando> obterFormando(int id);

}
