package com.vvm.sh.di.atividadesPendentes;

import android.app.Application;

import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.di.atividadesPendentes.certificadoTratamento.CertificadoTratamentoScope;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AtividadesPendentesModule {

    @AtividadesPendentesScope
    @Provides
    @Named("idApi_")
    static int provideIdApi(Application application){

        int idApi = PreferenciasUtil.obterIdApi(application.getApplicationContext());
        return idApi;
    }

    @AtividadesPendentesScope
    @Provides
    static AtividadePendenteDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();
        return dao;
    }


    @AtividadesPendentesScope
    @Provides
    static ProcessoProdutivoDao provideProcessoProdutivoDao(VvmshBaseDados vvmshBaseDados){

        ProcessoProdutivoDao dao = vvmshBaseDados.obterProcessoProdutivoDao();
        return dao;
    }


    @AtividadesPendentesScope
    @Provides
    AtividadePendenteRepositorio provideAtividadePendenteRepositorio(@Named("idApi_") int idApi,
                                                                     AtividadePendenteDao atividadePendenteDao, ProcessoProdutivoDao processoProdutivoDao,
                                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        AtividadePendenteRepositorio repositorio = new AtividadePendenteRepositorio(idApi,atividadePendenteDao, processoProdutivoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
