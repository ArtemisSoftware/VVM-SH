package com.vvm.sh.di.crossSelling;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.crossSelling.CrossSellingViewModel;

import dagger.Binds;
import dagger.multibindings.IntoMap;

public abstract class CrossSellingViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CrossSellingViewModel.class)
    public abstract ViewModel bindCrossSellingViewModel(CrossSellingViewModel viewModel);
}
