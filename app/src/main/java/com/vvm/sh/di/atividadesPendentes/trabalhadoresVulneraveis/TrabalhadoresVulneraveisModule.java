package com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis;

import android.app.Application;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.di.atividadesPendentes.propostaPlanoAcao.PropostaPlanoAcaoScope;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.TrabalhadoresVulneraveisRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TrabalhadoresVulneraveisModule {

    @TrabalhadoresVulneraveisScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @TrabalhadoresVulneraveisScope
    @Provides
    static TrabalhadoresVulneraveisDao provideTrabalhadoresVulneraveisDao(VvmshBaseDados vvmshBaseDados){

        TrabalhadoresVulneraveisDao dao = vvmshBaseDados.obterTrabalhadoresVulneraveisDao();
        return dao;
    }



    @TrabalhadoresVulneraveisScope
    @Provides
    static CategoriaProfissionalDao provideCategoriaProfissionalDao(VvmshBaseDados vvmshBaseDados){

        CategoriaProfissionalDao dao = vvmshBaseDados.obterCategoriaProfissionalDao();

        return dao;
    }


    @TrabalhadoresVulneraveisScope
    @Provides
    TrabalhadoresVulneraveisRepositorio provideTrabalhadoresVulneraveisRepositorio(@Named("idApi_") int idApi,
                                                                                   TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                                                   ResultadoDao resultadoDao) {

        TrabalhadoresVulneraveisRepositorio repositorio = new TrabalhadoresVulneraveisRepositorio(idApi,trabalhadoresVulneraveisDao, categoriaProfissionalDao, resultadoDao);
        return repositorio;
    }

}
