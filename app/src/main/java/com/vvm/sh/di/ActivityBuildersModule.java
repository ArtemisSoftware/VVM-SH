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
import com.vvm.sh.di.atividadesPendentes.FormacaoScope;
import com.vvm.sh.di.ocorrencias.OcorrenciasModule;
import com.vvm.sh.di.ocorrencias.OcorrenciasScope;
import com.vvm.sh.di.ocorrencias.OcorrenciasViewModelsModule;
import com.vvm.sh.di.tarefa.TarefaModule;
import com.vvm.sh.di.tarefa.TarefaScope;
import com.vvm.sh.di.tarefa.TarefaViewModelsModule;
import com.vvm.sh.ui.anomalias.DialogoAnomalia;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendente;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendenteExecutada;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendenteNaoExecutada;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasRegistoActivity;
import com.vvm.sh.ui.ocorrencias.RegistarOcorrenciaActivity;
import com.vvm.sh.ui.tarefa.DialogoEmail;
import com.vvm.sh.ui.tarefa.TarefaActivity;
import com.vvm.sh.ui.agenda.TrabalhoActivity;
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

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AutenticacaoScope
    @ContributesAndroidInjector(
            modules = { AutenticacaoViewModelsModule.class, AutenticacaoModule.class }
    )
    abstract AutenticacaoActivity contributeAutenticacaoActivity();






    @AgendaScope
    @ContributesAndroidInjector(
            modules = { AgendaViewModelsModule.class, AgendaModule.class }
    )
    abstract MainActivity contributeMainActivity();


    @AgendaScope
    @ContributesAndroidInjector(
            modules = { AgendaViewModelsModule.class, AgendaModule.class }
    )
    abstract TrabalhoActivity contributeTrabalhoActivity();




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
            modules = { AtividadesPendentesViewModelsModule.class, AtividadesPendentesModule.class }
    )
    abstract FormacaoActivity contributeFormacaoActivity();




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


}
