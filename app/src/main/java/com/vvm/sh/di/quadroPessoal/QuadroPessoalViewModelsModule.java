package com.vvm.sh.di.quadroPessoal;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.quadroPessoal.QuadroPessoalViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class QuadroPessoalViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuadroPessoalViewModel.class)
    public abstract ViewModel bindQuadroPessoalViewModel(QuadroPessoalViewModel viewModel);
}
