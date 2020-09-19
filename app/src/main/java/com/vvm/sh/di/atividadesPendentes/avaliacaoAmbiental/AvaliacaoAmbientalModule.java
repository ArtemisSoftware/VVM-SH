package com.vvm.sh.di.atividadesPendentes.avaliacaoAmbiental;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.MedidaDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.repositorios.AvaliacaoAmbientalRepositorio;

import dagger.Module;
import dagger.Provides;

@Module
public class AvaliacaoAmbientalModule {



    @AvaliacaoAmbientalScope
    @Provides
    static AvaliacaoAmbientalDao provideAvaliacaoAmbientalDao(VvmshBaseDados vvmshBaseDados){

        AvaliacaoAmbientalDao dao = vvmshBaseDados.obterAvaliacaoAmbientalDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }



    @AvaliacaoAmbientalScope
    @Provides
    static CategoriaProfissionalDao provideCategoriaProfissionalDao(VvmshBaseDados vvmshBaseDados){

        CategoriaProfissionalDao dao = vvmshBaseDados.obterCategoriaProfissionalDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AvaliacaoAmbientalScope
    @Provides
    static MedidaDao provideMedidaDao(VvmshBaseDados vvmshBaseDados){

        MedidaDao dao = vvmshBaseDados.obterMedidaDao();

        //Timber.d("Providing NoteDao: " + dao);
        return dao;
    }


    @AvaliacaoAmbientalScope
    @Provides
    AvaliacaoAmbientalRepositorio provideAtividadePendenteRepositorio(int idApi, AvaliacaoAmbientalDao avaliacaoAmbientalDao, CategoriaProfissionalDao categoriaProfissionalDao,
                                                                      MedidaDao medidaDao,
                                                                      TipoDao tipoDao, ResultadoDao resultadoDao) {

        AvaliacaoAmbientalRepositorio repositorio = new AvaliacaoAmbientalRepositorio(idApi, avaliacaoAmbientalDao, categoriaProfissionalDao, medidaDao, tipoDao, resultadoDao);

        //Timber.d("Providing PokemonRepository: " + repository);
        return repositorio;
    }

}
