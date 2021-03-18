package com.vvm.sh.di.transferencias.atualizacaoApp;

import com.vvm.sh.api.SegurancaHigieneApi;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.di.opcoes.OpcoesScope;
import com.vvm.sh.repositorios.VersaoAppRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AtualizacaoAppModule {


    @AtualizacaoAppScope
    @Provides
    VersaoAppRepositorio provideVersaoAppRepositorio(SegurancaHigieneApi segurancaHigieneApi) {

        VersaoAppRepositorio repositorio = new VersaoAppRepositorio(segurancaHigieneApi);
        return repositorio;
    }



}
