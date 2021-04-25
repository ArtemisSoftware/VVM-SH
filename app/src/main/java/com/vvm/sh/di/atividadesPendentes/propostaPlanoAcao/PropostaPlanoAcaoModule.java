package com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.di.atividadesPendentes.levantamentos.LevantamentosScope;
import com.vvm.sh.repositorios.PropostaPlanoAcaoRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class PropostaPlanoAcaoModule {

    @PropostaPlanoAcaoScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @PropostaPlanoAcaoScope
    @Provides
    static PropostaPlanoAcaoDao providePropostaPlanoAcaoDao(VvmshBaseDados vvmshBaseDados){

        PropostaPlanoAcaoDao dao = vvmshBaseDados.obterPropostaPlanoAcaoDao();
        return dao;
    }




    @PropostaPlanoAcaoScope
    @Provides
    PropostaPlanoAcaoRepositorio providePropostaPlanoAcaoRepositorio(@Named("idApi_") int idApi, PropostaPlanoAcaoDao propostaPlanoAcaoDao, ResultadoDao resultadoDao) {

        PropostaPlanoAcaoRepositorio repositorio = new PropostaPlanoAcaoRepositorio(idApi, propostaPlanoAcaoDao, resultadoDao);
        return repositorio;
    }


}
