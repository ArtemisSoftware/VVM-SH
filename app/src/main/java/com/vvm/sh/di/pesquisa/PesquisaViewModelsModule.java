package com.vvm.sh.di.pesquisa;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.pesquisa.PesquisaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PesquisaViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PesquisaViewModel.class)
    public abstract ViewModel bindPesquisaViewModel(PesquisaViewModel viewModel);
}
