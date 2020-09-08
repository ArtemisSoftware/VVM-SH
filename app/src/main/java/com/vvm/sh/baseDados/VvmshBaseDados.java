package com.vvm.sh.baseDados;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.CrossSellingDao;
import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.OcorrenciaDao;
import com.vvm.sh.baseDados.dao.ParqueExtintorDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.SinistralidadeDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.SinistralidadeResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;

@Database(
            entities = {
                    Atualizacao.class, Tipo.class, Utilizador.class,
                    Tarefa.class,

                    //Trabalho

                    Cliente.class, AtividadeExecutada.class, Anomalia.class, AtividadePendente.class, Ocorrencia.class, OcorrenciaHistorico.class,
                    Morada.class,
                    ParqueExtintor.class, TipoExtintor.class,

                    //Resultado

                    EmailResultado.class, AnomaliaResultado.class, FormandoResultado.class, AcaoFormacaoResultado.class,
                    AtividadePendenteResultado.class, CrossSellingResultado.class, OcorrenciaResultado.class,
                    ImagemResultado.class, SinistralidadeResultado.class,
                    ParqueExtintorResultado.class,
                    Resultado.class
            },
            version = BaseDadosContantes.VERSAO
)
@TypeConverters({Conversor.class})
public abstract class VvmshBaseDados extends RoomDatabase {


    //---------------------
    //Geral
    //---------------------

    public abstract AtualizacaoDao obterAtualizacaoDao();

    public abstract TipoDao obterTipoDao();

    public abstract UtilizadorDao obterUtilizadorDao();


    //---------------------
    //Trabalho
    //---------------------

    public abstract TarefaDao obterTarefaDao();



    //---------------------
    //Resultados
    //---------------------

    public abstract EmailDao obterEmailDao();

    public abstract AnomaliaDao obterAnomaliaDao();

    public abstract AtividadePendenteDao obterAtividadePendenteDao();

    public abstract FormandoDao obterFormandoDao();

    public abstract OcorrenciaDao obterOcorrenciaDao();

    public abstract ResultadoDao obterResultadoDao();

    public abstract CrossSellingDao obterCrossSellingDao();

    public abstract AcaoFormacaoDao obterAcaoFormacaoDao();

    public abstract ParqueExtintorDao obterParqueExtintorDao();


    public abstract ImagemDao obterImagemDao();

    public abstract SinistralidadeDao obterSinistralidadeDao();

    public abstract TransferenciasDao obterTransferenciasDao();


    //---------------------
    //
    //---------------------


    public abstract AgendaDao obterAgendaDao();
}
