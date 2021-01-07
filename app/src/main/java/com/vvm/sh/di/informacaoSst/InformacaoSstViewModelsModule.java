package com.vvm.sh.di.informacaoSst;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.informacaoSst.InformacaoSstViewModel;
import com.vvm.sh.ui.registoVisita.RegistoVisitaViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class InformacaoSstViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(InformacaoSstViewModel.class)
    public abstract ViewModel bindInformacaoSstViewModel(InformacaoSstViewModel viewModel);

}

