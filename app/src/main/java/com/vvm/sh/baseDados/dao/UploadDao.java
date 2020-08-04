package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class UploadDao {

    //TODO: obterResultados -> os dados necessitam do idutilizador + data

    @Query("SELECT res.* " +
            "FROM resultados as res " +
            "LEFT JOIN (SELECT idTarefa, idUtilizador FROM tarefas) as trf ON res.idTarefa = trf.idTarefa " +
            "WHERE idUtilizador = :idUtilizador AND sincronizado = :sincronizado ")
    abstract public Maybe<List<Resultado>> obterResultados(String idUtilizador, boolean sincronizado);


    @Query("SELECT * FROM emailsResultado WHERE idTarefa = :idTarefa")
    abstract public EmailResultado obterEmail(int idTarefa);


    @Query("SELECT * FROM anomaliasResultado WHERE idTarefa = :idTarefa")
    abstract public List<AnomaliaResultado> obterAnomalias(int idTarefa);


    @Query("SELECT * FROM crossSellingResultado WHERE idTarefa = :idTarefa")
    abstract public List<CrossSellingResultado> obterCrossSelling(int idTarefa);


    @Query("SELECT * FROM ocorrenciaResultado WHERE idTarefa = :idTarefa")
    abstract public List<OcorrenciaResultado> obterOcorrencias(int idTarefa);


    @Transaction
    @Query("SELECT * " +
            "FROM atividadesPendentesResultado as at_pend_res " +
            "LEFT JOIN (SELECT idTarefa, id as idAtividade FROM atividadesPendentes) as at_pend ON at_pend_res.id = at_pend.idAtividade " +
            "WHERE at_pend.idTarefa = :idTarefa")
    abstract public List<AtividadePendenteResultado_> obterAtividadesPendentes(int idTarefa);


    @Query("SELECT * FROM acoesFormacaoResultado WHERE idAtividade = :idAtividade")
    abstract public AcaoFormacaoResultado obterAcaoFormacao(int idAtividade);

    @Query("SELECT * FROM formandosResultado WHERE idAtividade = :idAtividade AND selecionado = 1")
    abstract public List<FormandoResultado> obterFormandos(int idAtividade);


    @Query("SELECT * FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public Tarefa obterTarefa(int idTarefa);

}
