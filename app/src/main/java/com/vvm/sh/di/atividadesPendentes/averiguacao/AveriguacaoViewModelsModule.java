package com.vvm.sh.di.atividadesPendentes.averiguacao;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.AveriguacaoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AveriguacaoViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AveriguacaoViewModel.class)
    public abstract ViewModel bindAveriguacaoViewModel(AveriguacaoViewModel viewModel);
}
