package com.vvm.sh.di.anomalias;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.anomalias.AnomaliasViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AnomaliasViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnomaliasViewModel.class)
    public abstract ViewModel bindAnomaliasViewModel(AnomaliasViewModel viewModel);
}
