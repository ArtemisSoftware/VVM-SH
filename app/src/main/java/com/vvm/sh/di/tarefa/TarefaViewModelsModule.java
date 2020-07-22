package com.vvm.sh.di.tarefa;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.tarefa.TarefaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TarefaViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TarefaViewModel.class)
    public abstract ViewModel bindTarefaViewModelModel(TarefaViewModel viewModel);
}
