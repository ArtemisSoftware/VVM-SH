package com.vvm.sh.di.ocorrencias;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.ocorrencias.OcorrenciasViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class OcorrenciasViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(OcorrenciasViewModel.class)
    public abstract ViewModel bindOcorrenciasViewModel(OcorrenciasViewModel viewModel);
}
