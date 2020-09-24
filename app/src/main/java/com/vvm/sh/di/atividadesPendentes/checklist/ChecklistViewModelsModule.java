package com.vvm.sh.di.atividadesPendentes.checklist;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.ChecklistViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ChecklistViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChecklistViewModel.class)
    public abstract ViewModel bindChecklistViewModel(ChecklistViewModel viewModel);

}
