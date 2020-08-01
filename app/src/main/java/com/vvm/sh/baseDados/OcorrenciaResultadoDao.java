package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaResultado;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
abstract public class OcorrenciaResultadoDao implements BaseDao<OcorrenciaResultado>{


    @Query("SELECT * FROM ocorrenciaResultado WHERE idTarefa = :idTarefa AND id = :id")
    abstract public Maybe<OcorrenciaResultado> obterResultadoOcorrencia(int idTarefa, int id);


    @Query("DELETE FROM ocorrenciaResultado WHERE idTarefa = :idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);

}
