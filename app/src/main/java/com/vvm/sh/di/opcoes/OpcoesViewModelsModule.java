package com.vvm.sh.di.opcoes;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.opcoes.OpcoesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class OpcoesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(OpcoesViewModel.class)
    public abstract ViewModel bindPokemonViewModel(OpcoesViewModel viewModel);
}
