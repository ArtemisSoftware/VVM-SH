package com.vvm.sh.di;

import com.vvm.sh.MainActivity;
import com.vvm.sh.di.agenda.AgendaModule;
import com.vvm.sh.di.agenda.AgendaScope;
import com.vvm.sh.di.agenda.AgendaViewModelsModule;
import com.vvm.sh.di.anomalias.AnomaliasModule;
import com.vvm.sh.di.anomalias.AnomaliasScope;
import com.vvm.sh.di.anomalias.AnomaliasViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesModule;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalModule;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalScope;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.averiguacao.AveriguacaoModule;
import com.vvm.sh.di.atividadesPendentes.averiguacao.AveriguacaoScope;
import com.vvm.sh.di.atividadesPendentes.averiguacao.AveriguacaoViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.certificadoTratamento.CertificadoTratamentoModule;
import com.vvm.sh.di.atividadesPendentes.certificadoTratamento.CertificadoTratamentoScope;
import com.vvm.sh.di.atividadesPendentes.certificadoTratamento.CertificadoTratamentoViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.checklist.ChecklistModule;
import com.vvm.sh.di.atividadesPendentes.checklist.ChecklistScope;
import com.vvm.sh.di.atividadesPendentes.checklist.ChecklistViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoModule;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoScope;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosModule;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosScope;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao.PropostaPlanoAcaoModule;
import com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao.PropostaPlanoAcaoScope;
import com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao.PropostaPlanoAcaoViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis.TrabalhadoresVulneraveisModule;
import com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis.TrabalhadoresVulneraveisScope;
import com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis.TrabalhadoresVulneraveisViewModelsModule;
import com.vvm.sh.di.imagens.ImagensModule;
import com.vvm.sh.di.imagens.ImagensScope;
import com.vvm.sh.di.imagens.ImagensViewModelsModule;
import com.vvm.sh.di.informacaoSst.InformacaoSstModule;
import com.vvm.sh.di.informacaoSst.InformacaoSstScope;
import com.vvm.sh.di.informacaoSst.InformacaoSstViewModelsModule;
import com.vvm.sh.di.planoAccao.PlanoAccaoModule;
import com.vvm.sh.di.planoAccao.PlanoAccaoScope;
import com.vvm.sh.di.planoAccao.PlanoAccaoViewModelsModule;
import com.vvm.sh.di.registoVisita.RegistoVisitaModule;
import com.vvm.sh.di.registoVisita.RegistoVisitaScope;
import com.vvm.sh.di.registoVisita.RegistoVisitaViewModelsModule;
import com.vvm.sh.di.ocorrencias.OcorrenciasModule;
import com.vvm.sh.di.ocorrencias.OcorrenciasScope;
import com.vvm.sh.di.ocorrencias.OcorrenciasViewModelsModule;
import com.vvm.sh.di.pesquisa.PesquisaModule;
import com.vvm.sh.di.pesquisa.PesquisaScope;
import com.vvm.sh.di.pesquisa.PesquisaViewModelsModule;
import com.vvm.sh.di.quadroPessoal.QuadroPessoalModule;
import com.vvm.sh.di.quadroPessoal.QuadroPessoalScope;
import com.vvm.sh.di.quadroPessoal.QuadroPessoalViewModelsModule;
import com.vvm.sh.di.tarefa.TarefaModule;
import com.vvm.sh.di.tarefa.TarefaScope;
import com.vvm.sh.di.tarefa.TarefaViewModelsModule;
import com.vvm.sh.di.transferencias.TransferenciasModule;
import com.vvm.sh.di.transferencias.TransferenciasScope;
import com.vvm.sh.di.transferencias.TransferenciasViewModelsModule;
import com.vvm.sh.ui.anomalias.DialogoAnomalia;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendenteExecutada;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendenteNaoExecutada;
import com.vvm.sh.ui.atividadesPendentes.relatorios.DialogoProcessoProdutivo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoGeralActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoIluminacaoRegistoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoTemperaturaHumidadeRegistoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacoesAmbientaisActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.RelatorioAvaliacaoAmbientalActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.AveriguacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.AveriguacaoListagemActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.DialogoAveriguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.CertificadoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.CertificadoTratamentoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.ChecklistActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.DialogoArea;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.DialogoChecklist;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.DialogoPergunta;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.QuestoesChecklistActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.SeccaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.DialogoEquipamento;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.EquipamentosActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.CategoriasProfissionaisActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.DialogoCategoriasProfissionais;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.DialogoModeloCategoriasProfissionais;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.DialogoModelos;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.LevantamentosActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.PerigoTarefaActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.RelatorioLevantamentoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.RiscoRegistoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.RiscosActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.PropostaPlanoAccaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.TrabalhadorVulneravelRegistoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.TrabalhadoresVulneraveisActivity;
import com.vvm.sh.ui.imagens.BibliotecaImagensActivity;
import com.vvm.sh.ui.imagens.GaleriaActivity;
import com.vvm.sh.ui.informacaoSst.DialogoResponsavel;
import com.vvm.sh.ui.informacaoSst.InformacaoSstActivity;
import com.vvm.sh.ui.informacaoSst.ObrigacoesLegaisActivity;
import com.vvm.sh.ui.pesquisa.PesquisaMedidasActivity;
import com.vvm.sh.ui.planoAccao.PlanoAccaoActivity;
import com.vvm.sh.ui.registoVisita.DadosClienteActivity;
import com.vvm.sh.ui.registoVisita.DialogoTrabalhoRealizado;
import com.vvm.sh.ui.registoVisita.RegistoVisitaActivity;
import com.vvm.sh.ui.registoVisita.TrabalhoRealizadoActivity;
import com.vvm.sh.ui.autenticacao.CarregamentoActivity;
import com.vvm.sh.ui.autenticacao.PerfilActivity;
import com.vvm.sh.ui.cliente.SinistralidadeActivity;
import com.vvm.sh.ui.parqueExtintores.ExtintoresActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasHistoricoActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasRegistoActivity;
import com.vvm.sh.ui.ocorrencias.RegistarOcorrenciaActivity;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.ui.quadroPessoal.ColaboradorActivity;
import com.vvm.sh.ui.quadroPessoal.DialogoColaborador;
import com.vvm.sh.ui.quadroPessoal.DialogoOpcoesColaborador;
import com.vvm.sh.ui.quadroPessoal.QuadroPessoalActivity;
import com.vvm.sh.ui.tarefa.DialogoEmail;
import com.vvm.sh.ui.tarefa.TarefaActivity;
import com.vvm.sh.ui.transferencias.AtualizacaoTiposActivity;
import com.vvm.sh.ui.transferencias.DownloadTrabalhoActivity;
import com.vvm.sh.ui.anomalias.AnomaliasActivity;
import com.vvm.sh.ui.atividadesExecutadas.AtividadesExecutadasActivity;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesActivity;
import com.vvm.sh.ui.autenticacao.AutenticacaoActivity;
import com.vvm.sh.di.autenticacao.AutenticacaoModule;
import com.vvm.sh.di.autenticacao.AutenticacaoScope;
import com.vvm.sh.di.autenticacao.AutenticacaoViewModelsModule;
import com.vvm.sh.di.crossSelling.CrossSellingModule;
import com.vvm.sh.di.crossSelling.CrossSellingScope;
import com.vvm.sh.di.crossSelling.CrossSellingViewModelsModule;
import com.vvm.sh.di.opcoes.OpcoesModule;
import com.vvm.sh.di.opcoes.OpcoesScope;
import com.vvm.sh.di.opcoes.OpcoesViewModelsModule;
import com.vvm.sh.ui.cliente.InformacaoActivity;
import com.vvm.sh.ui.crossSelling.CrossSellingActivity;
import com.vvm.sh.ui.crossSelling.DialogoSinaletica;
import com.vvm.sh.ui.ocorrencias.OcorrenciasActivity;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.ui.transferencias.UploadTrabalhoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AutenticacaoScope
    @ContributesAndroidInjector(
            modules = { AutenticacaoViewModelsModule.class, AutenticacaoModule.class }
    )
    abstract AutenticacaoActivity contributeAutenticacaoActivity();



    @OpcoesScope
    @ContributesAndroidInjector(
            modules = { OpcoesViewModelsModule.class, OpcoesModule.class }
    )
    abstract AtualizacaoAppActivity contributeAtualizacaoAppActivity();


    @OpcoesScope
    @ContributesAndroidInjector(
            modules = { OpcoesViewModelsModule.class, OpcoesModule.class }
    )
    abstract TiposActivity contributeTiposActivity();


    @TransferenciasScope
    @ContributesAndroidInjector(
            modules = { TransferenciasViewModelsModule.class, TransferenciasModule.class }
    )
    abstract UploadTrabalhoActivity contributeUploadActivity();


    @TransferenciasScope
    @ContributesAndroidInjector(
            modules = { TransferenciasViewModelsModule.class, TransferenciasModule.class }
    )
    abstract DownloadTrabalhoActivity contributeTrabalhoActivity();





    @AutenticacaoScope
    @ContributesAndroidInjector(
            modules = { AutenticacaoViewModelsModule.class, AutenticacaoModule.class }
    )
    abstract PerfilActivity contributePerfilActivity();




    @AgendaScope
    @ContributesAndroidInjector(
            modules = { AgendaViewModelsModule.class, AgendaModule.class }
    )
    abstract MainActivity contributeMainActivity();





    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract TarefaActivity contributeTarefaActivity();

    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract AtividadesExecutadasActivity contributeAtividadesExecutadasActivity();


    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract InformacaoActivity contributeInformacaoActivity();



    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract DialogoEmail contributeDialogoEmail();



    //----------------------
    //Sinistralidade
    //----------------------


    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract SinistralidadeActivity contributeSinistralidadeActivity();








    @AnomaliasScope
    @ContributesAndroidInjector(
            modules = { AnomaliasViewModelsModule.class, AnomaliasModule.class }
    )
    abstract AnomaliasActivity contributeAnomaliasActivity();


    @AnomaliasScope
    @ContributesAndroidInjector(
            modules = { AnomaliasViewModelsModule.class, AnomaliasModule.class }
    )
    abstract DialogoAnomalia contributeDialogoAnomalia();



    @OcorrenciasScope
    @ContributesAndroidInjector(
            modules = { OcorrenciasViewModelsModule.class, OcorrenciasModule.class }
    )
    abstract OcorrenciasActivity contributeOcorrenciasActivity();


    @OcorrenciasScope
    @ContributesAndroidInjector(
            modules = { OcorrenciasViewModelsModule.class, OcorrenciasModule.class }
    )
    abstract OcorrenciasRegistoActivity contributeOcorrenciasRegistoActivity();

    @OcorrenciasScope
    @ContributesAndroidInjector(
            modules = { OcorrenciasViewModelsModule.class, OcorrenciasModule.class }
    )
    abstract RegistarOcorrenciaActivity contributeRegistarOcorrenciaActivity();

    @OcorrenciasScope
    @ContributesAndroidInjector(
            modules = { OcorrenciasViewModelsModule.class, OcorrenciasModule.class }
    )
    abstract OcorrenciasHistoricoActivity contributeOcorrenciasHistoricoActivity();





    @AtividadesPendentesScope
    @ContributesAndroidInjector(
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract AtividadesPendentesActivity contributeAtividadesPendentesActivity();


    @AtividadesPendentesScope
    @ContributesAndroidInjector(
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract DialogoAtividadePendente contributeDialogoAtividadePendente();


    @AtividadesPendentesScope
    @ContributesAndroidInjector(
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract DialogoAtividadePendenteNaoExecutada contributeDialogoAtividadePendenteNaoExecutada();


    @AtividadesPendentesScope
    @ContributesAndroidInjector(
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract DialogoAtividadePendenteExecutada contributeDialogoAtividadePendenteExecutada();


    @AtividadesPendentesScope
    @ContributesAndroidInjector(
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract DialogoProcessoProdutivo contributeDialogoProcessoProdutivo();



    //----------------------
    //Formacao
    //----------------------


    @FormacaoScope
    @ContributesAndroidInjector(
            modules = { FormacaoViewModelsModule.class, FormacaoModule.class }
    )
    abstract FormacaoActivity contributeFormacaoActivity();

    @FormacaoScope
    @ContributesAndroidInjector(
            modules = { FormacaoViewModelsModule.class, FormacaoModule.class }
    )
    abstract FormandoActivity contributeFormandoActivity();

    @FormacaoScope
    @ContributesAndroidInjector(
            modules = { FormacaoViewModelsModule.class, FormacaoModule.class }
    )
    abstract AcaoFormacaoActivity contributeAcaoFormacaoActivity();




    @CrossSellingScope
    @ContributesAndroidInjector(
            modules = { CrossSellingViewModelsModule.class, CrossSellingModule.class }
    )
    abstract CrossSellingActivity contributeCrossSellingActivity();


    @CrossSellingScope
    @ContributesAndroidInjector(
            modules = { CrossSellingViewModelsModule.class, CrossSellingModule.class }
    )
    abstract DialogoSinaletica contributeDialogoSinaletica();







    //----------------------
    //Pesquisa
    //----------------------

    @PesquisaScope
    @ContributesAndroidInjector(
            modules = { PesquisaViewModelsModule.class, PesquisaModule.class }
    )
    abstract PesquisaActivity contributePesquisaActivity();


    @PesquisaScope
    @ContributesAndroidInjector(
            modules = { PesquisaViewModelsModule.class, PesquisaModule.class }
    )
    abstract PesquisaMedidasActivity contributePesquisaMedidasActivity();

    //----------------------
    //Registo visita
    //----------------------


    @RegistoVisitaScope
    @ContributesAndroidInjector(
            modules = { RegistoVisitaViewModelsModule.class, RegistoVisitaModule.class }
    )
    abstract RegistoVisitaActivity contributeRegistoVisitaActivity();


    @RegistoVisitaScope
    @ContributesAndroidInjector(
            modules = { RegistoVisitaViewModelsModule.class, RegistoVisitaModule.class }
    )
    abstract DadosClienteActivity contributeDadosClienteActivity();


    @RegistoVisitaScope
    @ContributesAndroidInjector(
            modules = { RegistoVisitaViewModelsModule.class, RegistoVisitaModule.class }
    )
    abstract TrabalhoRealizadoActivity contributeTrabalhoRealizadoActivity();

    @RegistoVisitaScope
    @ContributesAndroidInjector(
            modules = { RegistoVisitaViewModelsModule.class, RegistoVisitaModule.class }
    )
    abstract DialogoTrabalhoRealizado contributeDialogoTrabalhoRealizado();



    //----------------------
    //Trabalhadores vulneraveis
    //----------------------

    @TrabalhadoresVulneraveisScope
    @ContributesAndroidInjector(
            modules = { TrabalhadoresVulneraveisViewModelsModule.class, TrabalhadoresVulneraveisModule.class }
    )
    abstract TrabalhadoresVulneraveisActivity contributeTrabalhadoresVulneraveisActivity();


    @TrabalhadoresVulneraveisScope
    @ContributesAndroidInjector(
            modules = { TrabalhadoresVulneraveisViewModelsModule.class, TrabalhadoresVulneraveisModule.class }
    )
    abstract TrabalhadorVulneravelRegistoActivity contributeTrabalhadorVulneravelRegistoActivity();



    //----------------------
    //Plano Accao
    //----------------------


    @PlanoAccaoScope
    @ContributesAndroidInjector(
            modules = { PlanoAccaoViewModelsModule.class, PlanoAccaoModule.class }
    )
    abstract PlanoAccaoActivity contributePlanoAccaoActivity();


    //----------------------
    //Equipamentos
    //----------------------

    @PesquisaScope
    @ContributesAndroidInjector(
            modules = { PesquisaViewModelsModule.class, PesquisaModule.class }
    )
    abstract EquipamentosActivity contributeEquipamentosActivity();

    @PesquisaScope
    @ContributesAndroidInjector(
            modules = { PesquisaViewModelsModule.class, PesquisaModule.class }
    )
    abstract DialogoEquipamento contributeDialogoEquipamento();


    //----------------------
    //Averiguacao
    //----------------------


    @AveriguacaoScope
    @ContributesAndroidInjector(
            modules = { AveriguacaoViewModelsModule.class, AveriguacaoModule.class }
    )
    abstract AveriguacaoActivity contributeAveriguacaoActivity();

    @AveriguacaoScope
    @ContributesAndroidInjector(
            modules = { AveriguacaoViewModelsModule.class, AveriguacaoModule.class }
    )
    abstract AveriguacaoListagemActivity contributeAveriguacaoListagemActivity();

    @AveriguacaoScope
    @ContributesAndroidInjector(
            modules = { AveriguacaoViewModelsModule.class, AveriguacaoModule.class }
    )
    abstract DialogoAveriguacao contributeDialogoAveriguacao();


    //----------------------
    //Proposta plano acao
    //----------------------


    @PropostaPlanoAcaoScope
    @ContributesAndroidInjector(
            modules = { PropostaPlanoAcaoViewModelsModule.class, PropostaPlanoAcaoModule.class }
    )
    abstract PropostaPlanoAccaoActivity contributePropostaPlanoAccaoActivity();

    @TransferenciasScope
    @ContributesAndroidInjector(
            modules = { TransferenciasViewModelsModule.class, TransferenciasModule.class }
    )
    abstract CarregamentoActivity contributeCarregamentoActivity();

    @TransferenciasScope
    @ContributesAndroidInjector(
            modules = { TransferenciasViewModelsModule.class, TransferenciasModule.class }
    )
    abstract AtualizacaoTiposActivity contributeAtualizacaoTiposActivity();

    //----------------------
    //Avaliacao ambiental
    //----------------------

    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract RelatorioAvaliacaoAmbientalActivity contributeRelatorioAvaliacaoAmbientalActivity();

    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract AvaliacaoGeralActivity contributeAvaliacaoGeralActivity();

    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract AvaliacoesAmbientaisActivity contributeAvaliacoesAmbientaisActivity();

    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract AvaliacaoTemperaturaHumidadeRegistoActivity contributeAvaliacaoTemperaturaHumidadeRegistoActivity();

    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract AvaliacaoIluminacaoRegistoActivity contributeAvaliacaoAvaliacaoIluminacaoRegistoActivity();


    //---------------------
    //Checklist
    //---------------------

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract ChecklistActivity contributeChecklistActivity();

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract SeccaoActivity contributeSeccaoActivity();

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract QuestoesChecklistActivity contributeQuestoesChecklistActivity();

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract DialogoArea contributeDialogoArea();

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract DialogoPergunta contributeDialogoPergunta();

    @ChecklistScope
    @ContributesAndroidInjector(
            modules = { ChecklistViewModelsModule.class, ChecklistModule.class }
    )
    abstract DialogoChecklist contributeDialogoChecklist();



    //----------------------
    //Parque Extintores
    //-----------------------

    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract ExtintoresActivity contributeExtintoresActivity();

    //----------------------
    //Levantamentos
    //-----------------------


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract LevantamentosActivity contributeLevantamentosActivity();


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract RelatorioLevantamentoActivity contributeRelatorioLevantamentoActivity();

    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract PerigoTarefaActivity contributePerigoTarefaActivity();

    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract CategoriasProfissionaisActivity contributeCategoriasProfissionaisActivity();

    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract DialogoCategoriasProfissionais contributeDialogoCategoriasProfissionais();


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract RiscosActivity contributeRiscosActivity();


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract RiscoRegistoActivity contributeRiscoRegistoActivity();


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract DialogoModelos contributeDialogoModelos();


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract DialogoModeloCategoriasProfissionais contributeDialogoModeloCategoriasProfissionais();


    //-----------------
    //Quadro pessoal
    //-----------------


    @QuadroPessoalScope
    @ContributesAndroidInjector(
            modules = { QuadroPessoalViewModelsModule.class, QuadroPessoalModule.class }
    )
    abstract ColaboradorActivity contributeColaboradorActivity();

    @QuadroPessoalScope
    @ContributesAndroidInjector(
            modules = { QuadroPessoalViewModelsModule.class, QuadroPessoalModule.class }
    )
    abstract DialogoColaborador contributeDialogoColaborador();

    @QuadroPessoalScope
    @ContributesAndroidInjector(
            modules = { QuadroPessoalViewModelsModule.class, QuadroPessoalModule.class }
    )
    abstract DialogoOpcoesColaborador contributeDialogoOpcoesColaborador();

    @QuadroPessoalScope
    @ContributesAndroidInjector(
            modules = { QuadroPessoalViewModelsModule.class, QuadroPessoalModule.class }
    )
    abstract QuadroPessoalActivity contributeQuadroPessoalActivity();


    //-----------------
    //Imagens
    //-----------------

    @ImagensScope
    @ContributesAndroidInjector(
            modules = { ImagensViewModelsModule.class, ImagensModule.class }
    )
    abstract BibliotecaImagensActivity contributeBibliotecaImagensActivity();


    @ImagensScope
    @ContributesAndroidInjector(
            modules = { ImagensViewModelsModule.class, ImagensModule.class }
    )
    abstract GaleriaActivity contributeImagensActivity();


    //-----------------
    //Informacao Sst
    //-----------------


    @InformacaoSstScope
    @ContributesAndroidInjector(
            modules = { InformacaoSstViewModelsModule.class, InformacaoSstModule.class }
    )
    abstract InformacaoSstActivity contributeInformacaoSstActivity();

    @InformacaoSstScope
    @ContributesAndroidInjector(
            modules = { InformacaoSstViewModelsModule.class, InformacaoSstModule.class }
    )
    abstract ObrigacoesLegaisActivity contributeObrigacoesLegaisActivity();


    @InformacaoSstScope
    @ContributesAndroidInjector(
            modules = { InformacaoSstViewModelsModule.class, InformacaoSstModule.class }
    )
    abstract DialogoResponsavel contributeDialogoResponsavel();

    //-----------------
    //Certificado de tratamento
    //-----------------

    @CertificadoTratamentoScope
    @ContributesAndroidInjector(
            modules = { CertificadoTratamentoViewModelsModule.class, CertificadoTratamentoModule.class }
    )
    abstract CertificadoTratamentoActivity contributeCertificadoTratamentoActivity();


    @CertificadoTratamentoScope
    @ContributesAndroidInjector(
            modules = { CertificadoTratamentoViewModelsModule.class, CertificadoTratamentoModule.class }
    )
    abstract CertificadoActivity contributeCertificadoActivity();




}
