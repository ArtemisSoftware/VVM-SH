package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class FormandoDao implements BaseDao<FormandoResultado>{


    @Query("SELECT * FROM formandosResultado WHERE idAtividade = :idAtividade")
    abstract public Flowable<List<Formando>> obterFormandos(int idAtividade);


    @Query("SELECT * FROM formandosResultado WHERE id = :id")
    abstract public Maybe<Formando> obterFormando(int id);
}
