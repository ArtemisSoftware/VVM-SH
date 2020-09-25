package com.vvm.sh.di.atividadesPendentes.trabalhadoresVulneraveis;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TrabalhadoresVulneraveisDao;
import com.vvm.sh.di.atividadesPendentes.AtividadesPendentesScope;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.TrabalhadoresVulneraveisRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class TrabalhadoresVulneraveisModule {

    @TrabalhadoresVulneraveisScope
    @Provides
    static TrabalhadoresVulneraveisDao provideTrabalhadoresVulneraveisDao(VvmshBaseDados vvmshBaseDados){

        TrabalhadoresVulneraveisDao dao = vvmshBaseDados.obterTrabalhadoresVulneraveisDao();
        return dao;
    }



    @TrabalhadoresVulneraveisScope
    @Provides
    TrabalhadoresVulneraveisRepositorio provideTrabalhadoresVulneraveisRepositorio(int idApi,
                                                                                   TrabalhadoresVulneraveisDao trabalhadoresVulneraveisDao, ResultadoDao resultadoDao) {

        TrabalhadoresVulneraveisRepositorio repositorio = new TrabalhadoresVulneraveisRepositorio(idApi,trabalhadoresVulneraveisDao, resultadoDao);
        return repositorio;
    }

}
