package com.vvm.sh.di.atividadesPendentes;

import com.vvm.sh.baseDados.dao.AtividadePendenteDao;
import com.vvm.sh.baseDados.dao.ProcessoProdutivoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AtividadesPendentesModule {


    @AtividadesPendentesScope
    @Provides
    static AtividadePendenteDao provideAtividadePendenteResultadoDao(VvmshBaseDados vvmshBaseDados){

        AtividadePendenteDao dao = vvmshBaseDados.obterAtividadePendenteDao();

        //Timber.d("Providing NoteDao: " + dao);
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
    AtividadePendenteRepositorio provideAtividadePendenteRepositorio(int idApi,
                                                                     AtividadePendenteDao atividadePendenteDao, ProcessoProdutivoDao processoProdutivoDao,
                                                                     TipoDao tipoDao, ResultadoDao resultadoDao) {

        AtividadePendenteRepositorio repositorio = new AtividadePendenteRepositorio(idApi,atividadePendenteDao, processoProdutivoDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }


}
