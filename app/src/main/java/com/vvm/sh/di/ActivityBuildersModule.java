package com.vvm.sh.di;

import com.vvm.sh.di.opcoes.OpcoesModule;
import com.vvm.sh.di.opcoes.OpcoesScope;
import com.vvm.sh.di.opcoes.OpcoesViewModelsModule;
import com.vvm.sh.ui.contaUtilizador.TiposActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


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
