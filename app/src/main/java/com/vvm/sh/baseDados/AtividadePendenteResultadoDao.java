package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class AtividadePendenteResultadoDao implements BaseDao<AtividadePendenteResultado>{

    @Query("SELECT * FROM atividadesPendentesResultado WHERE id = :id")
    abstract public Maybe<AtividadePendenteResultado> obterAtividade(int id);
}
