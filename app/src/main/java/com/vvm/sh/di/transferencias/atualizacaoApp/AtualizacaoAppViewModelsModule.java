package com.vvm.sh.di.transferencias.atualizacaoApp;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.transferencias.TransferenciasViewModel;
import com.vvm.sh.ui.transferencias.atualizacaoApp.AtualizacaoAppViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AtualizacaoAppViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AtualizacaoAppViewModel.class)
    public abstract ViewModel bindAtualizacaoAppViewModel(AtualizacaoAppViewModel viewModel);
}
