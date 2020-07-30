package com.vvm.sh.di.atividadesPendentes;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AtividadesPendentesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AtividadesPendentesViewModel.class)
    public abstract ViewModel bindAtividadesPendentesViewModel(AtividadesPendentesViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(FormacaoViewModel.class)
    public abstract ViewModel bindFormacaoViewModel(FormacaoViewModel viewModel);
}
