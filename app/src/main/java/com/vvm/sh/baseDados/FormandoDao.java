package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class FormandoDao implements BaseDao<FormandoResultado>{


    @Query("SELECT * " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idImagem, imagem as assinatura FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO  + ") as img " +
            "ON frm_res.id = img.idImagem " +
            " WHERE idAtividade = :idAtividade")
    abstract public Flowable<List<Formando>> obterFormandos(int idAtividade);


    @Query("SELECT * " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id as idImagem, imagem as assinatura FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO  + ") as img " +
            "ON frm_res.id = img.idImagem " +
            "WHERE id = :id")
    abstract public Maybe<Formando> obterFormando(int id);

}
