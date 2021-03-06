package com.vvm.sh.di.registoVisita;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.registoVisita.RegistoVisitaViewModel;

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

