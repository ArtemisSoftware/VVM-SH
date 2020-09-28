package com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.repositorios.PropostaPlanoAcaoRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class PropostaPlanoAcaoModule {

//
//    @PropostaPlanoAcaoScope
//    @Provides
//    static PropostaPlanoAcaoDao providePropostaPlanoAcaoDao(VvmshBaseDados vvmshBaseDados){
//
//        PropostaPlanoAcaoDao dao = vvmshBaseDados.obterPropostaPlanoAcaoDao();
//        return dao;
//    }
//
//
//
//
//    @PropostaPlanoAcaoScope
//    @Provides
//    PropostaPlanoAcaoRepositorio providePropostaPlanoAcaoRepositorio(PropostaPlanoAcaoDao propostaPlanoAcaoDao, ResultadoDao resultadoDao) {
//
//        PropostaPlanoAcaoRepositorio repositorio = new PropostaPlanoAcaoRepositorio(propostaPlanoAcaoDao, resultadoDao);
//        return repositorio;
//    }

}
