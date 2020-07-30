package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;

import java.util.List;

import io.reactivex.Flowable;

@Dao
abstract public class FormandoDao /*implements BaseDao<Formando>*/{

//
//    @Query("SELECT * FROM formandosResultado WHERE idAtividade = :idAtividade")
//    abstract public Flowable<List<Formando>> obterFormandos(int idAtividade);
}
