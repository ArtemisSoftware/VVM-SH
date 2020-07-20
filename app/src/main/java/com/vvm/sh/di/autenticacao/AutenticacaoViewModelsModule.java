package com.vvm.sh.di.autenticacao;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.ui.autenticacao.AutenticacaoViewModel;
import com.vvm.sh.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AutenticacaoViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AutenticacaoViewModel.class)
    public abstract ViewModel bindAutenticacaoViewModel(AutenticacaoViewModel viewModel);
}
