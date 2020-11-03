package com.vvm.sh.baseDados;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vvm.sh.baseDados.dao.AcaoFormacaoDao;
import com.vvm.sh.baseDados.dao.AgendaDao;
import com.vvm.sh.baseDados.dao.AnomaliaDao;
import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.AveriguacaoDao;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.CrossSellingDao;
import com.vvm.sh.baseDados.dao.EmailDao;
import com.vvm.sh.baseDados.dao.FormandoDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.LevantamentoAvaliacaoDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.OcorrenciaDao;
import com.vvm.sh.baseDados.dao.ParqueExtintorDao;
import com.vvm.sh.baseDados.dao.PdfDao;
import com.vvm.sh.baseDados.dao.PesquisaDao;
import com.vvm.sh.baseDados.dao.PlanoAccaoDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.QuadroPessoalDao;
import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.RegistoVisitaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.RiscoDao;
import com.vvm.sh.baseDados.dao.SinistralidadeDao;
import com.vvm.sh.baseDados.dao.TarefaDao;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.baseDados.dao.TrabalhosRealizadosDao;
import com.vvm.sh.baseDados.dao.TransferenciasDao;
import com.vvm.sh.baseDados.dao.UploadDao;
import com.vvm.sh.baseDados.dao.UtilizadorDao;
import com.vvm.sh.baseDados.dao.VerificacaoEquipamentoDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaAveriguacao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAuditoriaMedida;
import com.vvm.sh.baseDados.entidades.RelatorioAvaliacaoRiscosMedida;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
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
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;

@Database(
            entities = {
                    Atualizacao.class, Tipo.class, Utilizador.class,
                    Tarefa.class,
                    CheckList.class, AreaChecklist.class, SeccaoChecklist.class, ItemChecklist.class,
                    TipoAtividadePlaneavel.class, TipoTemplateAvrLevantamento.class, TipoTemplateAvrRisco.class, TipoTemplatesAVRMedidaRisco.class,
                    TipoNovo.class,

                    //Trabalho

                    Cliente.class, AtividadeExecutada.class, Anomalia.class, AtividadePendente.class, Ocorrencia.class, OcorrenciaHistorico.class,
                    Morada.class,
                    ParqueExtintor.class, TipoExtintor.class, Colaborador.class, PlanoAcao.class, PlanoAcaoAtividade.class,
                    RelatorioAveriguacao.class, RelatorioAuditoriaMedida.class, RelatorioAvaliacaoRiscosMedida.class, MedidaAveriguacao.class,

                    //Resultado

                    EmailResultado.class, AnomaliaResultado.class, FormandoResultado.class, AcaoFormacaoResultado.class,
                    AtividadePendenteResultado.class, CrossSellingResultado.class, OcorrenciaResultado.class, PlanoAccaoResultado.class,
                    ImagemResultado.class, SinistralidadeResultado.class,
                    ParqueExtintorResultado.class,
                    CategoriaProfissionalResultado.class, MedidaResultado.class,
                    ColaboradorResultado.class,
                    RelatorioAmbientalResultado.class, AvaliacaoAmbientalResultado.class,

                    TrabalhoRealizadoResultado.class, RegistoVisitaResultado.class,

                    LevantamentoRiscoResultado.class, ProcessoProdutivoResultado.class, TrabalhadorVulneravelResultado.class,
                    AreaChecklistResultado.class, QuestionarioChecklistResultado.class, PropostaPlanoAcaoResultado.class, RiscoResultado.class,
                    VerificacaoEquipamentoResultado.class,

                    RelatorioAveriguacaoResultado.class,

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
    public abstract TipoNovoDao obterTipoNovoDao();

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

    public abstract PlanoAccaoDao obterPlanoAccaoDao();

    public abstract AcaoFormacaoDao obterAcaoFormacaoDao();



    public abstract ImagemDao obterImagemDao();


    public abstract SinistralidadeDao obterSinistralidadeDao();

    public abstract ParqueExtintorDao obterParqueExtintorDao();

    public abstract CategoriaProfissionalDao obterCategoriaProfissionalDao();

    public abstract MedidaDao obterMedidaDao();



    public abstract QuadroPessoalDao obterQuadroPessoalDao();

    public abstract AvaliacaoAmbientalDao obterAvaliacaoAmbientalDao();




    public abstract LevantamentoDao obterLevantamentoDao();
    public abstract LevantamentoAvaliacaoDao obterLevantamentoAvaliacaoDao();
    public abstract ProcessoProdutivoDao obterProcessoProdutivoDao();
    public abstract TrabalhadoresVulneraveisDao obterTrabalhadoresVulneraveisDao();
    public abstract RiscoDao obterRiscoDao();

    public abstract AreaChecklistDao obterAreaChecklistDao();
    public abstract QuestionarioChecklistDao obterQuestionarioChecklistDao();


    public abstract RegistoVisitaDao obterRegistoVisitaDao();

    public abstract TrabalhosRealizadosDao obterTrabalhosRealizadosDao();




    public abstract TransferenciasDao obterTransferenciasDao();



    //---------------------
    //
    //---------------------


    public abstract AgendaDao obterAgendaDao();

    public abstract VerificacaoEquipamentoDao obterVerificacaoEquipamentoDao();

    public abstract PropostaPlanoAcaoDao obterPropostaPlanoAcaoDao();

    public abstract AveriguacaoDao obterAveriguacaoDao();

    public abstract PdfDao obterPdfDao();

    public abstract UploadDao obterUploadDao();

    public abstract PesquisaDao obterPesquisaDao();
}
