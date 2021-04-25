package com.vvm.sh.di.atividadesPendentes.levantamentos;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.LevantamentoAvaliacaoDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.RiscoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental.AvaliacaoAmbientalScope;
import com.vvm.sh.di.atividadesPendentes.checklist.ChecklistScope;
import com.vvm.sh.repositorios.LevantamentoAvaliacaoRepositorio;
import com.vvm.sh.repositorios.LevantamentoRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LevantamentosModule {

    @LevantamentosScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @LevantamentosScope
    @Provides
    static CategoriaProfissionalDao provideCategoriaProfissionalDao(VvmshBaseDados vvmshBaseDados){

        CategoriaProfissionalDao dao = vvmshBaseDados.obterCategoriaProfissionalDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    static LevantamentoDao provideProvidesLevantamentoDao(VvmshBaseDados vvmshBaseDados){

        LevantamentoDao dao = vvmshBaseDados.obterLevantamentoDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    static RiscoDao provideProvidesRiscoDao(VvmshBaseDados vvmshBaseDados){

        RiscoDao dao = vvmshBaseDados.obterRiscoDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    static MedidaDao provideProvidesMedidaDao(VvmshBaseDados vvmshBaseDados){

        MedidaDao dao = vvmshBaseDados.obterMedidaDao();
        return dao;
    }

    @LevantamentosScope
    @Provides
    static PropostaPlanoAcaoDao provideProvidesPropostaPlanoAcaoDao(VvmshBaseDados vvmshBaseDados){

        PropostaPlanoAcaoDao dao = vvmshBaseDados.obterPropostaPlanoAcaoDao();
        return dao;
    }


    @LevantamentosScope
    @Provides
    LevantamentoRepositorio provideLevantamentoRepositorio(@Named("idApi_") int idApi,
                                                           LevantamentoDao levantamentoDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                           RiscoDao riscoDao, MedidaDao medidaDao, PropostaPlanoAcaoDao propostaPlanoAcaoDao, ImagemDao imagemDao,
                                                           TipoDao tipoDao, ResultadoDao resultadoDao) {

        LevantamentoRepositorio repositorio = new LevantamentoRepositorio(idApi, levantamentoDao, categoriaProfissionalDao, riscoDao, medidaDao, propostaPlanoAcaoDao, imagemDao, tipoDao, resultadoDao);
        return repositorio;
    }


    @LevantamentosScope
    @Provides
    static LevantamentoAvaliacaoDao provideProvidesLevantamentoAvaliacaoDao(VvmshBaseDados vvmshBaseDados){

        LevantamentoAvaliacaoDao dao = vvmshBaseDados.obterLevantamentoAvaliacaoDao();
        return dao;
    }

    @LevantamentosScope
    @Provides
    LevantamentoAvaliacaoRepositorio provideLevantamentoAvaliacaoRepositorio(LevantamentoAvaliacaoDao levantamentoAvaliacaoDao) {

        LevantamentoAvaliacaoRepositorio repositorio = new LevantamentoAvaliacaoRepositorio(levantamentoAvaliacaoDao);
        return repositorio;
    }
}
