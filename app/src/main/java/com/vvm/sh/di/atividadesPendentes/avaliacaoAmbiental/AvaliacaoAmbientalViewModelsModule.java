package com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbientalViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AvaliacaoAmbientalViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AvaliacaoAmbientalViewModel.class)
    public abstract ViewModel bindAvaliacaoAmbientalViewModel(AvaliacaoAmbientalViewModel viewModel);
}
