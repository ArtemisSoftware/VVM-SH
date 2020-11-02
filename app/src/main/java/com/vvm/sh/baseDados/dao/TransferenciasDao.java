package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.ColaboradorBd;
import com.vvm.sh.api.modelos.bd.ExtintorBd;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.envio.Checklist;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;
import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.ui.transferencias.modelos.Pendencia;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

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
    @Query("SELECT CASE WHEN IFNULL(COUNT(*), 0) > 0 THEN 1 ELSE 0 END dadosUpload " +
            "FROM resultados " +
            "WHERE idTarefa IN (SELECT idTarefa FROM tarefas WHERE idUtilizador =:idUtilizador) " +
            "AND sincronizado = 0")
    abstract public Maybe<Boolean> existemDadosUpload(String idUtilizador);




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
    //TRABALHO
    //-------------------


    @Insert
    abstract public Long inserirRegisto(Tarefa tarefa);

    @Insert
    abstract public void inserirRegisto(Cliente registo);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public List<Long> inserirAtividadesExecutadas(List<AtividadeExecutada> registos);

    @Insert
    abstract public List<Long> inserirAtividadesPendentes(List<AtividadePendente> registos);



    @Insert
    abstract public List<Long> inserirAnomalias(List<Anomalia> registos);


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);

    @Insert
    abstract public List<Long> inserir(List<OcorrenciaHistorico> registos);



    @Insert
    abstract public List<Long> inserirMoradas(List<Morada> registos);

    @Insert
    abstract public List<Long> inserirTipoExtintor(List<TipoExtintor> registos);

    @Insert
    abstract public List<Long> inserirParqueExtintores(List<ParqueExtintor> registos);

    @Insert
    abstract public List<Long>  inserirQuadroPessoal(List<Colaborador> registos);


    @Insert
    abstract public Long inserirPlanoAcao(PlanoAcao registo);

    @Insert
    abstract public List<Long>  inserirPlanoAtividades(List<PlanoAcaoAtividade> registos);



    @Query("DELETE FROM tarefas WHERE idTarefa = :idTarefa")
    abstract public void removerTarefa(int idTarefa);


    @Query("DELETE FROM tarefas WHERE idTarefa IN(SELECT idTarefa FROM tarefas WHERE idUtilizador = :idUtilizador AND data = :data)")
    abstract public void removerTrabalho(String idUtilizador, long data);



}
