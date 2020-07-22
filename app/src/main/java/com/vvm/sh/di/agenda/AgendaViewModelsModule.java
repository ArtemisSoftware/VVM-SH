package com.vvm.sh.di.agenda;


import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.agenda.AgendaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AgendaViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AgendaViewModel.class)
    public abstract ViewModel bindAgendaViewModel(AgendaViewModel viewModel);
}
