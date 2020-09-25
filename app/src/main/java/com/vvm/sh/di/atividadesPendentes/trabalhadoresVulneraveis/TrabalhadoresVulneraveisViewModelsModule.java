package com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.TrabalhadoresVulneraveisViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class TrabalhadoresVulneraveisViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrabalhadoresVulneraveisViewModel.class)
    public abstract ViewModel bindTrabalhadoresVulneraveisViewModel(TrabalhadoresVulneraveisViewModel viewModel);

}
