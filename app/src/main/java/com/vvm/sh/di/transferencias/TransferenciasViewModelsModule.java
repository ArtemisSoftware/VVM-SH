package com.vvm.sh.di.transferencias;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.transferencias.TransferenciasViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TransferenciasViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransferenciasViewModel.class)
    public abstract ViewModel bindUploadViewModel(TransferenciasViewModel viewModel);
}
