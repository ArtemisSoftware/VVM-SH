package com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.PropostaPlanoAcaoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PropostaPlanoAcaoViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PropostaPlanoAcaoViewModel.class)
    public abstract ViewModel bindPropostaPlanoAcaoViewModel(PropostaPlanoAcaoViewModel viewModel);

}
