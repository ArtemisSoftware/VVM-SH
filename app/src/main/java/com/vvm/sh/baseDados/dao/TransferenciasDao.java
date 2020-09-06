package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.FormandoResultado_;
import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class TransferenciasDao implements BaseDao<Resultado> {




    @Transaction
    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN (" +
            "SELECT idTarefa, IFNULL(SUM(ct_atp_res), 0) as ct_realizado FROM (" +
            "SELECT * FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado GROUP BY id) as atp_res ON atp.id = atp_res.id" +
            ") as pend GROUP BY idTarefa" +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador) AND ct_realizado = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador);


    @Transaction
    @Query("SELECT * " +
            "FROM clientes as cl " +
            "LEFT JOIN (" +
            "SELECT idTarefa, IFNULL(SUM(ct_atp_res), 0) as ct_realizado FROM (" +
            "SELECT * FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT id, COUNT(*) as ct_atp_res FROM atividadesPendentesResultado GROUP BY id) as atp_res ON atp.id = atp_res.id" +
            ") as pend GROUP BY idTarefa" +
            ") as pend ON cl.idTarefa = pend.idTarefa " +
            "WHERE cl.idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador AND data =:data) AND ct_realizado = 0")
    abstract public Maybe<List<Pendencia>> obterPendencias(String idUtilizador, long data);






    @Transaction
    @Query("SELECT *,  " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " as sincronizado " +
            "FROM tarefas as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct FROM resultados WHERE sincronizado = :sincronizado GROUP BY idTarefa) as res " +
            "ON trf.idTarefa = res.idTarefa " +
            "WHERE ct > 0 AND idUtilizador = :idUtilizador")
    abstract public Maybe<List<Upload>> obterUploads(String idUtilizador, boolean sincronizado);



    @Transaction
    @Query("SELECT *, " +
            "CASE " +
            "WHEN IFNULL(ct_sinc_total, 0) = 0 THEN " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " " +
            "WHEN IFNULL(ct_sinc, 0) = IFNULL(ct_sinc_total, 0) THEN " + Identificadores.Sincronizacao.SINCRONIZADO + " " +
            "ELSE  " + Identificadores.Sincronizacao.NAO_SINCRONIZADO + " END as sincronizado " +
            "FROM tarefas  as trf " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct_sinc_total FROM resultados GROUP BY idTarefa) as res_sinc_total " +
            "ON trf.idTarefa = res_sinc_total.idTarefa " +
            "LEFT JOIN (SELECT idTarefa, COUNT(sincronizado) as ct_sinc FROM resultados WHERE sincronizado = "+ Identificadores.Sincronizacao.SINCRONIZADO +" GROUP BY idTarefa) as res_sinc " +
            "ON trf.idTarefa = res_sinc.idTarefa " +
            "WHERE ct_sinc_total > 0 AND idUtilizador = :idUtilizador AND data = :data")
    abstract public Maybe<List<Upload>> obterUploads(String idUtilizador, long data);






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

    @Query("SELECT * " +
            "FROM formandosResultado as frm_res " +
            "LEFT JOIN (SELECT id, idImagem FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_FORMANDO + ") as img " +
            "ON frm_res.id = img.id " +
            "WHERE idAtividade = :idAtividade AND selecionado = 1")
    abstract public List<FormandoResultado_> obterFormandos(int idAtividade);


    @Query("SELECT * FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public Tarefa obterTarefa(int idTarefa);


    @Query("SELECT * FROM imagensResultado WHERE idImagem IN(:ids)")
    abstract public List<ImagemResultado> obterImagens(List<Integer> ids);



    //-------------------
    //TRABALHO
    //-------------------


    @Insert
    abstract public Long inserirRegisto(Tarefa tarefa);

    @Insert
    abstract public void inserirRegisto(Cliente registo);


    @Insert
    abstract public List<Long> inserirAtividadesExecutadas(List<AtividadeExecutada> registos);

    @Insert
    abstract public List<Long> inserirAtividadesPendentes(List<AtividadePendente> registos);



    @Insert
    abstract public List<Long> inserirAnomalias(List<Anomalia> registos);


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);

    @Insert
    abstract public List<Long> inserir(List<OcorrenciaHistorico> registos);


    @Query("DELETE FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public void removerTarefa(int idTarefa);


    @Query("DELETE FROM tarefas WHERE idTarefa IN(SELECT idTarefa FROM tarefas WHERE idUtilizador = :idUtilizador AND data = :data)")
    abstract public void removerTrabalho(String idUtilizador, long data);

}