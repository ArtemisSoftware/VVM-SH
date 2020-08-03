package com.vvm.sh.di.upload;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.upload.UploadViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class UploadViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(UploadViewModel.class)
    public abstract ViewModel bindUploadViewModel(UploadViewModel viewModel);
}
