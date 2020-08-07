package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.upload.modelos.Pendencia;
import com.vvm.sh.ui.upload.modelos.Upload;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class UploadDao implements BaseDao<Resultado> {

    //TODO: obterResultados -> os dados necessitam do idutilizador + data

    @Query("SELECT res.* " +
            "FROM resultados as res " +
            "LEFT JOIN (SELECT idTarefa, idUtilizador FROM tarefas) as trf ON res.idTarefa = trf.idTarefa " +
            "WHERE idUtilizador = :idUtilizador AND sincronizado = :sincronizado ")
    abstract public Maybe<List<Resultado>> obterResultados(String idUtilizador, boolean sincronizado);


    //@Transaction
//    @Query("SELECT * " +
//            "FROM tarefas as trf " +
//            "LEFT JOIN (SELECT idTarefa, sincronizado FROM resultados) as res ON trf.idTarefa = res.idTarefa " +
//            "WHERE idUtilizador = :idUtilizador AND sincronizado = :sincronizado ")

    @Transaction
    @Query("SELECT * " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct FROM resultados WHERE sincronizado = :sincronizado GROUP BY idTarefa) as res " +
            "ON trf.idTarefa = res.idTarefa " +
            "WHERE ct > 0 AND idUtilizador = :idUtilizador")
    abstract public Maybe<List<Upload>> obterUploads(String idUtilizador, boolean sincronizado);

//SELECT idTarefa, atp.id as id, COUNT(*) as ct_atp, IFNULL(ct_atp_res, 0) as ct_atp_res FROM atividadesPendentes as atp LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado) as atp_res ON atp.id = atp_res.id GROUP BY idTarefa


    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN(" +
            "SELECT idTarefa, atp.id as id, COUNT(*) as ct_atp, IFNULL(ct_atp_res, 0) as ct_atp_res FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado) as atp_res ON atp.id = atp_res.id GROUP BY idTarefa " +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador) AND ct_atp_res = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador);


    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN(" +
            "SELECT idTarefa, atp.id as id, COUNT(*) as ct_atp, IFNULL(ct_atp_res, 0) as ct_atp_res FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado) as atp_res ON atp.id = atp_res.id GROUP BY idTarefa " +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador AND data =:data) AND ct_atp_res = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador, long data);

    //-------------------
    //RESULTADOS
    //-------------------



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
