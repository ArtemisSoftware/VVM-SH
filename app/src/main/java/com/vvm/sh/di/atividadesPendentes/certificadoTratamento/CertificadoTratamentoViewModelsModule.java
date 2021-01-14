package com.vvm.sh.di.atividadesPendentes.certificadoTratamento;

import androidx.lifecycle.ViewModel;

import com.vvm.sh.di.ViewModelKey;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.CertificadoTratamentoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class CertificadoTratamentoViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CertificadoTratamentoViewModel.class)
    public abstract ViewModel bindCertificadoTratamentoViewModel(CertificadoTratamentoViewModel viewModel);

}
