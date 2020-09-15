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
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoModule;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoScope;
import com.vvm.sh.di.atividadesPendentes.formacao.FormacaoViewModelsModule;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosModule;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosScope;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosViewModelsModule;
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
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormandoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoIluminacaoRegistoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacoesAmbientaisActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.RelatorioAvaliacaoAmbientalActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.LevantamentosActivity;
import com.vvm.sh.ui.autenticacao.PerfilActivity;
import com.vvm.sh.ui.cliente.SinistralidadeActivity;
import com.vvm.sh.ui.cliente.extintores.ExtintoresActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasHistoricoActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasRegistoActivity;
import com.vvm.sh.ui.ocorrencias.RegistarOcorrenciaActivity;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.ui.quadroPessoal.ColaboradorActivity;
import com.vvm.sh.ui.quadroPessoal.DialogoColaborador;
import com.vvm.sh.ui.quadroPessoal.QuadroPessoalActivity;
import com.vvm.sh.ui.tarefa.DialogoEmail;
import com.vvm.sh.ui.tarefa.TarefaActivity;
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


    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract SinistralidadeActivity contributeSinistralidadeActivity();

    @TarefaScope
    @ContributesAndroidInjector(
            modules = { TarefaViewModelsModule.class, TarefaModule.class }
    )
    abstract ExtintoresActivity contributeExtintoresActivity();





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
    //Quadro pessoal
    //----------------------


    @QuadroPessoalScope
    @ContributesAndroidInjector(
            modules = { QuadroPessoalViewModelsModule.class, QuadroPessoalModule.class }
    )
    abstract QuadroPessoalActivity contributeQuadroPessoalActivity();


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
    abstract AvaliacoesAmbientaisActivity contributeAvaliacoesAmbientaisActivity();


    @AvaliacaoAmbientalScope
    @ContributesAndroidInjector(
            modules = { AvaliacaoAmbientalViewModelsModule.class, AvaliacaoAmbientalModule.class }
    )
    abstract AvaliacaoIluminacaoRegistoActivity contributeAvaliacaoIluminacaoRegistoActivity();



    //----------------------
    //Levantamentos
    //----------------------


    @LevantamentosScope
    @ContributesAndroidInjector(
            modules = { LevantamentosViewModelsModule.class, LevantamentosModule.class }
    )
    abstract LevantamentosActivity contributeLevantamentosActivity();






    @PesquisaScope
    @ContributesAndroidInjector(
            modules = { PesquisaViewModelsModule.class, PesquisaModule.class }
    )
    abstract PesquisaActivity contributePesquisaActivity();






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


}
