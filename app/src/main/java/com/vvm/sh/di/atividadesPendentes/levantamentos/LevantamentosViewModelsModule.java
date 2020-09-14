package com.vvm.sh.di.atividadesPendentes.levantamentos;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.LevantamentosViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class LevantamentosViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LevantamentosViewModel.class)
    public abstract ViewModel bindLevantamentosViewModel(LevantamentosViewModel viewModel);
}
