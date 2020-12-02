package com.vvm.sh.di.imagens;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.imagens.ImagemViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ImagensViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImagemViewModel.class)
    public abstract ViewModel bindImagemViewModel(ImagemViewModel viewModel);
}
