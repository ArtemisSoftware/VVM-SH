package com.vvm.sh.baseDados;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vvm.sh.baseDados.dao.DownloadTrabalhoDao;
import com.vvm.sh.baseDados.dao.ImagemResultadoDao;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoResultado;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.crossSelling.modelos.CrossSellingResultado;
import com.vvm.sh.ui.ocorrencias.modelos.Ocorrencia;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaHistorico;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaResultado;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;

@Database(
            entities = {
                    Atualizacao.class, Tipo.class, Utilizador.class,
                    Tarefa.class,

                    //Trabalho

                    Cliente.class, AtividadeExecutada.class, Anomalia.class, AtividadePendente.class, Ocorrencia.class, OcorrenciaHistorico.class,

                    //Resultado

                    EmailResultado.class, AnomaliaResultado.class, FormandoResultado.class, AcaoFormacaoResultado.class,
                    AtividadePendenteResultado.class, CrossSellingResultado.class, OcorrenciaResultado.class,
                    ImagemResultado.class,
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

    public abstract AtividadeExecutadaDao obterAtividadeExecutadaDao();

    public abstract ClienteDao obterClienteDao();

    public abstract AnomaliaDao obterAnomaliaDao();

    public abstract AtividadePendenteDao obterAtividadePendenteDao();

    public abstract OcorrenciaDao obterOcorrenciaDao();

    public abstract OcorrenciaHistoricoDao obterOcorrenciaHistoricoDao();

    public abstract DownloadTrabalhoDao obterDownloadTrabalhoDao();

    //---------------------
    //Resultados
    //---------------------

    public abstract EmailDao obterEmailDao();

    public abstract AnomaliaResultadoDao obterAnomaliaResultadoDao();

    public abstract AtividadePendenteResultadoDao obterAtividadePendenteResultadoDao();

    public abstract FormandoDao obterFormandoDao();

    public abstract OcorrenciaResultadoDao obterOcorrenciaResultadoDao();

    public abstract ResultadoDao obterResultadoDao();

    public abstract CrossSellingDao obterCrossSellingDao();

    public abstract AcaoFormacaoDao obterAcaoFormacaoDao();


    public abstract ImagemResultadoDao obterImagemResultadoDao();

    public abstract UploadDao obterUploadDao();
}
