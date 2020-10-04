package com.vvm.sh.di.atividadesPendentes.averiguacao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.AveriguacaoDao;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AveriguacaoModule {

    @AveriguacaoScope
    @Provides
    static AveriguacaoDao provideAveriguacaoDao(VvmshBaseDados vvmshBaseDados){

        AveriguacaoDao dao = vvmshBaseDados.obterAveriguacaoDao();
        return dao;
    }

}
