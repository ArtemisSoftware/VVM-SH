package com.vvm.sh.di.planoAccao;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.planoAccao.PlanoAccaoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PlanoAccaoViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlanoAccaoViewModel.class)
    public abstract ViewModel bindPlanoAccaoViewModel(PlanoAccaoViewModel viewModel);
}
