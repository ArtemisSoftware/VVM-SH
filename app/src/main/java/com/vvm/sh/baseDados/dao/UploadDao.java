package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.tarefa.modelos.EmailResultado;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class UploadDao {

    //TODO: os dados necessitam do idutilizador + data

    @Query("SELECT res.* " +
            "FROM resultados as res " +
            "LEFT JOIN (SELECT idTarefa, idUtilizador FROM tarefas) as trf ON res.idTarefa = trf.idTarefa " +
            "WHERE idUtilizador = :idUtilizador AND sincronizado = :sincronizado ")
    abstract public Maybe<List<Resultado>> obterResultados(String idUtilizador, boolean sincronizado);


    @Query("SELECT * FROM emailsResultado WHERE idTarefa = :idTarefa")
    abstract public EmailResultado obterEmail(int idTarefa);

}
