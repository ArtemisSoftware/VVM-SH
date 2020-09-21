package com.vvm.sh.di.atividadesPendentes.registoVisita;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.relatorios.registoVisita.RegistoVisitaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class RegistoVisitaViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistoVisitaViewModel.class)
    public abstract ViewModel bindAtividadesPendentesViewModel(RegistoVisitaViewModel viewModel);

}

